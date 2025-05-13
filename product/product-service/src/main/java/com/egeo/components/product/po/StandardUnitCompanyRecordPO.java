package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-07 11:18:56
 */
public class StandardUnitCompanyRecordPO {


	private Long id;

	/**
	 * su记录id
	 */
	private Long standardUnitRecordId;	

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	
	/**
	 * 公司类型 0:正式公司 1:测试公司 2:竞品公司
	 */
	private Integer companyType;

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

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
	 * su记录id
	 * @return su记录id
	 */
	public Long getStandardUnitRecordId() {
		return standardUnitRecordId;
	}

	/**
	 * su记录id
	 * @param standardUnitRecordId su记录id
	 */
	public void setStandardUnitRecordId(Long standardUnitRecordId) {
		this.standardUnitRecordId = standardUnitRecordId;
	}

	/**
	 * 公司id
	 * @return 公司id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 公司id
	 * @param companyId 公司id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
}
	