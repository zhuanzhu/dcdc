package com.egeo.components.promotion.dto;

public class UpdateExchangeAndCouponWithTxDTO {
	private String couponUnitCode;
	private Integer couponUnitStatus; 
	private Long couponUnitId;
	private CouponBatchDTO couponBatchDTO; 
	private Long recordId; 
	private Long userId;
	public UpdateExchangeAndCouponWithTxDTO(String couponUnitCode, Integer couponUnitStatus, 
			Long couponUnitId, CouponBatchDTO couponBatchDTO, Long recordId, Long userId) {
		this.couponUnitCode	=couponUnitCode;
		this.couponUnitStatus	=couponUnitStatus;
		this.couponUnitId	=couponUnitId;
		this.couponBatchDTO	=couponBatchDTO;
		this.recordId	=recordId;
		this.userId	=userId;
		
	}
	public String getCouponUnitCode() {
		return couponUnitCode;
	}
	public void setCouponUnitCode(String couponUnitCode) {
		this.couponUnitCode = couponUnitCode;
	}
	public Integer getCouponUnitStatus() {
		return couponUnitStatus;
	}
	public void setCouponUnitStatus(Integer couponUnitStatus) {
		this.couponUnitStatus = couponUnitStatus;
	}
	public Long getCouponUnitId() {
		return couponUnitId;
	}
	public void setCouponUnitId(Long couponUnitId) {
		this.couponUnitId = couponUnitId;
	}
	public CouponBatchDTO getCouponBatchDTO() {
		return couponBatchDTO;
	}
	public void setCouponBatchDTO(CouponBatchDTO couponBatchDTO) {
		this.couponBatchDTO = couponBatchDTO;
	}
	public Long getRecordId() {
		return recordId;
	}
	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
