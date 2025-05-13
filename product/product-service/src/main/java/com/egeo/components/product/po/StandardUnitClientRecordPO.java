package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-07 11:18:56
 */
public class StandardUnitClientRecordPO {


	private Long id;

	/**
	 * su记录id
	 */
	private Long standardUnitRecordId;	

	/**
	 * 客户端id
	 */
	private Long clientId;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
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
	 * 客户端id
	 * @return 客户端id
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * 客户端id
	 * @param clientId 客户端id
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
	