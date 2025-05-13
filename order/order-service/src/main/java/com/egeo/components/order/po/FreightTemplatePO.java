package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class FreightTemplatePO {


	private Long id;

	/**
	 * 
	 */
	private Long merchantId;	

	/**
	 * 运费模板名称
	 */
	private String name;	

	/**
	 * 10:自定义运费; 11:卖家承担运费;
	 */
	private Integer type;	

	/**
	 * 计费方式  10:按件数; 11:按重量; 12:按体积; 13:按金额;
	 */
	private Integer chargeWay;	

	/**
	 * 是否默认   1:默认;  0:非默认;
	 */
	private Integer isDft;	

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
	 * 运费模板名称
	 * @return 运费模板名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 运费模板名称
	 * @param name 运费模板名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 10:自定义运费; 11:卖家承担运费;
	 * @return 10:自定义运费; 11:卖家承担运费;
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 10:自定义运费; 11:卖家承担运费;
	 * @param type 10:自定义运费; 11:卖家承担运费;
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 计费方式  10:按件数; 11:按重量; 12:按体积; 13:按金额;
	 * @return 计费方式  10:按件数; 11:按重量; 12:按体积; 13:按金额;
	 */
	public Integer getChargeWay() {
		return chargeWay;
	}

	/**
	 * 计费方式  10:按件数; 11:按重量; 12:按体积; 13:按金额;
	 * @param chargeWay 计费方式  10:按件数; 11:按重量; 12:按体积; 13:按金额;
	 */
	public void setChargeWay(Integer chargeWay) {
		this.chargeWay = chargeWay;
	}

	/**
	 * 是否默认   1:默认;  0:非默认;
	 * @return 是否默认   1:默认;  0:非默认;
	 */
	public Integer getIsDft() {
		return isDft;
	}

	/**
	 * 是否默认   1:默认;  0:非默认;
	 * @param isDft 是否默认   1:默认;  0:非默认;
	 */
	public void setIsDft(Integer isDft) {
		this.isDft = isDft;
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
	