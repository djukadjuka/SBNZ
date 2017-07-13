package com.djuka.ftn.services.receipts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djuka.ftn.model.receipts.FullDiscount;
import com.djuka.ftn.repositories.receipts.FullDiscountRepository;

@Service
public class FullDiscountServicesImpl implements FullDiscountServices{

	@Autowired
	private FullDiscountRepository r;
	
	@Override
	public FullDiscount save(FullDiscount fullDiscount) {
		return r.save(fullDiscount);
	}

}
