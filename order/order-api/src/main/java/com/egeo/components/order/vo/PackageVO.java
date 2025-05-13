package com.egeo.components.order.vo;

import java.util.List;

/**
 * 客户端订单物流详情包裹VO
 * @author graci
 *
 */
public class PackageVO {

	private String deliveryCompanyName;

	private String waybillNum;

	private String shipCompanyCode;

	private Integer deliveryStatus;

	private String orderCode;	// 母订单编号

	private List<OrderConfirmGoodsVO> goodsList;

	private List<Steps> stepsList;

	/**
	 * 收件地址id
	 */
	private Long receiverAddressId;

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


	public String getDeliveryCompanyName() {
		return deliveryCompanyName;
	}

	public void setDeliveryCompanyName(String deliveryCompanyName) {
		this.deliveryCompanyName = deliveryCompanyName;
	}

	public String getWaybillNum() {
		return waybillNum;
	}

	public void setWaybillNum(String waybillNum) {
		this.waybillNum = waybillNum;
	}

	public String getShipCompanyCode() {
		return shipCompanyCode;
	}

	public void setShipCompanyCode(String shipCompanyCode) {
		this.shipCompanyCode = shipCompanyCode;
	}

	public Integer getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(Integer deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public List<OrderConfirmGoodsVO> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<OrderConfirmGoodsVO> goodsList) {
		this.goodsList = goodsList;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public List<Steps> getStepsList() {
		return stepsList;
	}

	public void setStepsList(List<Steps> stepsList) {
		this.stepsList = stepsList;
	}


	public Long getReceiverAddressId() {
		return receiverAddressId;
	}

	public void setReceiverAddressId(Long receiverAddressId) {
		this.receiverAddressId = receiverAddressId;
	}

	public String getGoodReceiverName() {
		return goodReceiverName;
	}

	public void setGoodReceiverName(String goodReceiverName) {
		this.goodReceiverName = goodReceiverName;
	}

	public String getGoodReceiverMobile() {
		return goodReceiverMobile;
	}

	public void setGoodReceiverMobile(String goodReceiverMobile) {
		this.goodReceiverMobile = goodReceiverMobile;
	}

	public String getProCityArea() {
		return proCityArea;
	}

	public void setProCityArea(String proCityArea) {
		this.proCityArea = proCityArea;
	}

	public String getGoodReceiverAddress() {
		return goodReceiverAddress;
	}

	public void setGoodReceiverAddress(String goodReceiverAddress) {
		this.goodReceiverAddress = goodReceiverAddress;
	}
}
