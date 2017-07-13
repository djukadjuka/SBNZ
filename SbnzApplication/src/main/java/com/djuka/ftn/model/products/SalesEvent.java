package com.djuka.ftn.model.products;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class SalesEvent implements Serializable{

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String eventName;
	
	@Column
	private Long startDate;
	
	@Column
	private Long endDate;
	
	@Column
	private Float eventDiscount;
	
	@JsonIgnore
	@OneToMany(mappedBy="salesEvent")
	private Set<ProductCategoryRelation> relation;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public Float getEventDiscount() {
		return eventDiscount;
	}

	public void setEventDiscount(Float eventDiscount) {
		this.eventDiscount = eventDiscount;
	}

	public Set<ProductCategoryRelation> getRelation() {
		return relation;
	}

	public void setRelation(Set<ProductCategoryRelation> relation) {
		this.relation = relation;
	}

	@Override
	public String toString() {
		return "SalesEvent [id=" + id + ", eventName=" + eventName + "]";
	}
	
	
}
