package com.egeo.components.stock.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-09-13 15:30:19
 */
public class StoreStockChangeApplyPictureDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 门店库存申请id
	 */
	private Long storeStockChangeApplyId;	

	/**
	 * 图片路径
	 */
	private String picturePath;	

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
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 门店库存申请id
	 * @return 门店库存申请id
	 */
	public Long getStoreStockChangeApplyId() {
		return storeStockChangeApplyId;
	}

	/**
	 * 门店库存申请id
	 * @param storeStockChangeApplyId 门店库存申请id
	 */
	public void setStoreStockChangeApplyId(Long storeStockChangeApplyId) {
		this.storeStockChangeApplyId = storeStockChangeApplyId;
	}
	/**
	 * 图片路径
	 * @return 图片路径
	 */
	public String getPicturePath() {
		return picturePath;
	}

	/**
	 * 图片路径
	 * @param picturePath 图片路径
	 */
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
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
	