package com.djuka.ftn.controllers.wrappers.adminControlPanel;
//{id:data.id, min:min, max:max, perc:perc}
public class UserWrapper {
	private Integer id;
	private Float min;
	private Float max;
	private Float perc;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Float getMin() {
		return min;
	}
	public void setMin(Float min) {
		this.min = min;
	}
	public Float getMax() {
		return max;
	}
	public void setMax(Float max) {
		this.max = max;
	}
	public Float getPerc() {
		return perc;
	}
	public void setPerc(Float perc) {
		this.perc = perc;
	}
	@Override
	public String toString() {
		return "UserWrapper [id=" + id + ", min=" + min + ", max=" + max + ", perc=" + perc + "]";
	}
		
}
