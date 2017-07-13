package com.djuka.ftn.model.products;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductCategoryRelation implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(referencedColumnName="id", name="event_id")
	private SalesEvent salesEvent;
	
	@JsonIgnore
	@Id
	@ManyToOne
	@JoinColumn(referencedColumnName="id", name="product_category_id")
	private ProductCategory productCategory;

	public SalesEvent getSalesEvent() {
		return salesEvent;
	}

	public void setSalesEvent(SalesEvent salesEvent) {
		this.salesEvent = salesEvent;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}
