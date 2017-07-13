package com.djuka.ftn.repositories.product;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.djuka.ftn.model.products.Product;
import com.djuka.ftn.model.products.ProductCategory;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer>{

	@Modifying
	@Transactional
	@Query(nativeQuery=true,
	value="INSERT INTO product_category (id, category_name, max_discount, parent_category_id)"
			+ " VALUES (null, :category_name, :max_discount, :parent_category_id)")
	public void createNewProductCategory(@Param("category_name") String categoryName, @Param("max_discount") Float maxDiscount, @Param("parent_category_id") Integer parentId);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true,
	value="UPDATE product_category "
			+ "SET category_name=:name, max_discount=:max, parent_category_id=:parent WHERE id = :id")
	public void updateProductCategory(@Param("name") String name, @Param("max") Float maxDiscount, @Param("parent") Integer parentId, @Param("id") Integer id);
	
	/**categories for */
	@Query(nativeQuery=true,
			value="SELECT * FROM product_category WHERE id IN (SELECT * FROM product WHERE id IN ("
					+ " SELECT re.product_id FROM receipt_entry re, receipt r WHERE"
					+ " re.receipt_id = r.id "
					+ " AND r.date_of_transaction > (:current_time - 30*24*60*60*1000)"
					+ " AND r.buyer_id = :buyer_id))"
			)
	public ArrayList<Product> getProductsBoughtCategory30Days(@Param("buyer_id") Integer buyer_id, @Param("current_time") Long current_time);
}
