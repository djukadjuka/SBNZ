package com.djuka.ftn.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.djuka.ftn.model.users.BaseUser;
import com.djuka.ftn.model.users.UserConstants;
import com.djuka.ftn.model.users.buyer.Buyer;
import com.djuka.ftn.services.buyer.BuyerCategoryServices;
import com.djuka.ftn.services.buyer.BuyerServices;
import com.djuka.ftn.services.receipts.ReceiptServices;

@RequestMapping("/profile")
@RestController
public class SBNZProfileController {

	@Autowired
	private BuyerServices buyerServices;
	
	@Autowired
	private BuyerCategoryServices BuyerCategoryServices;
	
	@Autowired
	private ReceiptServices receiptServices;
	
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
		
		map.put(ControllerConstants.MAP_KEY_STATUS, true);
		map.put(ControllerConstants.MAP_KEY_USER, user);
		
		if(user.getRole() == UserConstants.USER_ROLE_BUYER){
			Buyer buyer = buyerServices.getBuyerById(user.getId());
			map.put(ControllerConstants.MAP_KEY_USER_ROLE_BUYER , buyer);
			map.put(ControllerConstants.MAP_KEY_RECEIPTS_FOR_USER, receiptServices.getReceiptsForUser(buyer.getId()));
			
		}else if(user.getRole() == UserConstants.USER_ROLE_MANAGER){
			//implement for manager
		}else{
			//implement for seller
		}
		
		return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
	}
}
