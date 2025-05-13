package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.List;

import com.egeo.dto.HttpServletRequestDTO;

public class CancelAndRefundOrderWithTxDTO implements Serializable {
	private String userName;
	private SoDTO order;
	private  List<SoItemDTO> items;
	private  Long userId;
	private  String soRefundCodeByFubi;
	private String soRefundCodeByCash;
	private String soRefundCodeByJiDian;
	private String soRefundCodeByBuyCard;
	private  HttpServletRequestDTO req;

	public CancelAndRefundOrderWithTxDTO() {
	}

	public CancelAndRefundOrderWithTxDTO(String userName, SoDTO order, List<SoItemDTO> items, Long userId, String soRefundCodeByFubi,
										 String soRefundCodeByCash, HttpServletRequestDTO req) {
		this.userName = userName;
		this.order = order;
		this.items = items;
		this.userId = userId;
		this.soRefundCodeByCash = soRefundCodeByCash;
		this.soRefundCodeByFubi = soRefundCodeByFubi;
		this.req = req;
	}

	public CancelAndRefundOrderWithTxDTO(String userName, SoDTO order, List<SoItemDTO> items, Long userId, String soRefundCodeByFubi,
										 String soRefundCodeByCash,String soRefundCodeByJiDian, HttpServletRequestDTO req) {
		this.userName = userName;
		this.order = order;
		this.items = items;
		this.userId = userId;
		this.soRefundCodeByCash = soRefundCodeByCash;
		this.soRefundCodeByFubi = soRefundCodeByFubi;
		this.soRefundCodeByJiDian = soRefundCodeByJiDian;
		this.req = req;
	}

	public CancelAndRefundOrderWithTxDTO(String userName, SoDTO order, List<SoItemDTO> items, Long userId, String soRefundCodeByFubi, String soRefundCodeByCash, String soRefundCodeByJiDian, String soRefundCodeByBuyCard, HttpServletRequestDTO req) {
		this.userName = userName;
		this.order = order;
		this.items = items;
		this.userId = userId;
		this.soRefundCodeByFubi = soRefundCodeByFubi;
		this.soRefundCodeByCash = soRefundCodeByCash;
		this.soRefundCodeByJiDian = soRefundCodeByJiDian;
		this.soRefundCodeByBuyCard = soRefundCodeByBuyCard;
		this.req = req;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public SoDTO getOrder() {
		return order;
	}

	public void setOrder(SoDTO order) {
		this.order = order;
	}

	public List<SoItemDTO> getItems() {
		return items;
	}

	public void setItems(List<SoItemDTO> items) {
		this.items = items;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public HttpServletRequestDTO getReq() {
		return req;
	}

	public void setReq(HttpServletRequestDTO req) {
		this.req = req;
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
