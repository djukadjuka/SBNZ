package com.djuka.ftn.services.buyer;

import java.util.ArrayList;

import com.djuka.ftn.model.users.buyer.BuyerCategory;

public interface BuyerCategoryServices {
	public ArrayList<BuyerCategory> getAllCategories();
	public void editBuyerCategory(Integer id, Float pointsPercentage, Float max, Float min);
	public BuyerCategory getBuyer(Integer id);
}
