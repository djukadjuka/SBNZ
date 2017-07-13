package com.djuka.ftn.services.product;

import java.util.ArrayList;

import com.djuka.ftn.model.products.ProductCategory;

public interface ProductCategoryServices {

	public ArrayList<ProductCategory> getAllCategories();
	public void createNewProductCategory(String categoryName, Float maxDiscount, Integer parentId);
	public void updateProductCategory(String name, Float maxDiscount, Integer parentId, Integer id);
}
