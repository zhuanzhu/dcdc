package com.egeo.components.stock.fo;

import java.util.List;

import com.egeo.components.product.dto.CommodityProductUnitDTO;

public class UnfreezeAndDeductStockBatchWithTxFO {
	private Long puId;
	private Integer puCount;
	private Integer type;
	private  String orderCode;
	private  Long userId;
	private  String userName;
	private  String ip;
	private  String mac;
	private  List<Long> puIds;
	private  List<CommodityProductUnitDTO> commodityProductUnitDTOs;
	public UnfreezeAndDeductStockBatchWithTxFO(Long puId, Integer puCount, Integer type, 
			 String orderCode, Long userId, String userName, String ip, String mac, List<Long> puIds,List<CommodityProductUnitDTO> commodityProductUnitDTOs) {
		this.puId                     = puId;
		this.puCount                  = puCount;
		this.type                     = type;
		this.orderCode                = orderCode;
		this.userId                   = userId;
		this.userName                 = userName;
		this.ip                       = ip;
		this.mac                      = mac;
		this.puIds                   = puIds;
		this.commodityProductUnitDTOs = commodityProductUnitDTOs;
		
		
	}
	public Long getPuId() {
		return puId;
	}
	public void setPuId(Long puId) {
		this.puId = puId;
	}
	public Integer getPuCount() {
		return puCount;
	}
	public void setPuCount(Integer puCount) {
		this.puCount = puCount;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public List<Long> getPuIds() {
		return puIds;
	}
	public void setPuIds(List<Long> puIds) {
		this.puIds = puIds;
	}
	public List<CommodityProductUnitDTO> getCommodityProductUnitDTOs() {
		return commodityProductUnitDTOs;
	}
	public void setCommodityProductUnitDTOs(List<CommodityProductUnitDTO> commodityProductUnitDTOs) {
		this.commodityProductUnitDTOs = commodityProductUnitDTOs;
	}
}
