package com.djuka.ftn.model.products;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ProductCategory implements Serializable{

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String categoryName;
	
	@ManyToOne
	@JoinColumn(name="parent_category_id")
	private ProductCategory parentCategory;
	
	@JsonIgnore
	@OneToMany(mappedBy="parentCategory")
	private Set<ProductCategory> childCategories;
	
	@Column
	private Float maxDiscount;

	@JsonIgnore
	@OneToMany(mappedBy="productCategory")
	private Set<Product> products;
	
	@OneToMany(mappedBy="productCategory", fetch=FetchType.EAGER)
	private Set<ProductCategoryRelation> relation;
	
	public Set<ProductCategoryRelation> getRelation() {
		return relation;
	}

	public void setRelation(Set<ProductCategoryRelation> relation) {
		this.relation = relation;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ProductCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(ProductCategory parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<ProductCategory> getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(Set<ProductCategory> childCategories) {
		this.childCategories = childCategories;
	}

	public Float getMaxDiscount() {
		return maxDiscount;
	}

	public void setMaxDiscount(Float maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	
	@Override
	public String toString(){
		return "Id:["+this.id+"], Name:["+this.categoryName+"], Parent:["+this.parentCategory+"], MaxDiscount:["+this.maxDiscount+"]";
	}
	
	@Override
	public int hashCode(){
		return this.id;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof ProductCategory && o != null && ((ProductCategory) o).getId() == this.id)
			return true;
		return false;
	}
}
