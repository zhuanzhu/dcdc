package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-08-18 15:45:01
 */
public class SoDeliveryItemVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 订单编号
	 */

	private String orderCode;		 
	/**
	 * 包裹序号
	 */

	private String packageNo;		 
	/**
	 * 配送单号
	 */

	private String deliveryExpressNbr;		 
	/**
	 * 描述
	 */

	private String remark;		 
	/**
	 * 实际发货时间
	 */

	private Date deliveryDate;		 
	/**
	 * 预计送达时间
	 */

	private Date estimateReceiveDate;		 
	/**
	 * 发货备注
	 */

	private String deliveryRemark;		 
	/**
	 * 配送人
	 */

	private String deliverierName;		 
	/**
	 * 配送人手机号
	 */

	private String deliverierMobile;		 
	/**
	 * 平台id
	 */

	private Long platformId;		 
	/**
	 * 修改时间:更新时数据库会自动set值
	 */

	private Date updateTime;		 
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */

	private Date createTime;		 

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
	 * 订单编号
	 * @return 订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单编号
	 * @param orderCode 订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}	
	/**
	 * 包裹序号
	 * @return 包裹序号
	 */
	public String getPackageNo() {
		return packageNo;
	}

	/**
	 * 包裹序号
	 * @param packageNo 包裹序号
	 */
	public void setPackageNo(String packageNo) {
		this.packageNo = packageNo;
	}	
	/**
	 * 配送单号
	 * @return 配送单号
	 */
	public String getDeliveryExpressNbr() {
		return deliveryExpressNbr;
	}

	/**
	 * 配送单号
	 * @param deliveryExpressNbr 配送单号
	 */
	public void setDeliveryExpressNbr(String deliveryExpressNbr) {
		this.deliveryExpressNbr = deliveryExpressNbr;
	}	
	/**
	 * 描述
	 * @return 描述
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 描述
	 * @param remark 描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}	
	/**
	 * 实际发货时间
	 * @return 实际发货时间
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * 实际发货时间
	 * @param deliveryDate 实际发货时间
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}	
	/**
	 * 预计送达时间
	 * @return 预计送达时间
	 */
	public Date getEstimateReceiveDate() {
		return estimateReceiveDate;
	}

	/**
	 * 预计送达时间
	 * @param estimateReceiveDate 预计送达时间
	 */
	public void setEstimateReceiveDate(Date estimateReceiveDate) {
		this.estimateReceiveDate = estimateReceiveDate;
	}	
	/**
	 * 发货备注
	 * @return 发货备注
	 */
	public String getDeliveryRemark() {
		return deliveryRemark;
	}

	/**
	 * 发货备注
	 * @param deliveryRemark 发货备注
	 */
	public void setDeliveryRemark(String deliveryRemark) {
		this.deliveryRemark = deliveryRemark;
	}	
	/**
	 * 配送人
	 * @return 配送人
	 */
	public String getDeliverierName() {
		return deliverierName;
	}

	/**
	 * 配送人
	 * @param deliverierName 配送人
	 */
	public void setDeliverierName(String deliverierName) {
		this.deliverierName = deliverierName;
	}	
	/**
	 * 配送人手机号
	 * @return 配送人手机号
	 */
	public String getDeliverierMobile() {
		return deliverierMobile;
	}

	/**
	 * 配送人手机号
	 * @param deliverierMobile 配送人手机号
	 */
	public void setDeliverierMobile(String deliverierMobile) {
		this.deliverierMobile = deliverierMobile;
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
}
	