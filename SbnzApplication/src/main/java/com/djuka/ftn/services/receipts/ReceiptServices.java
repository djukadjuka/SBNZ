package com.djuka.ftn.services.receipts;

import java.util.ArrayList;

import com.djuka.ftn.model.receipts.Receipt;

public interface ReceiptServices {
	public ArrayList<Receipt> getReceiptsForUser(Integer user_id);
	
	public Receipt save(Receipt r);
	
	public ArrayList<Receipt> findAllReceipts();
	
	public ArrayList<Receipt> getAllUnprocessedReceipts();
	
	public void updateReceiptState(Integer state, Integer id);
	
	public Receipt findOneReceipt(Integer id);
}
