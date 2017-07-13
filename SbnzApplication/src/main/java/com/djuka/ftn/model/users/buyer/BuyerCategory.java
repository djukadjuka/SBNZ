package com.djuka.ftn.model.users.buyer;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BuyerCategory implements Serializable{

	@Id
	@GeneratedValue
	private Integer buyerCategoryId;
	
	@Column(nullable=false)
	private String categoryName;
	
	@Column(nullable=false)
	private Float spendingBoundMax;
	
	@Column(nullable=false)
	private Float spendingBoundMin;
	
	@Column(nullable=false)
	private Float pointsPercentage;

	@JsonIgnore
	@OneToMany(mappedBy="buyerCategory")
	private Set<Buyer> buyers;
	
	public Set<Buyer> getBuyers() {
		return buyers;
	}

	public void setBuyers(Set<Buyer> buyers) {
		this.buyers = buyers;
	}

	public Integer getId() {
		return buyerCategoryId;
	}

	public void setId(Integer id) {
		this.buyerCategoryId = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Float getSpendingBoundMax() {
		return spendingBoundMax;
	}

	public void setSpendingBoundMax(Float spedingBoundMax) {
		this.spendingBoundMax = spedingBoundMax;
	}

	public Float getSpendingBoundMin() {
		return spendingBoundMin;
	}

	public void setSpendingBoundMin(Float spendingBoundMin) {
		this.spendingBoundMin = spendingBoundMin;
	}

	public Float getPointsPercentage() {
		return pointsPercentage;
	}

	public void setPointsPercentage(Float pointsPercentage) {
		this.pointsPercentage = pointsPercentage;
	}
	
	@Override
	public String toString(){
		return "Id:["+this.buyerCategoryId+"], CatName:["+this.categoryName+"], Max:["+this.spendingBoundMax+"], Min:["+this.spendingBoundMin+"], Percentage:["+this.pointsPercentage+"%]";
	}
	
	@Override
	public int hashCode(){
		return this.buyerCategoryId;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof BuyerCategory && o != null && ((BuyerCategory) o).getId() == this.buyerCategoryId)
			return true;
		return false;
	}
}
