package com.djuka.ftn.services.product;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djuka.ftn.model.products.Product;
import com.djuka.ftn.repositories.product.ProductRepository;

@Service
public class ProductServicesImpl implements ProductServices {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ArrayList<Product> getAllProducts(){
		return (ArrayList<Product>) productRepository.findAll();
	}

	@Override
	public ArrayList<Product> getAllAvailableProducts() {
		return productRepository.getAllAvailableProducts();
	}

	@Override
	public Product getOneProduct(Integer id) {
		return productRepository.findOne(id);
	}

	@Override
	public void updateProductAmount(Integer productId, Integer amountSold) {
		productRepository.updateNumberOfProducts(productId, amountSold);
	}

	@Override
	public void setRestockOfProduct(Boolean restock, Integer productId) {
		this.productRepository.setRestockProduct(restock, productId);
	}

	@Override
	public ArrayList<Product> getNonArchivedProducts() {
		return productRepository.getNonArchivedProducts();
	}
}
