package com.djuka.ftn.model.receipts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FullDiscount implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	private Receipt receipt;
	
	@Column
	private Float discountPercentage;
	
	@Column
	private Boolean initialDiscount;

	public FullDiscount(){};
	
	public FullDiscount(Boolean initial, Float percentage){
		this.discountPercentage = percentage;
		this.initialDiscount = initial;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Float getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Boolean getInitialDiscount() {
		return initialDiscount;
	}

	public void setInitialDiscount(Boolean initialDiscount) {
		this.initialDiscount = initialDiscount;
	}

	@Override
	public String toString() {
		return "FullDiscount [id=" + id +  ", discountPercentage=" + discountPercentage
				+ ", initialDiscount=" + initialDiscount + "]";
	}
	
	
}
