package com.djuka.ftn.controllers.ruleWrappers;

import com.djuka.ftn.model.users.buyer.BuyerCategory;

public class PointsWrapper {
	public BuyerCategory buyerCategory;
	public Float finalReceiptPrice;
	public Integer achievedPoints;
	@Override
	public String toString() {
		return "PointsWrapper [buyerCategory=" + buyerCategory + ", finalReceiptPrice=" + finalReceiptPrice
				+ ", achievedPoints=" + achievedPoints + "]";
	}
		
	
}
