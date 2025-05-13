package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.List;

public class AttName implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9129408587134510513L;

	private Long id;
	
	private String name;
	
	private List<Value> list;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Value> getList() {
		return list;
	}

	public void setList(List<Value> list) {
		this.list = list;
	}

}
