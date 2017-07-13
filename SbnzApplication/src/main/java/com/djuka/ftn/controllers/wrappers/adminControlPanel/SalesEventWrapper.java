package com.djuka.ftn.controllers.wrappers.adminControlPanel;

import java.util.ArrayList;

//{id:id, name:name, startDate:new Date(startDate).getTime(),endDate:new Date(endDate).getTime(), discount:discount}

public class SalesEventWrapper {
	
	private String name;
	private Integer id;
	private Long startDate;
	private Long endDate;
	private Float discount;
	private ArrayList<Integer> validForCategories;
	
	public ArrayList<Integer> getValidForCategories() {
		return validForCategories;
	}
	public void setValidForCategories(ArrayList<Integer> validForCategories) {
		this.validForCategories = validForCategories;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	public Float getDiscount() {
		return discount;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "SalesEventWrapper [name=" + name + ", id=" + id + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", discount=" + discount + ", validForCategories=" + validForCategories + "]";
	}
	
	
	
}
