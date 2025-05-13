package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-11-09 15:50:11
 */
public class PointsRuleDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 单个商品最高获取积分
	 */
	private BigDecimal singleProdHighestGain;	

	/**
	 * 商品积分获取设定类型：1按商品类目，2按商品品牌，3按订单金额
	 */
	private Integer gainType;	

	/**
	 * 订单金额方式金额积分比率
	 */
	private BigDecimal orderMoneyRate;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

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
	 * 单个商品最高获取积分
	 * @return 单个商品最高获取积分
	 */
	public BigDecimal getSingleProdHighestGain() {
		return singleProdHighestGain;
	}

	/**
	 * 单个商品最高获取积分
	 * @param singleProdHighestGain 单个商品最高获取积分
	 */
	public void setSingleProdHighestGain(BigDecimal singleProdHighestGain) {
		this.singleProdHighestGain = singleProdHighestGain;
	}
	/**
	 * 商品积分获取设定类型：1按商品类目，2按商品品牌，3按订单金额
	 * @return 商品积分获取设定类型：1按商品类目，2按商品品牌，3按订单金额
	 */
	public Integer getGainType() {
		return gainType;
	}

	/**
	 * 商品积分获取设定类型：1按商品类目，2按商品品牌，3按订单金额
	 * @param gainType 商品积分获取设定类型：1按商品类目，2按商品品牌，3按订单金额
	 */
	public void setGainType(Integer gainType) {
		this.gainType = gainType;
	}
	/**
	 * 订单金额方式金额积分比率
	 * @return 订单金额方式金额积分比率
	 */
	public BigDecimal getOrderMoneyRate() {
		return orderMoneyRate;
	}

	/**
	 * 订单金额方式金额积分比率
	 * @param orderMoneyRate 订单金额方式金额积分比率
	 */
	public void setOrderMoneyRate(BigDecimal orderMoneyRate) {
		this.orderMoneyRate = orderMoneyRate;
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
	