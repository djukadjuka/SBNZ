package com.djuka.ftn.repositories.buyer;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.djuka.ftn.model.users.buyer.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer>{

	@Transactional
	@Modifying
	@Query(nativeQuery = true,
			value = "UPDATE buyer SET reward_points = reward_points + :points WHERE id = :id")
	public void updateBuyerPoints(@Param("id") Integer buyerId, @Param("points") Integer pointsOffset);
	
}
