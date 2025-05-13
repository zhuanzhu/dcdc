package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class FreightTemplateItemPO {


	private Long id;

	/**
	 * 
	 */
	private Long merchantId;	

	/**
	 * 
	 */
	private Long freightTemplateId;	

	/**
	 * 
	 */
	private String distributionCode;	

	/**
	 * 类型区分 {1:基本配送方式; 2:指定条件包邮; }
	 */
	private Integer type;	

	/**
	 * 计费方式 包邮条件 { 10:按件数; 11:按重量; 12:按体积;  20:重量和金额; 21:件数和金额; 22:体积和金额;}
	 */
	private Integer chargeWay;	

	/**
	 * 针对每个配送方式的 运费的规则
	 */
	private String freightRule;	

	/**
	 * 配送区域
	 */
	private String distributionRegion;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

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
	 * 
	 * @return 
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 
	 * @param merchantId 
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getFreightTemplateId() {
		return freightTemplateId;
	}

	/**
	 * 
	 * @param freightTemplateId 
	 */
	public void setFreightTemplateId(Long freightTemplateId) {
		this.freightTemplateId = freightTemplateId;
	}

	/**
	 * 
	 * @return 
	 */
	public String getDistributionCode() {
		return distributionCode;
	}

	/**
	 * 
	 * @param distributionCode 
	 */
	public void setDistributionCode(String distributionCode) {
		this.distributionCode = distributionCode;
	}

	/**
	 * 类型区分 {1:基本配送方式; 2:指定条件包邮; }
	 * @return 类型区分 {1:基本配送方式; 2:指定条件包邮; }
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型区分 {1:基本配送方式; 2:指定条件包邮; }
	 * @param type 类型区分 {1:基本配送方式; 2:指定条件包邮; }
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 计费方式 包邮条件 { 10:按件数; 11:按重量; 12:按体积;  20:重量和金额; 21:件数和金额; 22:体积和金额;}
	 * @return 计费方式 包邮条件 { 10:按件数; 11:按重量; 12:按体积;  20:重量和金额; 21:件数和金额; 22:体积和金额;}
	 */
	public Integer getChargeWay() {
		return chargeWay;
	}

	/**
	 * 计费方式 包邮条件 { 10:按件数; 11:按重量; 12:按体积;  20:重量和金额; 21:件数和金额; 22:体积和金额;}
	 * @param chargeWay 计费方式 包邮条件 { 10:按件数; 11:按重量; 12:按体积;  20:重量和金额; 21:件数和金额; 22:体积和金额;}
	 */
	public void setChargeWay(Integer chargeWay) {
		this.chargeWay = chargeWay;
	}

	/**
	 * 针对每个配送方式的 运费的规则
	 * @return 针对每个配送方式的 运费的规则
	 */
	public String getFreightRule() {
		return freightRule;
	}

	/**
	 * 针对每个配送方式的 运费的规则
	 * @param freightRule 针对每个配送方式的 运费的规则
	 */
	public void setFreightRule(String freightRule) {
		this.freightRule = freightRule;
	}

	/**
	 * 配送区域
	 * @return 配送区域
	 */
	public String getDistributionRegion() {
		return distributionRegion;
	}

	/**
	 * 配送区域
	 * @param distributionRegion 配送区域
	 */
	public void setDistributionRegion(String distributionRegion) {
		this.distributionRegion = distributionRegion;
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
}
	