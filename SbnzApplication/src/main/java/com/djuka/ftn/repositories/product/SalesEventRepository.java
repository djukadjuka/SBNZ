package com.djuka.ftn.repositories.product;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;

import com.djuka.ftn.model.products.SalesEvent;

@Repository
public interface SalesEventRepository extends JpaRepository<SalesEvent, Integer>{

	@Transactional
	@Modifying
	@Query(nativeQuery=true,
	value="DELETE FROM product_category_relation WHERE event_id = :id")
	public void killAllRelationsWithSalesEvent(@Param("id") Integer seId);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,
	value="INSERT INTO product_category_relation (event_id, product_category_id) VALUES (:e_id, :p_id)")
	public void insertProductCategoryRelation(@Param("e_id") Integer e_id, @Param("p_id") Integer p_id);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,
	value="INSERT INTO sales_event (id,end_date,event_discount,event_name,start_date)"
			+ " VALUES (null, :end_date, :event_discount, :event_name, :start_date)")
	public Integer insertNewSalesEvent(@Param("end_date") Long endDate, 
			@Param("event_discount") Float eventDiscount, 
			@Param("event_name") String eventName, 
			@Param("start_date") Long startDate);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,
			value="UPDATE sales_event SET "
					+ " end_date = :end_date,"
					+ " start_date = :start_date, "
					+ " event_name = :event_name,"
					+ " event_discount = :event_discount WHERE id = :id")
	public void editSalesEvent(@Param("end_date") Long endDate, 
			@Param("event_discount") Float eventDiscount, 
			@Param("event_name") String eventName, 
			@Param("start_date") Long startDate,
			@Param("id") Integer id);
}
