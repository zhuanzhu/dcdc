package com.egeo.components.finance.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 后台流水列表页面vo
 * @author jiang
 * @date 2018-01-06 15:25:31
 */
public class AccountFlowVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 流出账户id
	 */
	private Long outflowAccountid;
	/**
	 * 流出账户类型 0:公司账户  1:员工账户
	 */
	private Integer outflowAccounttype;
	
	private String outflowAccountName;
	
	private String inflowAccountName;
	/**
	 * 流入账户id
	 */
	private Long inflowAccountid;
	/**
	 * 流入账户类型 0:公司账户  1:员工账户
	 */
	private Integer inflowAccounttype;
	/**
	 * 金额
	 */
	private BigDecimal sum;
	/**
	 * 操作原因id
	 */
	private String reason;
	
	private String type;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private String createTime;
	
	private String currencyType;
	
	/**
	 * 是否已读 0-未读 1-已读
	 */
	private Integer isRead;

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

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
	 * 流出账户id
	 * @return 流出账户id
	 */
	public Long getOutflowAccountid() {
		return outflowAccountid;
	}

	/**
	 * 流出账户id
	 * @param outflowAccountid 流出账户id
	 */
	public void setOutflowAccountid(Long outflowAccountid) {
		this.outflowAccountid = outflowAccountid;
	}	
	/**
	 * 流出账户类型 0:公司账户  1:员工账户
	 * @return 流出账户类型 0:公司账户  1:员工账户
	 */
	public Integer getOutflowAccounttype() {
		return outflowAccounttype;
	}

	/**
	 * 流出账户类型 0:公司账户  1:员工账户
	 * @param outflowAccounttype 流出账户类型 0:公司账户  1:员工账户
	 */
	public void setOutflowAccounttype(Integer outflowAccounttype) {
		this.outflowAccounttype = outflowAccounttype;
	}	
	/**
	 * 流入账户id
	 * @return 流入账户id
	 */
	public Long getInflowAccountid() {
		return inflowAccountid;
	}

	/**
	 * 流入账户id
	 * @param inflowAccountid 流入账户id
	 */
	public void setInflowAccountid(Long inflowAccountid) {
		this.inflowAccountid = inflowAccountid;
	}	
	/**
	 * 流入账户类型 0:公司账户  1:员工账户
	 * @return 流入账户类型 0:公司账户  1:员工账户
	 */
	public Integer getInflowAccounttype() {
		return inflowAccounttype;
	}

	/**
	 * 流入账户类型 0:公司账户  1:员工账户
	 * @param inflowAccounttype 流入账户类型 0:公司账户  1:员工账户
	 */
	public void setInflowAccounttype(Integer inflowAccounttype) {
		this.inflowAccounttype = inflowAccounttype;
	}	
	/**
	 * 金额
	 * @return 金额
	 */
	public BigDecimal getSum() {
		return sum;
	}

	/**
	 * 金额
	 * @param sum 金额
	 */
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}	
	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOutflowAccountName() {
		return outflowAccountName;
	}

	public void setOutflowAccountName(String outflowAccountName) {
		this.outflowAccountName = outflowAccountName;
	}

	public String getInflowAccountName() {
		return inflowAccountName;
	}

	public void setInflowAccountName(String inflowAccountName) {
		this.inflowAccountName = inflowAccountName;
	}


	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}	
}
	