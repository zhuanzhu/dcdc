package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-09-14 15:19:51
 */
public class AddressPO {


	private Long id;

	/**
	 * 地址名称
	 */
	private String addressName;	

	/**
	 * 父类id
	 */
	private Long pid;	

	/**
	 * 排序
	 */
	private Integer sortValue;	

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
	 * 地址名称
	 * @return 地址名称
	 */
	public String getAddressName() {
		return addressName;
	}

	/**
	 * 地址名称
	 * @param addressName 地址名称
	 */
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	/**
	 * 父类id
	 * @return 父类id
	 */
	public Long getPid() {
		return pid;
	}

	/**
	 * 父类id
	 * @param pid 父类id
	 */
	public void setPid(Long pid) {
		this.pid = pid;
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
	