package com.djuka.ftn.controllers.wrappers.adminControlPanel;

//{name:name,parent:parent,perc:perc,id:hiddenId}

public class ProductCategoryWrapper {
	private String name;
	private Integer parent;
	private Float perc;
	public void setPerc(Float perc) {
		this.perc = perc;
	}
	private Integer id;
	public String getName() {
		return name;
	}
	public Float getPerc() {
		return perc;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParent() {
		return parent;
	}
	public void setParent(Integer parent) {
		this.parent = parent;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "ProductCategoryWrapper [name=" + name + ", parent=" + parent + ", perc=" + perc + ", id=" + id + "]";
	}
		
}
