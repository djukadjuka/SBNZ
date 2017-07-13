package com.djuka.ftn.services.receipts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djuka.ftn.model.receipts.ReceiptEntry;
import com.djuka.ftn.repositories.receipts.ReceiptEntryRepository;

@Service
public class ReceiptEntryServicesImpl implements ReceiptEntryServices{

	@Autowired
	private ReceiptEntryRepository repository;
	
	@Override
	public ReceiptEntry save(ReceiptEntry entry) {
		return repository.save(entry);
	}

}
