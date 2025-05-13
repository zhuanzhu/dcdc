package com.egeo.components.finance.po;


import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-23 14:37:37
 */
public class AccountBatchTmpPO {


	private Long id;

	/**
	 * 出方账户id 只能是公司
	 */
	private Long outflowAccountid;	

	/**
	 * 入方账户id 只能是公司 是用户则不填写
	 */
	private Long inflowAccountid;	

	/**
	 * 充值/调整批次
	 */
	private String finBatch;	

	/**
	 * 金额
	 */
	private BigDecimal sum;	

	/**
	 * 原因id
	 */
	private Long reasonId;	

	/**
	 * 备注
	 */
	private String remark;	

	/**
	 * 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 */
	private Integer status;	

	/**
	 * 执行人id
	 */
	private Long operatorId;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 类型 0:企业账户充值 1:企业账户调整 2:企业失效 3:订单现金付款 4:订单积分付款 5:订单现金退款 6:订单积分退款 7:员工积分充值 8:员工点赞福豆充值 9:员工离职 10:员工点赞
	 */
	private Integer type;	

	/**
	 * 公司id
	 */
	private Long companyId;	
	
	/**
	 * 批次审核通过/不通过原因
	 */
	private String examReason;

	public String getExamReason() {
		return examReason;
	}

	public void setExamReason(String examReason) {
		this.examReason = examReason;
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
	 * 出方账户id 只能是公司
	 * @return 出方账户id 只能是公司
	 */
	public Long getOutflowAccountid() {
		return outflowAccountid;
	}

	/**
	 * 出方账户id 只能是公司
	 * @param outflowAccountid 出方账户id 只能是公司
	 */
	public void setOutflowAccountid(Long outflowAccountid) {
		this.outflowAccountid = outflowAccountid;
	}

	/**
	 * 入方账户id 只能是公司 是用户则不填写
	 * @return 入方账户id 只能是公司 是用户则不填写
	 */
	public Long getInflowAccountid() {
		return inflowAccountid;
	}

	/**
	 * 入方账户id 只能是公司 是用户则不填写
	 * @param inflowAccountid 入方账户id 只能是公司 是用户则不填写
	 */
	public void setInflowAccountid(Long inflowAccountid) {
		this.inflowAccountid = inflowAccountid;
	}

	public String getFinBatch() {
		return finBatch;
	}

	public void setFinBatch(String finBatch) {
		this.finBatch = finBatch;
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
	 * 原因id
	 * @return 原因id
	 */
	public Long getReasonId() {
		return reasonId;
	}

	/**
	 * 原因id
	 * @param reasonId 原因id
	 */
	public void setReasonId(Long reasonId) {
		this.reasonId = reasonId;
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

	/**
	 * 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 * @return 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 * @param status 状态 0:待审核 1:已通过 2:未通过 3:已完成
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 执行人id
	 * @return 执行人id
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * 执行人id
	 * @param operatorId 执行人id
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
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
	 * 类型 0:企业账户充值 1:企业账户调整 2:企业失效 3:订单现金付款 4:订单积分付款 5:订单现金退款 6:订单积分退款 7:员工积分充值 8:员工点赞福豆充值 9:员工离职 10:员工点赞
	 * @return 类型 0:企业账户充值 1:企业账户调整 2:企业失效 3:订单现金付款 4:订单积分付款 5:订单现金退款 6:订单积分退款 7:员工积分充值 8:员工点赞福豆充值 9:员工离职 10:员工点赞
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型 0:企业账户充值 1:企业账户调整 2:企业失效 3:订单现金付款 4:订单积分付款 5:订单现金退款 6:订单积分退款 7:员工积分充值 8:员工点赞福豆充值 9:员工离职 10:员工点赞
	 * @param type 类型 0:企业账户充值 1:企业账户调整 2:企业失效 3:订单现金付款 4:订单积分付款 5:订单现金退款 6:订单积分退款 7:员工积分充值 8:员工点赞福豆充值 9:员工离职 10:员工点赞
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 公司id
	 * @return 公司id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 公司id
	 * @param companyId 公司id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
	