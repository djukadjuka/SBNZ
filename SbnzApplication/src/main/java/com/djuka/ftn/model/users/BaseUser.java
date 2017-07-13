package com.djuka.ftn.model.users;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
public class BaseUser implements Serializable{

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="username",nullable=false)
	private String username;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="surname",nullable=false)
	private String surname;
	
	@Column(name="role",nullable=false)
	private Integer role;
	
	@Column(name="registry_date",nullable=false)
	private Long registryDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Long getRegistryDate() {
		return registryDate;
	}

	public void setRegistryDate(Long registryDate) {
		this.registryDate = registryDate;
	}
	
	@Override
	public int hashCode(){
		return this.id;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof BaseUser && o != null && ((BaseUser) o).getId() == this.id){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		return "Userid:["+this.id+"], Username:["+this.username+"], Password:["+this.password+"], Name:["+this.name+"], Surname:["+this.surname+"], Registry:["+this.registryDate+"]";
	}
}
