package com.djuka.ftn.controllers.wrappers.shopping;

//var payload = {productId:prod.id, productAmount:vl};
public class AddToCartWrapper {
	private Integer productId;
	private Integer productAmount;
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
	@Override
	public String toString() {
		return "AddToCartWrapper [productId=" + productId + ", productAmount=" + productAmount + "]";
	}
	
}
