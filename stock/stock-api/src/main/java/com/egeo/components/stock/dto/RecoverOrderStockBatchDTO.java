package com.egeo.components.stock.dto;

import java.util.List;

import com.egeo.components.product.dto.CommodityProductUnitDTO;

public class RecoverOrderStockBatchDTO {
	private Long puId; 
	private Integer puCount; 
	private String orderCode;  
	private int status; 
	private Long userId; 
	private String userName; 
	private String ip; 
	private String mac;  
	private List<Long> puIdList; 
	private List<CommodityProductUnitDTO> commodityProductUnitDTOs; 
	public RecoverOrderStockBatchDTO() {}
	public RecoverOrderStockBatchDTO(Long puId, Integer puCount, String orderCode, int status, Long userId,
			String userName, String ip, String mac, List<Long> puIdList,
			List<CommodityProductUnitDTO> commodityProductUnitDTOs) {
		this.puId                     = puId;
		this.puCount                  = puCount;
		this.orderCode                = orderCode ;
		this.status                   = status;
		this.userId                   = userId;
		this.userName                 = userName;
		this.ip                       = ip;
		this.mac                      = mac;
		this.puIdList                 = puIdList;
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
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public List<Long> getPuIdList() {
		return puIdList;
	}
	public void setPuIdList(List<Long> puIdList) {
		this.puIdList = puIdList;
	}
	public List<CommodityProductUnitDTO> getCommodityProductUnitDTOs() {
		return commodityProductUnitDTOs;
	}
	public void setCommodityProductUnitDTOs(List<CommodityProductUnitDTO> commodityProductUnitDTOs) {
		this.commodityProductUnitDTOs = commodityProductUnitDTOs;
	}
	
}
