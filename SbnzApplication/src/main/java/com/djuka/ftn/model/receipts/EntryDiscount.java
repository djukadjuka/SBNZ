package com.djuka.ftn.model.receipts;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class EntryDiscount implements Serializable{

	@Id
	@GeneratedValue
	private Integer entryDiscountId;
	
	@JsonIgnore
	@ManyToOne
	private ReceiptEntry receiptEntry;
	
	@Column
	private Float discrountPercentage;
	
	@Column
	private Boolean initialDiscount;

	public Integer getEntryDiscountId() {
		return entryDiscountId;
	}

	public void setEntryDiscountId(Integer entryDiscountId) {
		this.entryDiscountId = entryDiscountId;
	}

	public ReceiptEntry getReceiptEntry() {
		return receiptEntry;
	}

	public void setReceiptEntry(ReceiptEntry receiptEntry) {
		this.receiptEntry = receiptEntry;
	}

	public Float getDiscrountPercentage() {
		return discrountPercentage;
	}

	public void setDiscrountPercentage(Float discrountPercentage) {
		this.discrountPercentage = discrountPercentage;
	}

	public Boolean getInitialDiscount() {
		return initialDiscount;
	}

	public void setInitialDiscount(Boolean initialDiscount) {
		this.initialDiscount = initialDiscount;
	}

	@Override
	public String toString() {
		return "EntryDiscount [entryDiscountId=" + entryDiscountId + ", receiptEntry=" + receiptEntry
				+ ", discrountPercentage=" + discrountPercentage + ", initialDiscount=" + initialDiscount + "]";
	}
	
}
