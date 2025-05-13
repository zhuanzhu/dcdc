package com.egeo.components.product.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class MerchantProductArticlesTaxPO {


	private Long id;

	/**
	 * 税号
	 */
	private Long taxNumber;	

	/**
	 * 商品说明
	 */
	private BigDecimal merchantExplain;	

	/**
	 * 商品范围
	 */
	private String merchantRange;	

	/**
	 * 税率
	 */
	private BigDecimal taxRale;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * 税号
	 * @return 税号
	 */
	public Long getTaxNumber() {
		return taxNumber;
	}

	/**
	 * 税号
	 * @param taxNumber 税号
	 */
	public void setTaxNumber(Long taxNumber) {
		this.taxNumber = taxNumber;
	}

	/**
	 * 商品说明
	 * @return 商品说明
	 */
	public BigDecimal getMerchantExplain() {
		return merchantExplain;
	}

	/**
	 * 商品说明
	 * @param merchantExplain 商品说明
	 */
	public void setMerchantExplain(BigDecimal merchantExplain) {
		this.merchantExplain = merchantExplain;
	}

	/**
	 * 商品范围
	 * @return 商品范围
	 */
	public String getMerchantRange() {
		return merchantRange;
	}

	/**
	 * 商品范围
	 * @param merchantRange 商品范围
	 */
	public void setMerchantRange(String merchantRange) {
		this.merchantRange = merchantRange;
	}

	/**
	 * 税率
	 * @return 税率
	 */
	public BigDecimal getTaxRale() {
		return taxRale;
	}

	/**
	 * 税率
	 * @param taxRale 税率
	 */
	public void setTaxRale(BigDecimal taxRale) {
		this.taxRale = taxRale;
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
	