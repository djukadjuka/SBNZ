package com.djuka.ftn.model.users.buyer;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.djuka.ftn.model.receipts.Receipt;
import com.djuka.ftn.model.users.BaseUser;

@Entity
public class Buyer implements Serializable{

	@Column
	private String address;
	
	@Column
	private Integer rewardPoints;
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	public BaseUser user;

	@ManyToOne
	@JoinColumn(name="b_category_id")
	private BuyerCategory buyerCategory;
	
	@OneToMany(mappedBy="buyer")
	private Set<Receipt> receipts;
	
	public Set<Receipt> getReceipts() {
		return receipts;
	}

	public void setReceipts(Set<Receipt> receipts) {
		this.receipts = receipts;
	}

	public BuyerCategory getBuyerCategory() {
		return buyerCategory;
	}

	public void setBuyerCategory(BuyerCategory buyerCategory) {
		this.buyerCategory = buyerCategory;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BaseUser getUser() {
		return user;
	}

	public void setUser(BaseUser user) {
		this.user = user;
	}
	
	@Override
	public String toString(){
		return user + ", Address["+this.address+"], RewardPoints:["+this.rewardPoints+"]";
	}
	
	@Override
	public boolean equals(Object o ){
		if(o instanceof Buyer && o != null && ((Buyer) o).getId() == this.id)
			return true;
		return false;
	}
}
