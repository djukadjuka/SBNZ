package com.djuka.ftn.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.djuka.ftn.util.KShortcuts;

@RequestMapping("/index")
@RestController
public class SBNZIndexController {

	@RequestMapping(
			path="/load-resources",
			produces=MediaType.APPLICATION_JSON_VALUE,
			method=RequestMethod.GET
			)
	public ResponseEntity<HashMap<String,Object>> loadResources(@Context HttpServletRequest request){
		HashMap<String,Object> map = new HashMap<>();
		
		Object o = request.getSession().getAttribute(ControllerConstants.SESSION_USER_KEYWORD);
		if(o == null){
			map.put("STATUS", false);
			return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
		}
		
		map.put("STATUS", true);
		map.put("USER", o);
		
		return new ResponseEntity<HashMap<String,Object>>(map,HttpStatus.OK);
	}
	
}
