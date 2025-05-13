package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-29 09:59:05
 */
public class SoDeliveryDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 商家id
	 */
	private Long merchantId;	

	/**
	 * 物流信息类型1:订单2:发票
	 */
	private Integer type;	

	/**
	 * 订单id
	 */
	private Long soId;	

	/**
	 * 子订单id
	 */
	private Long soChildId;	

	/**
	 * 
	 */
	private String orderCode;	

	/**
	 * 
	 */
	private String deliveryCompanyId;	

	/**
	 * 
	 */
	private String deliveryExpressNbr;	

	/**
	 * 实际入库时间
	 */
	private Date actualStorageTime;	

	/**
	 * 实际收货时间
	 */
	private Date actualReceiptTime;	

	/**
	 * 
	 */
	private String stockOutNo;	

	/**
	 * 
	 */
	private String remark;	

	/**
	 * 物流跟踪类型0:已签收,1:派送中,2:拒收,3:运单异常
	 */
	private Integer deliveryType;	

	/**
	 * 
	 */
	private String deliveryRemark;	

	/**
	 * 
	 */
	private String packageCode;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	public Long getId() {
		return id;
	}

	/**
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 用户id
	 * @return 用户id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * @param userId 用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 商家id
	 * @return 商家id
	 */
	public Long getMerchantId() {
		return merchantId;
	}

	/**
	 * 商家id
	 * @param merchantId 商家id
	 */
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	/**
	 * 物流信息类型1:订单2:发票
	 * @return 物流信息类型1:订单2:发票
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 物流信息类型1:订单2:发票
	 * @param type 物流信息类型1:订单2:发票
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 订单id
	 * @return 订单id
	 */
	public Long getSoId() {
		return soId;
	}

	/**
	 * 订单id
	 * @param soId 订单id
	 */
	public void setSoId(Long soId) {
		this.soId = soId;
	}
	/**
	 * 子订单id
	 * @return 子订单id
	 */
	public Long getSoChildId() {
		return soChildId;
	}

	/**
	 * 子订单id
	 * @param soChildId 子订单id
	 */
	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}
	/**
	 * 
	 * @return 
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 
	 * @param orderCode 
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	/**
	 * 
	 * @return 
	 */
	public String getDeliveryCompanyId() {
		return deliveryCompanyId;
	}

	/**
	 * 
	 * @param deliveryCompanyId 
	 */
	public void setDeliveryCompanyId(String deliveryCompanyId) {
		this.deliveryCompanyId = deliveryCompanyId;
	}
	/**
	 * 
	 * @return 
	 */
	public String getDeliveryExpressNbr() {
		return deliveryExpressNbr;
	}

	/**
	 * 
	 * @param deliveryExpressNbr 
	 */
	public void setDeliveryExpressNbr(String deliveryExpressNbr) {
		this.deliveryExpressNbr = deliveryExpressNbr;
	}
	/**
	 * 实际入库时间
	 * @return 实际入库时间
	 */
	public Date getActualStorageTime() {
		return actualStorageTime;
	}

	/**
	 * 实际入库时间
	 * @param actualStorageTime 实际入库时间
	 */
	public void setActualStorageTime(Date actualStorageTime) {
		this.actualStorageTime = actualStorageTime;
	}
	/**
	 * 实际收货时间
	 * @return 实际收货时间
	 */
	public Date getActualReceiptTime() {
		return actualReceiptTime;
	}

	/**
	 * 实际收货时间
	 * @param actualReceiptTime 实际收货时间
	 */
	public void setActualReceiptTime(Date actualReceiptTime) {
		this.actualReceiptTime = actualReceiptTime;
	}
	/**
	 * 
	 * @return 
	 */
	public String getStockOutNo() {
		return stockOutNo;
	}

	/**
	 * 
	 * @param stockOutNo 
	 */
	public void setStockOutNo(String stockOutNo) {
		this.stockOutNo = stockOutNo;
	}
	/**
	 * 
	 * @return 
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 
	 * @param remark 
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 物流跟踪类型0:已签收,1:派送中,2:拒收,3:运单异常
	 * @return 物流跟踪类型0:已签收,1:派送中,2:拒收,3:运单异常
	 */
	public Integer getDeliveryType() {
		return deliveryType;
	}

	/**
	 * 物流跟踪类型0:已签收,1:派送中,2:拒收,3:运单异常
	 * @param deliveryType 物流跟踪类型0:已签收,1:派送中,2:拒收,3:运单异常
	 */
	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}
	/**
	 * 
	 * @return 
	 */
	public String getDeliveryRemark() {
		return deliveryRemark;
	}

	/**
	 * 
	 * @param deliveryRemark 
	 */
	public void setDeliveryRemark(String deliveryRemark) {
		this.deliveryRemark = deliveryRemark;
	}
	/**
	 * 
	 * @return 
	 */
	public String getPackageCode() {
		return packageCode;
	}

	/**
	 * 
	 * @param packageCode 
	 */
	public void setPackageCode(String packageCode) {
		this.packageCode = packageCode;
	}
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
}
	