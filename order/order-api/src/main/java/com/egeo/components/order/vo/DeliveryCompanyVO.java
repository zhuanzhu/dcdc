package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-08-18 15:45:02
 */
public class DeliveryCompanyVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 快递公司名称
	 */

	private String name;		 
	/**
	 * 编码
	 */

	private String coding;		 
	/**
	 * 轨迹查询（0：否、1：是）
	 */

	private Integer trajectoryQuerying;		 
	/**
	 * 电子面单（0：否、1：是）
	 */

	private Integer electronicSurface;		 
	/**
	 * 预约取件（0：否、1：是）
	 */

	private Integer pickUp;		 
	/**
	 * 修改时间:更新时数据库会自动set值
	 */

	private Date updateTime;		 
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
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 快递公司名称
	 * @return 快递公司名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 快递公司名称
	 * @param name 快递公司名称
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 编码
	 * @return 编码
	 */
	public String getCoding() {
		return coding;
	}

	/**
	 * 编码
	 * @param coding 编码
	 */
	public void setCoding(String coding) {
		this.coding = coding;
	}	
	/**
	 * 轨迹查询（0：否、1：是）
	 * @return 轨迹查询（0：否、1：是）
	 */
	public Integer getTrajectoryQuerying() {
		return trajectoryQuerying;
	}

	/**
	 * 轨迹查询（0：否、1：是）
	 * @param trajectoryQuerying 轨迹查询（0：否、1：是）
	 */
	public void setTrajectoryQuerying(Integer trajectoryQuerying) {
		this.trajectoryQuerying = trajectoryQuerying;
	}	
	/**
	 * 电子面单（0：否、1：是）
	 * @return 电子面单（0：否、1：是）
	 */
	public Integer getElectronicSurface() {
		return electronicSurface;
	}

	/**
	 * 电子面单（0：否、1：是）
	 * @param electronicSurface 电子面单（0：否、1：是）
	 */
	public void setElectronicSurface(Integer electronicSurface) {
		this.electronicSurface = electronicSurface;
	}	
	/**
	 * 预约取件（0：否、1：是）
	 * @return 预约取件（0：否、1：是）
	 */
	public Integer getPickUp() {
		return pickUp;
	}

	/**
	 * 预约取件（0：否、1：是）
	 * @param pickUp 预约取件（0：否、1：是）
	 */
	public void setPickUp(Integer pickUp) {
		this.pickUp = pickUp;
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
	