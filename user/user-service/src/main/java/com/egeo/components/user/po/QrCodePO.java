package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author xia
 * @date 2018-09-13 17:09:31
 */
public class QrCodePO {


	private Long id;

	/**
	 * 店铺id
	 */
	private Long branchId;	

	/**
	 * 客户端id
	 */
	private Long clientId;	

	/**
	 * 渠道id
	 */
	private Long channelId;	

	/**
	 * 活动id
	 */
	private Long campaignId;	

	/**
	 * id
	 */
	private Long typeId;	

	/**
	 * 随机id
	 */
	private String rdid;

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

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
	 * 店铺id
	 * @return 店铺id
	 */
	public Long getBranchId() {
		return branchId;
	}

	/**
	 * 店铺id
	 * @param branchId 店铺id
	 */
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
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
	 * 渠道id
	 * @return 渠道id
	 */
	public Long getChannelId() {
		return channelId;
	}

	/**
	 * 渠道id
	 * @param channelId 渠道id
	 */
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	/**
	 * 活动id
	 * @return 活动id
	 */
	public Long getCampaignId() {
		return campaignId;
	}

	/**
	 * 活动id
	 * @param campaignId 活动id
	 */
	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * id
	 * @return id
	 */
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * id
	 * @param typeId id
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * 随机id
	 * @return 随机id
	 */
	public String getRdid() {
		return rdid;
	}

	/**
	 * 随机id
	 * @param rdid 随机id
	 */
	public void setRdid(String rdid) {
		this.rdid = rdid;
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
}
	