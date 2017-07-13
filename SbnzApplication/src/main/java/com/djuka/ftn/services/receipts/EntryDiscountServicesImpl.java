package com.djuka.ftn.services.receipts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djuka.ftn.model.receipts.EntryDiscount;
import com.djuka.ftn.repositories.receipts.EntryDiscountRepository;

@Service
public class EntryDiscountServicesImpl implements EntryDiscountServices{

	@Autowired
	private EntryDiscountRepository repo;
	
	@Override
	public EntryDiscount save(EntryDiscount entryDiscount) {
		return repo.save(entryDiscount);
	}

}
