package com.djuka.ftn.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.djuka.ftn.controllers.ruleWrappers.ProductOrderingWrapper;
import com.djuka.ftn.model.products.Product;
import com.djuka.ftn.model.receipts.Receipt;
import com.djuka.ftn.model.receipts.ReceiptConstants;
import com.djuka.ftn.model.receipts.ReceiptEntry;
import com.djuka.ftn.model.users.BaseUser;
import com.djuka.ftn.model.users.UserConstants;
import com.djuka.ftn.services.buyer.BuyerServices;
import com.djuka.ftn.services.product.ProductServices;
import com.djuka.ftn.services.receipts.ReceiptServices;
import com.djuka.ftn.util.KShortcuts;

@RequestMapping("/seller")
@RestController
public class SellerController {

	@Autowired
	private ReceiptServices receiptServices;
	
	@Autowired
	private ProductServices productServices;
	
	@Autowired
	private BuyerServices buyerServices;
	
	@RequestMapping(
			method = RequestMethod.POST,
			path = "/order-products",
			produces = MediaType.APPLICATION_JSON,
			consumes = MediaType.APPLICATION_JSON
			)
	public ResponseEntity<HashMap<String,Object>> orderMoreProducts(@RequestBody HashMap<String,Integer> map, @Context HttpServletRequest request){
		
		productServices.updateProductAmount(map.get("id"), (-1)*map.get("amount"));
		
		return loadResources(request);
	}
	
	@RequestMapping(
			path="/load-resources",
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON
			)
	public ResponseEntity<HashMap<String,Object>> loadResources(@Context HttpServletRequest request){
		HashMap<String,Object> map = new HashMap<>();
		
		Object o = request.getSession().getAttribute(ControllerConstants.SESSION_USER_KEYWORD);
		if(o == null){
			map.put(ControllerConstants.MAP_KEY_STATUS, false);
			return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
		}
		
		
		BaseUser user = (BaseUser) o;
		
		if(user.getRole() == UserConstants.USER_ROLE_BUYER){
			map.put(ControllerConstants.MAP_KEY_STATUS, false);
		}else if(user.getRole() == UserConstants.USER_ROLE_MANAGER){
			map.put(ControllerConstants.MAP_KEY_STATUS, false);
			
		}else{
			
			map.put(ControllerConstants.MAP_KEY_STATUS, true);
			map.put(ControllerConstants.MAP_KEY_USER, user);
			map.put(ControllerConstants.MAP_KEY_PRODUCTS_FOR_ORDERING, createProductsForOrdering());
			map.put(ControllerConstants.MAP_KEY_UNPROCESSED_RECEIPTS, receiptServices.getAllUnprocessedReceipts());
			map.put(ControllerConstants.MAP_KEY_ALL_AVAILABLE_PRODUCTS, productServices.getNonArchivedProducts());
		}
		
		return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
	}
	
	private ArrayList<Receipt> getUnProcessedReceipts(){
		ArrayList<Receipt> allReceipts = receiptServices.findAllReceipts();
		ArrayList<Receipt> unprocessed = new ArrayList<>();
		
		for(Receipt r : allReceipts){
			if(r.getReceiptState() == ReceiptConstants.RECEIPT_ORDERED)
				unprocessed.add(r);
		}
		
		return unprocessed;
	}
	
	private HashMap<Integer,Integer> createProductQuantityMap(){
		HashMap<Integer,Integer> productQuantityMap = new HashMap<>();
		
		ArrayList<Receipt> unprocessedReceipts = getUnProcessedReceipts();
		
		for(Receipt receipt : unprocessedReceipts){
			for(ReceiptEntry receiptEntry : receipt.getReceiptEntries()){
				
				int prodId = receiptEntry.getProduct().getId();
				int num = receiptEntry.getNumberOfProducts();
				
				if(productQuantityMap.containsKey(prodId)){
					num += productQuantityMap.get(prodId);
				}
				productQuantityMap.put(prodId , num);
			}
		}
		
		return productQuantityMap;
	}

	@RequestMapping(
			path="/approve-receipt",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON,
			produces=MediaType.APPLICATION_JSON
			)
	public ResponseEntity<HashMap<String,Object>> approveReceipt(@RequestBody Receipt receipt){
		
		receipt = receiptServices.findOneReceipt(receipt.getId());
		
		if(receipt.getAquiredPoints() != null){
			buyerServices.updateBuyerPoints(receipt.getBuyer().getId(), receipt.getAquiredPoints());			
		}
		
		receiptServices.updateReceiptState(ReceiptConstants.RECEIPT_SUCCESS, receipt.getId());
		
		for(ReceiptEntry entry : receipt.getReceiptEntries()){
			productServices.updateProductAmount(entry.getProduct().getId(), entry.getNumberOfProducts());
		}
		
		HashMap<String,Object> map = new HashMap<>();
		
		map.put(ControllerConstants.MAP_KEY_PRODUCTS_FOR_ORDERING, createProductsForOrdering());
		map.put(ControllerConstants.MAP_KEY_UNPROCESSED_RECEIPTS, receiptServices.getAllUnprocessedReceipts());
		map.put(ControllerConstants.MAP_KEY_ALL_AVAILABLE_PRODUCTS, productServices.getAllAvailableProducts());
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	@RequestMapping(
			path="/reject-receipt",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON,
			produces=MediaType.APPLICATION_JSON
			)
	public ResponseEntity<HashMap<String,Object>> rejectReceipt(@RequestBody Receipt receipt){
		
		receipt = receiptServices.findOneReceipt(receipt.getId());
		
		if(receipt.getAquiredPoints() != null){
			buyerServices.updateBuyerPoints(receipt.getBuyer().getId(), receipt.getSpentPoints());			
		}
		
		receiptServices.updateReceiptState(ReceiptConstants.RECEIPT_FAILURE, receipt.getId());
		
		HashMap<String,Object> map = new HashMap<>();
		
		map.put(ControllerConstants.MAP_KEY_PRODUCTS_FOR_ORDERING, createProductsForOrdering());
		map.put(ControllerConstants.MAP_KEY_UNPROCESSED_RECEIPTS, receiptServices.getAllUnprocessedReceipts());
		map.put(ControllerConstants.MAP_KEY_ALL_AVAILABLE_PRODUCTS, productServices.getAllAvailableProducts());
		
		return new ResponseEntity<>(map, HttpStatus.OK);
	}
	
	private HashMap<Integer, String> createProductsForOrdering(){
		HashMap<Integer,String> mapForOrders = new HashMap<>();
		
		HashMap<Integer,Integer> productQuantityMap = createProductQuantityMap();
		
		ArrayList<Product> allProducts = productServices.getNonArchivedProducts();
		
		for(Product p : allProducts){
			
			ProductOrderingWrapper wrapper = new ProductOrderingWrapper();
			wrapper.product = p;
			wrapper.product.setRestock(false);
			
			if(productQuantityMap.containsKey(p.getId())){
				wrapper.orderedNumber = productQuantityMap.get(p.getId());
			}else{
				wrapper.orderedNumber = 0;
			}
			
			KieSession session = KShortcuts.getKieSession();
			session.getAgenda().getAgendaGroup("OrderingRules").setFocus();
			session.insert(wrapper);
			session.insert(mapForOrders);
			session.fireAllRules();
			session.dispose();
			
			productServices.setRestockOfProduct(wrapper.product.getRestock(), wrapper.product.getId());
		}
		
		return mapForOrders;
	}
}
