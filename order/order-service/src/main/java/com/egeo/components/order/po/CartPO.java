package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-17 17:35:12
 */
public class CartPO {


	private Long id;
	private Long storeId;
	private Integer saleWay;

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getSaleWay() {
		return saleWay;
	}

	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}

	/**
	 * 用户id
	 */
	private Long userid;	

	/**
	 * 
	 */
	private String sessionid;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 客户端id
	 */
	private Long clientId;	

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
	 * 用户id
	 * @return 用户id
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * 用户id
	 * @param userid 用户id
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * 
	 * @return 
	 */
	public String getSessionid() {
		return sessionid;
	}

	/**
	 * 
	 * @param sessionid 
	 */
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
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
	