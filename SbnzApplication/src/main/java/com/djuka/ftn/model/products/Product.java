package com.djuka.ftn.model.products;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private Float price;
	
	@Column
	private Integer amountInStock;
	
	@Column
	private Long dateCreated;
	
	@Column
	private Boolean restock;
	
	@Column
	private Boolean archived;
	
	@Column
	private Integer minAmountInStock;
	
	@ManyToOne
	@JoinColumn(name="p_category_id")
	private ProductCategory productCategory;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getAmountInStock() {
		return amountInStock;
	}

	public void setAmountInStock(Integer amountInStock) {
		this.amountInStock = amountInStock;
	}

	public Long getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Boolean getRestock() {
		return restock;
	}

	public void setRestock(Boolean restock) {
		this.restock = restock;
	}

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

	public Integer getMinAmountInStock() {
		return minAmountInStock;
	}

	public void setMinAmountInStock(Integer minAmountInStock) {
		this.minAmountInStock = minAmountInStock;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}
	
	@Override
	public String toString(){
		return "Id:["+this.id+"], InStock:["+this.amountInStock+"], RefillOn:["+this.minAmountInStock+"], Archived:["+this.archived+"], Created:["+this.dateCreated+"], Price:["+this.price+"]";
	}
	
	@Override
	public int hashCode(){
		return this.id;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Product && o != null && ((Product) o).getId() == this.id)
			return true;
		return false;
	}
}
