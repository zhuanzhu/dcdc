package com.egeo.components.cms.dto;

import java.io.Serializable;

/**
 * 
 * @author min
 * @date 2018-01-29 09:29:45
 */
public class FunctionModuleCategoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 功能模块id
	 */
	private Long functionModuleId;	

	/**
	 * 类目节点id
	 */
	private Long categoryTreeNodeId;	

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 功能模块id
	 * @return 功能模块id
	 */
	public Long getFunctionModuleId() {
		return functionModuleId;
	}

	/**
	 * 功能模块id
	 * @param functionModuleId 功能模块id
	 */
	public void setFunctionModuleId(Long functionModuleId) {
		this.functionModuleId = functionModuleId;
	}
	/**
	 * 类目节点id
	 * @return 类目节点id
	 */
	public Long getCategoryTreeNodeId() {
		return categoryTreeNodeId;
	}

	/**
	 * 类目节点id
	 * @param categoryTreeNodeId 类目节点id
	 */
	public void setCategoryTreeNodeId(Long categoryTreeNodeId) {
		this.categoryTreeNodeId = categoryTreeNodeId;
	}
}
	