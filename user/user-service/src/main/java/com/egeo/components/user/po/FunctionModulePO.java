package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-05-31 17:20:15
 */
public class FunctionModulePO {


	private Long id;

	/**
	 * 父级编号
	 */
	private Long parentId;	

	/**
	 * 
	 */
	private String functionModuleName;	

	/**
	 * 排序
	 */
	private Integer sortValue;	

	/**
	 * 说明
	 */
	private String description;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * 父级编号
	 * @return 父级编号
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 父级编号
	 * @param parentId 父级编号
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 
	 * @return 
	 */
	public String getFunctionModuleName() {
		return functionModuleName;
	}

	/**
	 * 
	 * @param functionModuleName 
	 */
	public void setFunctionModuleName(String functionModuleName) {
		this.functionModuleName = functionModuleName;
	}

	/**
	 * 排序
	 * @return 排序
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序
	 * @param sortValue 排序
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}

	/**
	 * 说明
	 * @return 说明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 说明
	 * @param description 说明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
}
	