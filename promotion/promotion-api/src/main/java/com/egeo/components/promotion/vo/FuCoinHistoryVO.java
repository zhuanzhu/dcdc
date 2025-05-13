package com.egeo.components.promotion.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-11-10 15:43:54
 */
public class FuCoinHistoryVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 订单id
	 */
	private String orderCode;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 积分
	 */
	private BigDecimal coin;
	/**
	 * 操作类型：1.支付订单，2退货
	 */
	private Integer operationType;
	/**
	 * 积分操作阶段：1订单消费，2商品评价
	 */
	private String operationPhase;
	/**
	 * 积分类型，1增加积分，2减少积分
	 */
	private Integer type;
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
	 * 订单id
	 * @return 订单id
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单id
	 * @param orderCode 订单id
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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
	 * 积分
	 * @return 积分
	 */
	public BigDecimal getCoin() {
		return coin;
	}

	/**
	 * 积分
	 * @param coin 积分
	 */
	public void setCoin(BigDecimal coin) {
		this.coin = coin;
	}	
	/**
	 * 操作类型：1.支付订单，2退货
	 * @return 操作类型：1.支付订单，2退货
	 */
	public Integer getOperationType() {
		return operationType;
	}

	/**
	 * 操作类型：1.支付订单，2退货
	 * @param operationType 操作类型：1.支付订单，2退货
	 */
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}	
	/**
	 * 积分操作阶段：1订单消费，2商品评价
	 * @return 积分操作阶段：1订单消费，2商品评价
	 */
	public String getOperationPhase() {
		return operationPhase;
	}

	/**
	 * 积分操作阶段：1订单消费，2商品评价
	 * @param operationPhase 积分操作阶段：1订单消费，2商品评价
	 */
	public void setOperationPhase(String operationPhase) {
		this.operationPhase = operationPhase;
	}	
	/**
	 * 积分类型，1增加积分，2减少积分
	 * @return 积分类型，1增加积分，2减少积分
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 积分类型，1增加积分，2减少积分
	 * @param type 积分类型，1增加积分，2减少积分
	 */
	public void setType(Integer type) {
		this.type = type;
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
	