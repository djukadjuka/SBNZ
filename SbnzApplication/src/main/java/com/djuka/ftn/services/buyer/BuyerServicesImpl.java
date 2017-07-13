package com.djuka.ftn.services.buyer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djuka.ftn.model.users.buyer.Buyer;
import com.djuka.ftn.repositories.buyer.BuyerRepository;

@Service
public class BuyerServicesImpl implements BuyerServices{

	@Autowired
	private BuyerRepository buyerRepository;
	
	public Buyer getBuyerById(Integer id){
		return buyerRepository.findOne(id);
	}

	@Override
	public void updateBuyerPoints(Integer buyerId, Integer pointsOffset) {
		this.buyerRepository.updateBuyerPoints(buyerId, pointsOffset);
	}

	@Override
	public Buyer save(Buyer b) {
		return buyerRepository.save(b);
	}
	
}
