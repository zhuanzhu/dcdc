package com.egeo.components.order.vo;

/**
 * 订单发货信息导入excelVO
 * @author graci
 *
 */
public class DeliveryImportExcelVO {

	
	private Long soId;
	
	private String orderCode;
	
	private Long soChildId;
	
	private String childCode;
	
	private Long deliveryCompanyId;
	
	private String deliveryCompany;
	
	private String deliveryCode;
	
	private Long boxCode;

	private Long receiverAddressId;
	
	/**
	 * 订单用户id
	 */
	private Long userId;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getReceiverAddressId() {
		return receiverAddressId;
	}

	public void setReceiverAddressId(Long receiverAddressId) {
		this.receiverAddressId = receiverAddressId;
	}

	public Long getSoId() {
		return soId;
	}

	public void setSoId(Long soId) {
		this.soId = soId;
	}

	public Long getSoChildId() {
		return soChildId;
	}

	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}

	public Long getDeliveryCompanyId() {
		return deliveryCompanyId;
	}

	public void setDeliveryCompanyId(Long deliveryCompanyId) {
		this.deliveryCompanyId = deliveryCompanyId;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

	public String getDeliveryCompany() {
		return deliveryCompany;
	}

	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}

	public String getDeliveryCode() {
		return deliveryCode;
	}

	public void setDeliveryCode(String deliveryCode) {
		this.deliveryCode = deliveryCode;
	}

	public Long getBoxCode() {
		return boxCode;
	}

	public void setBoxCode(Long boxCode) {
		this.boxCode = boxCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((boxCode == null) ? 0 : boxCode.hashCode());
		result = prime * result + ((childCode == null) ? 0 : childCode.hashCode());
		result = prime * result + ((deliveryCode == null) ? 0 : deliveryCode.hashCode());
		result = prime * result + ((deliveryCompany == null) ? 0 : deliveryCompany.hashCode());
		result = prime * result + ((orderCode == null) ? 0 : orderCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeliveryImportExcelVO other = (DeliveryImportExcelVO) obj;
		if (boxCode == null) {
			if (other.boxCode != null)
				return false;
		} else if (!boxCode.equals(other.boxCode))
			return false;
		if (childCode == null) {
			if (other.childCode != null)
				return false;
		} else if (!childCode.equals(other.childCode))
			return false;
		if (deliveryCode == null) {
			if (other.deliveryCode != null)
				return false;
		} else if (!deliveryCode.equals(other.deliveryCode))
			return false;
		if (deliveryCompany == null) {
			if (other.deliveryCompany != null)
				return false;
		} else if (!deliveryCompany.equals(other.deliveryCompany))
			return false;
		if (orderCode == null) {
			if (other.orderCode != null)
				return false;
		} else if (!orderCode.equals(other.orderCode))
			return false;
		return true;
	}
	
	
}
