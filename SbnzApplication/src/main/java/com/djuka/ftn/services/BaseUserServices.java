package com.djuka.ftn.services;

import java.util.ArrayList;

import com.djuka.ftn.model.users.BaseUser;

public interface BaseUserServices {
	
	public ArrayList<BaseUser> getAllUsers();

	public BaseUser save(BaseUser user);
	
}
