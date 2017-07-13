package com.djuka.ftn.repositories.buyer;

import javax.transaction.Transactional;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.djuka.ftn.model.users.buyer.BuyerCategory;

@Repository
public interface BuyerCategoryRepository extends JpaRepository<BuyerCategory, Integer>{
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,value="UPDATE buyer_category SET points_percentage=:pp, spending_bound_max=:max, spending_bound_min=:min WHERE buyer_category_id=:id")
	public void editBuyerCategory(@Param("id") Integer id, @Param("pp") Float pointsPercentage, @Param("max") Float max, @Param("min") Float min); 
	
	@Query(nativeQuery=true, value="SELECT * FROM buyer_category WHERE buyer_category_id = :id")
	public BuyerCategory getBuyer(@Param("id") Integer id);
}
