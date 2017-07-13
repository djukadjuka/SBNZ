package com.djuka.ftn.repositories.product;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.djuka.ftn.model.products.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	@Query(nativeQuery=true,value="SELECT * FROM product WHERE archived = 0 and amount_in_stock > 0")
	public ArrayList<Product> getAllAvailableProducts();
	
	@Query(nativeQuery=true, value = "SELECT * FROM product WHERE archived = 0")
	public ArrayList<Product> getNonArchivedProducts();
	
	/**Product bought in 15 days*/
	@Query(nativeQuery=true,
			value="SELECT * FROM product WHERE id IN ("
					+ " SELECT re.product_id FROM receipt_entry re, receipt r WHERE"
					+ " re.receipt_id = r.id "
					+ " AND r.date_of_transaction > (:current_time - 14*24*60*60*1000)"
					+ " AND r.buyer_id = :buyer_id)")
	public ArrayList<Product> getProductsBoughtIn15Days(@Param("buyer_id") Integer buyer_id, @Param("current_time") Long current_time);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true,
	value="UPDATE product SET amount_in_stock = amount_in_stock - :amount WHERE id = :id")
	public void updateNumberOfProducts(@Param("id") Integer productId, @Param("amount") Integer amountOfProductsSold);
	
	@Transactional
	@Modifying
	@Query(nativeQuery=true,
	value="UPDATE product SET restock = :restock WHERE id = :id")
	public void setRestockProduct(@Param("restock") Boolean restock, @Param("id") Integer productId);
}
