package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author ghw
 * @date 2018-02-02 11:28:22
 */
public class SoPackageTempVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 子订单id
	 */
	private Long soChildId;
	/**
	 * 收货人姓名
	 */
	private String goodReceiverName;
	/**
	 * 收货人手机
	 */
	private String goodReceiverMobile;
	/**
	 * 省市区信息
	 */
	private String proCityArea;
	/**
	 * 地址详情
	 */
	private String goodReceiverAddress;
	/**
	 * 配送日期
	 */
	private String deliveryDate;
	/**
	 * 配送人姓名
	 */
	private String deliveryName;
	/**
	 * 配送人电话
	 */
	private String deliveryNameMobile;
	/**
	 * 签收人姓名
	 */
	private String signName;
	/**
	 * 
	 */
	private String signDate;
	/**
	 * 签收备注
	 */
	private String signRemark;
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
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 子订单id
	 * @return 子订单id
	 */
	public Long getSoChildId() {
		return soChildId;
	}

	/**
	 * 子订单id
	 * @param soChildId 子订单id
	 */
	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}	
	/**
	 * 收货人姓名
	 * @return 收货人姓名
	 */
	public String getGoodReceiverName() {
		return goodReceiverName;
	}

	/**
	 * 收货人姓名
	 * @param goodReceiverName 收货人姓名
	 */
	public void setGoodReceiverName(String goodReceiverName) {
		this.goodReceiverName = goodReceiverName;
	}	
	/**
	 * 收货人手机
	 * @return 收货人手机
	 */
	public String getGoodReceiverMobile() {
		return goodReceiverMobile;
	}

	/**
	 * 收货人手机
	 * @param goodReceiverMobile 收货人手机
	 */
	public void setGoodReceiverMobile(String goodReceiverMobile) {
		this.goodReceiverMobile = goodReceiverMobile;
	}	
	/**
	 * 省市区信息
	 * @return 省市区信息
	 */
	public String getProCityArea() {
		return proCityArea;
	}

	/**
	 * 省市区信息
	 * @param proCityArea 省市区信息
	 */
	public void setProCityArea(String proCityArea) {
		this.proCityArea = proCityArea;
	}	
	/**
	 * 地址详情
	 * @return 地址详情
	 */
	public String getGoodReceiverAddress() {
		return goodReceiverAddress;
	}

	/**
	 * 地址详情
	 * @param goodReceiverAddress 地址详情
	 */
	public void setGoodReceiverAddress(String goodReceiverAddress) {
		this.goodReceiverAddress = goodReceiverAddress;
	}	
	/**
	 * 配送人姓名
	 * @return 配送人姓名
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * 配送人姓名
	 * @param deliveryName 配送人姓名
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}	
	/**
	 * 配送人电话
	 * @return 配送人电话
	 */
	public String getDeliveryNameMobile() {
		return deliveryNameMobile;
	}

	/**
	 * 配送人电话
	 * @param deliveryNameMobile 配送人电话
	 */
	public void setDeliveryNameMobile(String deliveryNameMobile) {
		this.deliveryNameMobile = deliveryNameMobile;
	}	
	/**
	 * 签收人姓名
	 * @return 签收人姓名
	 */
	public String getSignName() {
		return signName;
	}

	/**
	 * 签收人姓名
	 * @param signName 签收人姓名
	 */
	public void setSignName(String signName) {
		this.signName = signName;
	}	
	/**
	 * 签收备注
	 * @return 签收备注
	 */
	public String getSignRemark() {
		return signRemark;
	}

	/**
	 * 签收备注
	 * @param signRemark 签收备注
	 */
	public void setSignRemark(String signRemark) {
		this.signRemark = signRemark;
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

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public String getSignDate() {
		return signDate;
	}


}
	