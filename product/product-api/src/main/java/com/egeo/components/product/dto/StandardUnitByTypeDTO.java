package com.egeo.components.product.dto;

import java.math.BigDecimal;

import com.egeo.orm.Pagination;

public class StandardUnitByTypeDTO {

    private Integer couponType;
    private Integer saleWay;
    private Long queryId;
    private Integer type;
    private Long platformId;
    private String userBalance;
    private Long clientId;
    private Long companyId;
    private Integer companyType;
    private Long storeId;
    private Pagination page;
    private Integer buyType;
    
    public StandardUnitByTypeDTO(Integer couponType, Integer saleWay, Long queryId, 
    		Integer type, Long platformId,BigDecimal userBalance, 
    		Long clientId, Long companyId, Integer companyType, 
    		Long storeId, Pagination page, Integer buyType) {
    	this.couponType= couponType;
    	this.saleWay = saleWay;
    	this.queryId = queryId; 
    	this.type= type;
    	this.platformId = platformId;
    	this.userBalance= userBalance.toPlainString(); 
    	this.clientId= clientId; 
    	this.companyId= companyId; 
    	this.companyType= companyType; 
    	this.storeId= storeId; 
    	this.page= page; 
    	this.buyType= buyType;
    }
    
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public Integer getSaleWay() {
		return saleWay;
	}
	public void setSaleWay(Integer saleWay) {
		this.saleWay = saleWay;
	}
	public Long getQueryId() {
		return queryId;
	}
	public void setQueryId(Long queryId) {
		this.queryId = queryId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public BigDecimal getUserBalance() {
		return new BigDecimal(this.userBalance);
	}
	public void setUserBalance(BigDecimal userBalance) {
		this.userBalance = userBalance.toPlainString();
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Integer getCompanyType() {
		return companyType;
	}
	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public Integer getBuyType() {
		return buyType;
	}
	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}
    
}
