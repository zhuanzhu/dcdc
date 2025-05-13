package com.egeo.components.user.vo;

import java.util.List;

/**
 * 安卓部门树vo
 * @author GRACIA
 *
 */
public class DepSimpleVOForAndroid {

	private Long id;
	private String name;
	private List<DepSimpleVOForAndroid> children;
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
	public List<DepSimpleVOForAndroid> getChildren() {
		return children;
	}
	public void setChildren(List<DepSimpleVOForAndroid> children) {
		this.children = children;
	}


	
	
	
}
