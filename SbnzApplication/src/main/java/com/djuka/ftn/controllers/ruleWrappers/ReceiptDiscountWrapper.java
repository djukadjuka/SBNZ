package com.djuka.ftn.controllers.ruleWrappers;

import java.util.ArrayList;

import com.djuka.ftn.model.receipts.FullDiscount;

public class ReceiptDiscountWrapper {
	public FullDiscount initialDiscount;
	public ArrayList<FullDiscount> bonusDiscounts = new ArrayList<>();
	public Float productPrice;
	public Long userLogDate;
	public Long currentDate;
	public Long years2;
	public String userCategoryName;
	public ArrayList<Integer> addedDiscounts = new ArrayList<>();
	public double maxPrice;
	
	@Override
	public String toString() {
		return "ReceiptDiscountWrapper [initialDiscount=" + initialDiscount + ", bonusDiscounts=" + bonusDiscounts
				+ ", productPrice=" + productPrice + ", userLogDate=" + userLogDate + ", currentDate=" + currentDate
				+ ", years2=" + years2 + ", addedDiscounts=" + addedDiscounts + "]";
	}
	public FullDiscount getInitialDiscount() {
		return initialDiscount;
	}
	public void setInitialDiscount(FullDiscount initialDiscount) {
		this.initialDiscount = initialDiscount;
	}
	public ArrayList<FullDiscount> getBonusDiscounts() {
		return bonusDiscounts;
	}
	public void setBonusDiscounts(ArrayList<FullDiscount> bonusDiscounts) {
		this.bonusDiscounts = bonusDiscounts;
	}
	public Float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}
	
}
