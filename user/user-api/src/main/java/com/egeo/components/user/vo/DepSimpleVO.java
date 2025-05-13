package com.egeo.components.user.vo;

/**
 * 部门简单vo,在客户端部门分级列表中展示
 * @author GRACIA
 *
 */
public class DepSimpleVO {

	private Long id;
	private String departmentName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	
}
