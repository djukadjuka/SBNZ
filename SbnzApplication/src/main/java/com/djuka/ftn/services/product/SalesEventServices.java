package com.djuka.ftn.services.product;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.djuka.ftn.model.products.SalesEvent;

public interface SalesEventServices {
	public ArrayList<SalesEvent> getAllSalesEvents();
	
	public void killAllRelationsWithSalesEvent(Integer seId);
	
	public void insertProductCategoryRelation(Integer e_id, Integer p_id);
	
	public Integer insertNewSalesEvent(Long endDate, Float eventDiscount, String eventName, Long startDate);
	
	public void editSalesEvent(Long endDate, Float eventDiscount, String eventName, Long startDate,Integer id);
	
	public SalesEvent getOneSalesEvent(Integer id);
}
