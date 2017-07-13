package com.djuka.ftn.services.buyer;

import com.djuka.ftn.model.users.buyer.Buyer;

public interface BuyerServices {

	public Buyer getBuyerById(Integer id);
	
	public void updateBuyerPoints(Integer buyerId, Integer pointsOffset);
	
	public Buyer save(Buyer b);
}
