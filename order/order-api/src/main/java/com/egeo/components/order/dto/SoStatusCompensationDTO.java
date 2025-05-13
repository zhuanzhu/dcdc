package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoStatusCompensationDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单编号
	 */
	private String orderCode;	

	/**
	 * 订单状态
	 */
	private Integer orderStatus;	

	/**
	 * 业务类型: 1,促销 2,海购
	 */
	private Integer orderBusinessType;	

	/**
	 * 业务子类型: 1001促销-拼团 1002,促销-拼钢 1003,促销-预售 2001,海购
	 */
	private Integer orderBusinessSubType;	

	/**
	 * 业务状态
	 */
	private Integer orderBusinessStatus;	

	/**
	 * 进展阶段: 1,pre 2,doing 3,after
	 */
	private Integer phase;	

	/**
	 * 补偿状态: 1,进行中 2,完成 3,异常需人工介入
	 */
	private Integer compensationStatus;	

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
	 * 订单状态
	 * @return 订单状态
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 订单状态
	 * @param orderStatus 订单状态
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 业务类型: 1,促销 2,海购
	 * @return 业务类型: 1,促销 2,海购
	 */
	public Integer getOrderBusinessType() {
		return orderBusinessType;
	}

	/**
	 * 业务类型: 1,促销 2,海购
	 * @param orderBusinessType 业务类型: 1,促销 2,海购
	 */
	public void setOrderBusinessType(Integer orderBusinessType) {
		this.orderBusinessType = orderBusinessType;
	}
	/**
	 * 业务子类型: 1001促销-拼团 1002,促销-拼钢 1003,促销-预售 2001,海购
	 * @return 业务子类型: 1001促销-拼团 1002,促销-拼钢 1003,促销-预售 2001,海购
	 */
	public Integer getOrderBusinessSubType() {
		return orderBusinessSubType;
	}

	/**
	 * 业务子类型: 1001促销-拼团 1002,促销-拼钢 1003,促销-预售 2001,海购
	 * @param orderBusinessSubType 业务子类型: 1001促销-拼团 1002,促销-拼钢 1003,促销-预售 2001,海购
	 */
	public void setOrderBusinessSubType(Integer orderBusinessSubType) {
		this.orderBusinessSubType = orderBusinessSubType;
	}
	/**
	 * 业务状态
	 * @return 业务状态
	 */
	public Integer getOrderBusinessStatus() {
		return orderBusinessStatus;
	}

	/**
	 * 业务状态
	 * @param orderBusinessStatus 业务状态
	 */
	public void setOrderBusinessStatus(Integer orderBusinessStatus) {
		this.orderBusinessStatus = orderBusinessStatus;
	}
	/**
	 * 进展阶段: 1,pre 2,doing 3,after
	 * @return 进展阶段: 1,pre 2,doing 3,after
	 */
	public Integer getPhase() {
		return phase;
	}

	/**
	 * 进展阶段: 1,pre 2,doing 3,after
	 * @param phase 进展阶段: 1,pre 2,doing 3,after
	 */
	public void setPhase(Integer phase) {
		this.phase = phase;
	}
	/**
	 * 补偿状态: 1,进行中 2,完成 3,异常需人工介入
	 * @return 补偿状态: 1,进行中 2,完成 3,异常需人工介入
	 */
	public Integer getCompensationStatus() {
		return compensationStatus;
	}

	/**
	 * 补偿状态: 1,进行中 2,完成 3,异常需人工介入
	 * @param compensationStatus 补偿状态: 1,进行中 2,完成 3,异常需人工介入
	 */
	public void setCompensationStatus(Integer compensationStatus) {
		this.compensationStatus = compensationStatus;
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
	