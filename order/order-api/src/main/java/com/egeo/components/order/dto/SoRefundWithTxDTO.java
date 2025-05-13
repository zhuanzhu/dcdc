package com.egeo.components.order.dto;

import com.egeo.dto.HttpServletRequestDTO;

import java.io.Serializable;

public class SoRefundWithTxDTO implements Serializable {

	private SoDTO soDTO;
	private String reason;
	private Long operatorId;
	private String soRefundCodeByFubi;
	private String soRefundCodeByCash;
	private Boolean isCancel;
	private String thirdRefundCode;
	private HttpServletRequestDTO req;
	private String soRefundCodeByJiDian;
	private String soRefundCodeByBuyCard;
	public SoRefundWithTxDTO() {
	}

	public SoRefundWithTxDTO(SoDTO soDTO, String reason, Long operatorId,
							 String soRefundCodeByFubi, String soRefundCodeByCash, Boolean isCancel, String thirdRefundCode, HttpServletRequestDTO req){
		this.soDTO              = soDTO                ;
		this.reason             = reason               ;
		this.operatorId         = operatorId           ;
		this.soRefundCodeByFubi = soRefundCodeByFubi   ;
		this.soRefundCodeByCash = soRefundCodeByCash   ;
		this.isCancel           = isCancel             ;
		this.req                = req                  ;
		this.thirdRefundCode    = thirdRefundCode      ;
	}

	public SoRefundWithTxDTO(SoDTO soDTO, String reason, Long operatorId,
							 String soRefundCodeByFubi, String soRefundCodeByCash, Boolean isCancel, String thirdRefundCode, HttpServletRequestDTO req,String soRefundCodeByJiDian){
		this.soDTO              = soDTO                ;
		this.reason             = reason               ;
		this.operatorId         = operatorId           ;
		this.soRefundCodeByFubi = soRefundCodeByFubi   ;
		this.soRefundCodeByCash = soRefundCodeByCash   ;
		this.isCancel           = isCancel             ;
		this.req                = req                  ;
		this.thirdRefundCode    = thirdRefundCode      ;
		this.soRefundCodeByJiDian = soRefundCodeByJiDian;
	}

	public SoRefundWithTxDTO(SoDTO soDTO, String reason, Long operatorId,
							 String soRefundCodeByFubi, String soRefundCodeByCash, Boolean isCancel, String thirdRefundCode, HttpServletRequestDTO req,String soRefundCodeByJiDian,String soRefundCodeByBuyCard){
		this.soDTO              = soDTO                ;
		this.reason             = reason               ;
		this.operatorId         = operatorId           ;
		this.soRefundCodeByFubi = soRefundCodeByFubi   ;
		this.soRefundCodeByCash = soRefundCodeByCash   ;
		this.isCancel           = isCancel             ;
		this.req                = req                  ;
		this.thirdRefundCode    = thirdRefundCode      ;
		this.soRefundCodeByJiDian = soRefundCodeByJiDian;
		this.soRefundCodeByBuyCard = soRefundCodeByBuyCard;
	}

	public SoDTO getSoDTO() {
		return soDTO;
	}

	public void setSoDTO(SoDTO soDTO) {
		this.soDTO = soDTO;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	public String getSoRefundCodeByFubi() {
		return soRefundCodeByFubi;
	}

	public void setSoRefundCodeByFubi(String soRefundCodeByFubi) {
		this.soRefundCodeByFubi = soRefundCodeByFubi;
	}

	public String getSoRefundCodeByCash() {
		return soRefundCodeByCash;
	}

	public void setSoRefundCodeByCash(String soRefundCodeByCash) {
		this.soRefundCodeByCash = soRefundCodeByCash;
	}

	public Boolean getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(Boolean isCancel) {
		this.isCancel = isCancel;
	}

	public HttpServletRequestDTO getReq() {
		return req;
	}

	public void setReq(HttpServletRequestDTO req) {
		this.req = req;
	}

	public String getThirdRefundCode() {
		return thirdRefundCode;
	}

	public void setThirdRefundCode(String thirdRefundCode) {
		this.thirdRefundCode = thirdRefundCode;
	}

	public String getSoRefundCodeByJiDian() {
		return soRefundCodeByJiDian;
	}

	public void setSoRefundCodeByJiDian(String soRefundCodeByJiDian) {
		this.soRefundCodeByJiDian = soRefundCodeByJiDian;
	}

	public String getSoRefundCodeByBuyCard() {
		return soRefundCodeByBuyCard;
	}

	public void setSoRefundCodeByBuyCard(String soRefundCodeByBuyCard) {
		this.soRefundCodeByBuyCard = soRefundCodeByBuyCard;
	}
}
