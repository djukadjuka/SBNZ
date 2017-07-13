package com.djuka.ftn.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djuka.ftn.model.users.BaseUser;
import com.djuka.ftn.repositories.BaseUserRepository;

@Service
public class BaseUserServicesImpl implements BaseUserServices{

	@Autowired private BaseUserRepository repository;
	
	@Override
	public ArrayList<BaseUser> getAllUsers() {
		return repository.getAllUsers();
	}

	@Override
	public BaseUser save(BaseUser user) {
		return repository.save(user);
	}

}
