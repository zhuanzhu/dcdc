package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xia
 * @date 2018-08-30 17:15:01
 */
public class PayTypeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 支付方式code(1 支付宝 2 微信 3 银联 4 建行 )
	 */
	private Integer payTypeCode;	

	/**
	 * 支付方式名(1 支付宝 2 微信 3 银联 4 建行 )
	 */
	private String payTypeName;	

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
	private String logImageUrl;
	private String payTypeRemarks;

	public String getLogImageUrl() {
		return logImageUrl;
	}

	public void setLogImageUrl(String logImageUrl) {
		this.logImageUrl = logImageUrl;
	}

	public String getPayTypeRemarks() {
		return payTypeRemarks;
	}

	public void setPayTypeRemarks(String payTypeRemarks) {
		this.payTypeRemarks = payTypeRemarks;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 支付方式code(1 支付宝 2 微信 3 银联 4 建行 )
	 * @return 支付方式code(1 支付宝 2 微信 3 银联 4 建行 )
	 */
	public Integer getPayTypeCode() {
		return payTypeCode;
	}

	/**
	 * 支付方式code(1 支付宝 2 微信 3 银联 4 建行 )
	 * @param payTypeCode 支付方式code(1 支付宝 2 微信 3 银联 4 建行 )
	 */
	public void setPayTypeCode(Integer payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	/**
	 * 支付方式名(1 支付宝 2 微信 3 银联 4 建行 )
	 * @return 支付方式名(1 支付宝 2 微信 3 银联 4 建行 )
	 */
	public String getPayTypeName() {
		return payTypeName;
	}

	/**
	 * 支付方式名(1 支付宝 2 微信 3 银联 4 建行 )
	 * @param payTypeName 支付方式名(1 支付宝 2 微信 3 银联 4 建行 )
	 */
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
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
	