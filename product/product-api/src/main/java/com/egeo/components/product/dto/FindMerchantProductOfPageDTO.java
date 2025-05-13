package com.egeo.components.product.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.egeo.orm.Pagination;

public class FindMerchantProductOfPageDTO {
	private String priceStart;
	private String priceEnd;
	private Integer startProfit;
	private Integer endProfit;
	private List<Long> storeIds;
	private Date starTime;
	private Date endTime;
	private MerchantProductDTO dto;
	private Pagination page;
	private List<String> nameList;
	public FindMerchantProductOfPageDTO(BigDecimal priceStart, BigDecimal priceEnd, Integer startProfit, 
			Integer endProfit, List<Long> storeIds, Date starTime, Date endTime, MerchantProductDTO dto, 
			Pagination page, List<String> nameList){
		this.priceStart  = priceStart.toPlainString() ;
		this.priceEnd    = priceEnd.toPlainString()   ;
		this.startProfit = startProfit;
		this.endProfit   = endProfit  ;
		this.storeIds    = storeIds   ;
		this.starTime    = starTime   ;
		this.endTime     = endTime    ;
		this.dto         = dto        ;
		this.page        = page       ;
		this.nameList    = nameList   ;
	}
	public BigDecimal getPriceStart() {
		return new BigDecimal(this.priceStart);
	}
	public void setPriceStart(BigDecimal priceStart) {
		this.priceStart = priceStart.toPlainString();
	}
	public BigDecimal getPriceEnd() {
		return new BigDecimal(this.priceEnd);
	}
	public void setPriceEnd(BigDecimal priceEnd) {
		this.priceEnd = priceEnd.toPlainString();
	}
	public Integer getStartProfit() {
		return startProfit;
	}
	public void setStartProfit(Integer startProfit) {
		this.startProfit = startProfit;
	}
	public Integer getEndProfit() {
		return endProfit;
	}
	public void setEndProfit(Integer endProfit) {
		this.endProfit = endProfit;
	}
	public List<Long> getStoreIds() {
		return storeIds;
	}
	public void setStoreIds(List<Long> storeIds) {
		this.storeIds = storeIds;
	}
	public Date getStarTime() {
		return starTime;
	}
	public void setStarTime(Date starTime) {
		this.starTime = starTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public MerchantProductDTO getDto() {
		return dto;
	}
	public void setDto(MerchantProductDTO dto) {
		this.dto = dto;
	}
	public Pagination getPage() {
		return page;
	}
	public void setPage(Pagination page) {
		this.page = page;
	}
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
}
