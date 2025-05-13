package com.egeo.components.finance.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 后台分页列表VO
 * 
 * @author jiang
 * @date 2018-01-06 15:25:31
 */
public class AdjustReasonVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 类型
	 */
	private Integer type;

	private String typeName;
	/**
	 * 原因内容
	 */
	private String name;
	/**
	 * 创建时间
	 */
	private String createTime;
	private Integer disabled;
	
	private List<Long> companyIds;

	public List<Long> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 类型
	 * 
	 * @return 类型
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 原因内容
	 * 
	 * @return 原因内容
	 */
	public String getName() {
		return name;
	}

	/**
	 * 原因内容
	 * 
	 * @param name
	 *            原因内容
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

}
