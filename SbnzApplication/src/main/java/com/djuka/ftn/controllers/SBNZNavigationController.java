package com.djuka.ftn.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/navbar")
@RestController
public class SBNZNavigationController {
	
	@RequestMapping(
			path="/logout",
			produces=MediaType.APPLICATION_JSON,
			method=RequestMethod.GET
			)
	public ResponseEntity<HashMap<String,Object>> logout(@Context HttpServletRequest request){
		request.getSession().invalidate();
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		System.out.println(request.getSession().getAttribute(ControllerConstants.SESSION_USER_KEYWORD));
		
		return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
	}
	
}
