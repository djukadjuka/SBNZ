package com.djuka.ftn.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.djuka.ftn.model.users.BaseUser;
import com.djuka.ftn.model.users.buyer.Buyer;
import com.djuka.ftn.services.BaseUserServices;
import com.djuka.ftn.services.buyer.BuyerCategoryServices;
import com.djuka.ftn.services.buyer.BuyerServices;
import com.djuka.ftn.util.KShortcuts;

@RequestMapping("/login-register")
@RestController
public class SBNZLoginRegisterController {
	
	@Autowired
	private BaseUserServices baseUserServices;
	
	@Autowired
	private BuyerServices buyerServices;
	
	@Autowired
	private BuyerCategoryServices buyerCategoryServices;
	
	@RequestMapping(
			path="/load-resources",
			produces=MediaType.APPLICATION_JSON,
			method=RequestMethod.GET
			)
	public ResponseEntity<HashMap<String,Object>> loadResources(@Context HttpServletRequest request){
		HashMap<String,Object> map = new HashMap<>();
		
		if(request.getSession().getAttribute(ControllerConstants.SESSION_USER_KEYWORD) != null){
			map.put(ControllerConstants.MAP_KEY_STATUS, false);
			return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
		}
		
		KShortcuts.getKieSession().dispose();
		
		map.put(ControllerConstants.MAP_KEY_STATUS, true);
		return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
	}
	
	@RequestMapping(
			path="/register",
			consumes=MediaType.APPLICATION_JSON,
			method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON
			)
	public ResponseEntity<HashMap<String,Object>> register(@RequestBody BaseUser user,@Context HttpServletRequest context){
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		ArrayList<BaseUser> users  = baseUserServices.getAllUsers();
		for(BaseUser u : users){
			if(u.getUsername().equals(user.getUsername())){
				map.put(ControllerConstants.MAP_KEY_STATUS, false);
				map.put(ControllerConstants.MAP_KEY_MESSAGE,"Selected username already exists!");
				return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
			}
		}
		
		user = baseUserServices.save(user);
		
		Buyer buyer = new Buyer();
		buyer.setAddress("NDEF");
		buyer.setBuyerCategory(buyerCategoryServices.getBuyer(1));
		buyer.setId(user.getId());
		buyer.setRewardPoints(0);
		buyer.setUser(user);
		
		buyer = buyerServices.save(buyer);
		
		context.getSession().setAttribute(ControllerConstants.SESSION_USER_KEYWORD, user);
		context.getSession().setAttribute(ControllerConstants.MAP_KEY_USER_ROLE_BUYER, buyer);
		
		map.put(ControllerConstants.MAP_KEY_STATUS, true);
		
		return new ResponseEntity<HashMap<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(
			path="/login",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON,
			produces=MediaType.APPLICATION_JSON
			)
	public ResponseEntity<HashMap<String,Object>> login(@RequestBody BaseUser user, @Context HttpServletRequest context){
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		for(BaseUser u : baseUserServices.getAllUsers()){
			if(u.getUsername().equals(user.getUsername())){
				if(u.getPassword().equals(user.getPassword())){
					//success
					Buyer b = buyerServices.getBuyerById(u.getId());
					context.getSession().setAttribute(ControllerConstants.MAP_KEY_USER_ROLE_BUYER, b);
					context.getSession().setAttribute(ControllerConstants.SESSION_USER_KEYWORD, u);
					map.put(ControllerConstants.MAP_KEY_STATUS, true);
					return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
				}
				//password error
				map.put(ControllerConstants.MAP_KEY_STATUS, false);
				map.put(ControllerConstants.MAP_KEY_MESSAGE, "Password incorrect!");
				return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
			}
		}
		//does not exist
		map.put(ControllerConstants.MAP_KEY_STATUS, false);
		map.put(ControllerConstants.MAP_KEY_MESSAGE, "User does not exist!");
		return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
		
	}
	
}
