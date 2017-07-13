package com.djuka.ftn.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.djuka.ftn.model.users.BaseUser;

@Repository
public interface BaseUserRepository extends JpaRepository<BaseUser, Long>{

	@Query(nativeQuery=true,value="SELECT * FROM base_user;")
	public ArrayList<BaseUser> getAllUsers();

}
