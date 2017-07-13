package com.djuka.ftn.repositories.receipts;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.djuka.ftn.model.receipts.Receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer>{

	@Query(nativeQuery = true, 
			value="SELECT * FROM receipt WHERE buyer_id = :user_id")
	public ArrayList<Receipt> getReceiptsForUser(@Param("user_id") Integer user_id);
	
	@Query(nativeQuery = true,
			value="SELECT * FROM receipt WHERE receipt_state = 2")
	public ArrayList<Receipt> getAllUnprocessedReceipts();
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,
	value="UPDATE receipt SET receipt_state = :state WHERE id = :id")
	public void updateReceiptState(@Param("state") Integer state, @Param("id") Integer id);
}
