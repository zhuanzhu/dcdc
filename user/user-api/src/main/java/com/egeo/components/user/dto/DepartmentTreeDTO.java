package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.List;

public class DepartmentTreeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 部门名称
	 */
	private String name;
	
	/**
	 * 父部门名称
	 */
	private String pName;
	
	/**
	 * 子部门
	 */
	private List<DepartmentTreeDTO> childs;
	
	

	public DepartmentTreeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public DepartmentTreeDTO(String name, String pName, List<DepartmentTreeDTO> childs) {
		super();
		this.name = name;
		this.pName = pName;
		this.childs = childs;
	}
	
	
	public DepartmentTreeDTO(String name, String pName) {
		super();
		this.name = name;
		this.pName = pName;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public List<DepartmentTreeDTO> getChilds() {
		return childs;
	}

	public void setChilds(List<DepartmentTreeDTO> childs) {
		this.childs = childs;
	}
	
	

}
