package com.egeo.components.finance.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-02-07 17:53:02
 */
public class SoFreezeFubiDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单id
	 */
	private Long soId;	

	/**
	 * 冻结积分金额
	 */
	private BigDecimal balance;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 用户id
	 */
	private Long userId;	

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
	 * 冻结积分金额
	 * @return 冻结积分金额
	 */
	public BigDecimal getBalance() {
		return balance;
	}

	/**
	 * 冻结积分金额
	 * @param balance 冻结积分金额
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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
}
	