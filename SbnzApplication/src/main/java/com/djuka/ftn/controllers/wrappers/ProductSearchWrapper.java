package com.djuka.ftn.controllers.wrappers;

public class ProductSearchWrapper {
	private Float minPrice;
	private Float maxPrice;
	private String categoryName;
	private String productName;
	private Integer productCode;
	public Float getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}
	public Float getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getProductCode() {
		return productCode;
	}
	public void setProductCode(Integer productCode) {
		this.productCode = productCode;
	}
	
	public String toString(){
		return "Code ["+this.productCode+"], PName ["+this.productName+"], CName ["+this.categoryName+"], min ["+this.minPrice+"],max ["+this.maxPrice+"]";
	}
}
