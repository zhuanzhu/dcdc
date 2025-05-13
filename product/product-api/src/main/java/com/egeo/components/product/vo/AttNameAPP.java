package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.List;

public class AttNameAPP implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9129408587134510513L;

	private Long id;
	
	private String name;
	
	private List<ValueAPP> list;

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

	public List<ValueAPP> getList() {
		return list;
	}

	public void setList(List<ValueAPP> list) {
		this.list = list;
	}

}
