package com.djuka.ftn.repositories.receipts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.djuka.ftn.model.receipts.FullDiscount;

@Repository
public interface FullDiscountRepository extends JpaRepository<FullDiscount, Integer>{

}
