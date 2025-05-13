package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.List;

public class NewSoOrderDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private SoDTO soDTO;//订单信息
	private List<SoItemDTO> itemList;//订单项列表
	private List<Long> cartItemIds;//购物车id列表
	private List<SoChildDTO> soChildDTOList;//子订单信息
	private SoDeviceDTO soDevice;//订单设备信息
	private List<LimitRuleRecordDTO> limitRuleRecordList; // 限购规则记录
	private SoThirdpartyDTO soThirdpartyDTO;	//第三方订单
	private Long companyAllId; // 所有公司id

	private List<SoThirdpartyDTO> soThirdpartyDTOList;

	public Long getCompanyAllId() {
		return companyAllId;
	}

	public void setCompanyAllId(Long companyAllId) {
		this.companyAllId = companyAllId;
	}

	public List<LimitRuleRecordDTO> getLimitRuleRecordList() {
		return limitRuleRecordList;
	}

	public void setLimitRuleRecordList(List<LimitRuleRecordDTO> limitRuleRecordList) {
		this.limitRuleRecordList = limitRuleRecordList;
	}

	public SoDeviceDTO getSoDevice() {
		return soDevice;
	}

	public void setSoDevice(SoDeviceDTO soDevice) {
		this.soDevice = soDevice;
	}

	public List<SoChildDTO> getSoChild() {
		return soChildDTOList;
	}

	public void setSoChild(List<SoChildDTO> soChild) {
		this.soChildDTOList = soChild;
	}

	public List<Long> getCartItemIds() {
		return cartItemIds;
	}

	public void setCartItemIds(List<Long> cartItemIds) {
		this.cartItemIds = cartItemIds;
	}

	public SoDTO getSoDTO() {
		return soDTO;
	}

	public void setSoDTO(SoDTO soDTO) {
		this.soDTO = soDTO;
	}

	public List<SoItemDTO> getItemList() {
		return itemList;
	}

	public void setItemList(List<SoItemDTO> itemList) {
		this.itemList = itemList;
	}

	public SoThirdpartyDTO getSoThirdpartyDTO() {
		return soThirdpartyDTO;
	}

	public void setSoThirdpartyDTO(SoThirdpartyDTO soThirdpartyDTO) {
		this.soThirdpartyDTO = soThirdpartyDTO;
	}


	public List<SoThirdpartyDTO> getSoThirdpartyDTOList() {
		return soThirdpartyDTOList;
	}

	public void setSoThirdpartyDTOList(List<SoThirdpartyDTO> soThirdpartyDTOList) {
		this.soThirdpartyDTOList = soThirdpartyDTOList;
	}
}
