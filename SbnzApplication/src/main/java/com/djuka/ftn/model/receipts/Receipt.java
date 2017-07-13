package com.djuka.ftn.model.receipts;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.djuka.ftn.model.users.buyer.Buyer;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Receipt implements Serializable{

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private Long dateOfTransaction;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="buyer_id")
	private Buyer buyer;
	
	@Column
	private Integer receiptState;
	
	@Column
	private Float initalPrice;
	
	@Column
	private Float discountPercentage;
	
	@Column
	private Float finalPrice;
	
	@Column
	private Integer spentPoints;
	
	@Column 
	private Integer aquiredPoints;
	
	@OneToMany(mappedBy="receipt")
	private Set<ReceiptEntry> receiptEntries;

	@JsonIgnore
	@OneToMany(mappedBy="receipt")
	private Set<FullDiscount> fullDiscounts;
	
	public Set<FullDiscount> getFullDiscounts() {
		return fullDiscounts;
	}

	public void setFullDiscounts(Set<FullDiscount> fullDiscounts) {
		this.fullDiscounts = fullDiscounts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Long dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public Integer getReceiptState() {
		return receiptState;
	}

	public void setReceiptState(Integer receiptState) {
		this.receiptState = receiptState;
	}

	public Float getInitalPrice() {
		return initalPrice;
	}

	public void setInitalPrice(Float initalPrice) {
		this.initalPrice = initalPrice;
	}

	public Float getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Float discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Float getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(Float finalPrice) {
		this.finalPrice = finalPrice;
	}

	public Integer getSpentPoints() {
		return spentPoints;
	}

	public void setSpentPoints(Integer spentPoints) {
		this.spentPoints = spentPoints;
	}

	public Integer getAquiredPoints() {
		return aquiredPoints;
	}

	public void setAquiredPoints(Integer aquiredPoints) {
		this.aquiredPoints = aquiredPoints;
	}

	public Set<ReceiptEntry> getReceiptEntries() {
		return receiptEntries;
	}

	public void setReceiptEntries(Set<ReceiptEntry> receiptEntries) {
		this.receiptEntries = receiptEntries;
	}
	
	
	
	@Override
	public String toString() {
		return "Receipt [id=" + id + ", dateOfTransaction=" + dateOfTransaction + ", buyer=" + buyer + ", receiptState="
				+ receiptState + ", initalPrice=" + initalPrice + ", discountPercentage=" + discountPercentage
				+ ", finalPrice=" + finalPrice + ", spentPoints=" + spentPoints + ", aquiredPoints=" + aquiredPoints
				+ ", receiptEntries=" + receiptEntries + ", fullDiscounts=" + fullDiscounts + "]";
	}

	public int hashCode(){
		return this.id;
	}
	
	public boolean equals(Object o){
		if(o instanceof Receipt && o != null && ((Receipt) o).getId()==this.id)
			return true;
		return false;
	}
}
