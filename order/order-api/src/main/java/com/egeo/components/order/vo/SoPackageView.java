package com.egeo.components.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用于后台订单详情物流信息页面的列表展示
 * @author graci
 *
 */
public class SoPackageView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 运单id
	 */
	private Long id;

	/**
	 * 子订单id
	 */
	private Long soChildId;
	
	private String childCode;
	/**
	 * 
	 */
	private String deliveryCompanyName;
	
	/**
	 * 快递单号
	 */
	private String deliveryCode;
	
	/**
	 * 箱号
	 */
	private String soBoxCode;	
	
	/**
	 * 订单物流信息 0:待发货，1:分拣中，2:已发货 3：已签收
	 */
	private Integer deliveryStatus;
	
	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	
	/**
	 * 子单运费
	 */
	private BigDecimal soChildDeliveryFee;
	
	/**
	 * 是否已有物流信息
	 */
	private boolean packageExist;
	
	

	public boolean isPackageExist() {
		return packageExist;
	}

	public void setPackageExist(boolean packageExist) {
		this.packageExist = packageExist;
	}

	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSoChildId() {
		return soChildId;
	}

	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}

	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}

	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}

	public String getDeliveryCode() {
		return deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}


	public String getSoBoxCode() {
		return soBoxCode;
	}

	public void setSoBoxCode(String soBoxCode) {
		this.soBoxCode = soBoxCode;
	}

	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public BigDecimal getSoChildDeliveryFee() {
		return soChildDeliveryFee;
	}

	public void setSoChildDeliveryFee(BigDecimal soChildDeliveryFee) {
		this.soChildDeliveryFee = soChildDeliveryFee;
	}
	

}
