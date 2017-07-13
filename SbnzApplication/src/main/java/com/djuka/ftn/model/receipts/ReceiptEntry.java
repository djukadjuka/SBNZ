package com.djuka.ftn.model.receipts;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.djuka.ftn.model.products.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ReceiptEntry implements Serializable{

	@Id
	@GeneratedValue
	private Integer receiptEntryId;
	
	@JsonIgnore
	@ManyToOne
	private Receipt receipt;

	@Column
	private Integer entryNumber; //TODO:must be unique in combination with receipt id...
	
	@ManyToOne
	private Product product;
	
	@Column
	private Float priceOfOneOnDay;
	
	@Column
	private Integer numberOfProducts;
	
	@Column
	private Float originalPrice;
	
	@Column
	private Float priceAfterDiscount;
	
	@Column
	private Float totalPrice;
	
	@OneToMany(mappedBy="receiptEntry")
	private Set<EntryDiscount> entryDiscounts;
	
	public Integer getReceiptEntryId() {
		return receiptEntryId;
	}

	public void setReceiptEntryId(Integer receiptEntryId) {
		this.receiptEntryId = receiptEntryId;
	}

	public Set<EntryDiscount> getEntryDiscounts() {
		return entryDiscounts;
	}

	public void setEntryDiscounts(Set<EntryDiscount> entryDiscounts) {
		this.entryDiscounts = entryDiscounts;
	}

	public Integer getEntryNumber() {
		return entryNumber;
	}

	public void setEntryNumber(Integer entryNumber) {
		this.entryNumber = entryNumber;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Float getPriceOfOneOnDay() {
		return priceOfOneOnDay;
	}

	public void setPriceOfOneOnDay(Float priceOfOneOnDay) {
		this.priceOfOneOnDay = priceOfOneOnDay;
	}

	public Integer getNumberOfProducts() {
		return numberOfProducts;
	}

	public void setNumberOfProducts(Integer numberOfProducts) {
		this.numberOfProducts = numberOfProducts;
	}

	public Float getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Float originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Float getPriceAfterDiscount() {
		return priceAfterDiscount;
	}

	public void setPriceAfterDiscount(Float priceAfterDiscount) {
		this.priceAfterDiscount = priceAfterDiscount;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	
	
	@Override
	public String toString() {
		return "ReceiptEntry [receiptEntryId=" + receiptEntryId + ", receipt=" + receipt.getId() + ", entryNumber="
				+ entryNumber + ", numberOfProducts=" + numberOfProducts + ", originalPrice=" + originalPrice
				+ ", priceAfterDiscount=" + priceAfterDiscount + ", totalPrice=" + totalPrice + "]";
	}
	
/*
	public boolean equals(Object o){
		if(o instanceof ReceiptEntry && o != null & ((ReceiptEntry) o).getReceiptEntryId() == this.getReceiptEntryId())
			return true;
		return false;
	}
	
	public int hashCode(){
		return this.getReceiptEntryId();
	}*/
}
