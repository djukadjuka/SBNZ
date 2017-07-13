package com.djuka.ftn.controllers.ruleWrappers;

import java.util.ArrayList;

import com.djuka.ftn.model.products.SalesEvent;

public class BonusDiscountRuleWrapper {
	public ArrayList<Float> addedEntries = new  ArrayList<Float>();
	public ArrayList<Integer> specialKeys = new ArrayList<>();
	//sales events for products category
	public ArrayList<SalesEvent> salesEvents = new ArrayList<>();
	@Override
	public String toString() {
		return "BonusDiscountRuleWrapper [addedEntries=" + addedEntries + ", specialKeys=" + specialKeys
				+ ", salesEvents=" + salesEvents + "]";
	}
	
	
	
	
}
