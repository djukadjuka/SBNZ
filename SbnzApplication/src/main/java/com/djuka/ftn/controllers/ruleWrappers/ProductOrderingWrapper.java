package com.djuka.ftn.controllers.ruleWrappers;

import com.djuka.ftn.model.products.Product;

public class ProductOrderingWrapper {
	public Product product;
	public int orderedNumber;
	@Override
	public String toString() {
		return "ProductOrderingWrapper [product=" + product + ", orderedNumber=" + orderedNumber + "]";
	}
	
}
