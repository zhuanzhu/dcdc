package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-05 19:55:46
 */
public class StandardProductUnitDescriptionRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * spu草稿记录Id
	 */
	private Long standardProductUnitRecordId;	

	/**
	 * 产品描述
	 */
	private String content;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * spu草稿记录Id
	 * @return spu草稿记录Id
	 */
	public Long getStandardProductUnitRecordId() {
		return standardProductUnitRecordId;
	}

	/**
	 * spu草稿记录Id
	 * @param standardProductUnitRecordId spu草稿记录Id
	 */
	public void setStandardProductUnitRecordId(Long standardProductUnitRecordId) {
		this.standardProductUnitRecordId = standardProductUnitRecordId;
	}
	/**
	 * 产品描述
	 * @return 产品描述
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 产品描述
	 * @param content 产品描述
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	