package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-29 09:55:24
 */
public class FrozenPuPO {


	private Long id;

	/**
	 * 
	 */
	private Long skuId;	

	/**
	 * 
	 */
	private Long puId;	

	/**
	 * 订单id
	 */
	private Long soId;	

	/**
	 * 子订单id
	 */
	private Long soChildId;	

	/**
	 * 冻结pu数量
	 */
	private Integer frozenCount;	

	/**
	 * 冻结状态 0:冻结中 1:已解冻
	 */
	private Integer freezeStatus;	

	/**
	 * 解冻原因 0:订单取消 1:订单履约
	 */
	private Integer unfreezeReason;	

	/**
	 * 解冻时间
	 */
	private Date unfreezeTime;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getSkuId() {
		return skuId;
	}

	/**
	 * 
	 * @param skuId 
	 */
	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	/**
	 * 
	 * @return 
	 */
	public Long getPuId() {
		return puId;
	}

	/**
	 * 
	 * @param puId 
	 */
	public void setPuId(Long puId) {
		this.puId = puId;
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
	 * 冻结pu数量
	 * @return 冻结pu数量
	 */
	public Integer getFrozenCount() {
		return frozenCount;
	}

	/**
	 * 冻结pu数量
	 * @param frozenCount 冻结pu数量
	 */
	public void setFrozenCount(Integer frozenCount) {
		this.frozenCount = frozenCount;
	}

	/**
	 * 冻结状态 0:冻结中 1:已解冻
	 * @return 冻结状态 0:冻结中 1:已解冻
	 */
	public Integer getFreezeStatus() {
		return freezeStatus;
	}

	/**
	 * 冻结状态 0:冻结中 1:已解冻
	 * @param freezeStatus 冻结状态 0:冻结中 1:已解冻
	 */
	public void setFreezeStatus(Integer freezeStatus) {
		this.freezeStatus = freezeStatus;
	}

	/**
	 * 解冻原因 0:订单取消 1:订单履约
	 * @return 解冻原因 0:订单取消 1:订单履约
	 */
	public Integer getUnfreezeReason() {
		return unfreezeReason;
	}

	/**
	 * 解冻原因 0:订单取消 1:订单履约
	 * @param unfreezeReason 解冻原因 0:订单取消 1:订单履约
	 */
	public void setUnfreezeReason(Integer unfreezeReason) {
		this.unfreezeReason = unfreezeReason;
	}

	/**
	 * 解冻时间
	 * @return 解冻时间
	 */
	public Date getUnfreezeTime() {
		return unfreezeTime;
	}

	/**
	 * 解冻时间
	 * @param unfreezeTime 解冻时间
	 */
	public void setUnfreezeTime(Date unfreezeTime) {
		this.unfreezeTime = unfreezeTime;
	}

	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	