package com.egeo.components.finance.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-11 18:22:01
 */
public class TempRechargeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 位于表格的行号
	 */
	private Integer rownum;	

	/**
	 * 用户名
	 */
	private String userName;	

	/**
	 * 员工号
	 */
	private String memberCode;	

	/**
	 * 邮箱
	 */
	private String email;	

	/**
	 * 用户id
	 */
	private Long userId;	

	/**
	 * 金额
	 */
	private BigDecimal sum;	

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 公司名称
	 */
	private String companyName;	

	/**
	 * 导入结果 0:正常 1:异常
	 */
	private Integer impRes;	

	/**
	 * 文件序号
	 */
	private String sn;	

	/**
	 * 文件创建时间
	 */
	private Date generatedTime;	

	/**
	 * 模板类型代码
	 */
	private Integer tmplType;	

	/**
	 * 模板类型名
	 */
	private String tmplName;	

	/**
	 * 账户类型
	 */
	private Integer accountType;	

	/**
	 * 批次原因id
	 */
	private Long reasonId;	

	/**
	 * 批次原因
	 */
	private String batchReason;	

	/**
	 * 流水原因
	 */
	private String flowReason;	

	/**
	 * 备注
	 */
	private String remark;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 创建时间
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;
	
	private String salt;
	
	private String finBatch;
	
	private Long accountId;
	
	private Integer status;
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getFinBatch() {
		return finBatch;
	}

	public void setFinBatch(String finBatch) {
		this.finBatch = finBatch;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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
	 * 位于表格的行号
	 * @return 位于表格的行号
	 */
	public Integer getRownum() {
		return rownum;
	}

	/**
	 * 位于表格的行号
	 * @param rownum 位于表格的行号
	 */
	public void setRownum(Integer rownum) {
		this.rownum = rownum;
	}
	/**
	 * 用户名
	 * @return 用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用户名
	 * @param userName 用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 员工号
	 * @return 员工号
	 */
	public String getMemberCode() {
		return memberCode;
	}

	/**
	 * 员工号
	 * @param memberCode 员工号
	 */
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	/**
	 * 邮箱
	 * @return 邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 邮箱
	 * @param email 邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
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
	/**
	 * 公司名称
	 * @return 公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 公司名称
	 * @param companyName 公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * 导入结果 0:正常 1:异常
	 * @return 导入结果 0:正常 1:异常
	 */
	public Integer getImpRes() {
		return impRes;
	}

	/**
	 * 导入结果 0:正常 1:异常
	 * @param impRes 导入结果 0:正常 1:异常
	 */
	public void setImpRes(Integer impRes) {
		this.impRes = impRes;
	}
	/**
	 * 文件序号
	 * @return 文件序号
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * 文件序号
	 * @param sn 文件序号
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * 文件创建时间
	 * @return 文件创建时间
	 */
	public Date getGeneratedTime() {
		return generatedTime;
	}

	/**
	 * 文件创建时间
	 * @param generatedTime 文件创建时间
	 */
	public void setGeneratedTime(Date generatedTime) {
		this.generatedTime = generatedTime;
	}
	/**
	 * 模板类型代码
	 * @return 模板类型代码
	 */
	public Integer getTmplType() {
		return tmplType;
	}

	/**
	 * 模板类型代码
	 * @param tmplType 模板类型代码
	 */
	public void setTmplType(Integer tmplType) {
		this.tmplType = tmplType;
	}
	/**
	 * 模板类型名
	 * @return 模板类型名
	 */
	public String getTmplName() {
		return tmplName;
	}

	/**
	 * 模板类型名
	 * @param tmplName 模板类型名
	 */
	public void setTmplName(String tmplName) {
		this.tmplName = tmplName;
	}
	/**
	 * 账户类型
	 * @return 账户类型
	 */
	public Integer getAccountType() {
		return accountType;
	}

	/**
	 * 账户类型
	 * @param accountType 账户类型
	 */
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	/**
	 * 批次原因id
	 * @return 批次原因id
	 */
	public Long getReasonId() {
		return reasonId;
	}

	/**
	 * 批次原因id
	 * @param reasonId 批次原因id
	 */
	public void setReasonId(Long reasonId) {
		this.reasonId = reasonId;
	}
	/**
	 * 批次原因
	 * @return 批次原因
	 */
	public String getBatchReason() {
		return batchReason;
	}

	/**
	 * 批次原因
	 * @param batchReason 批次原因
	 */
	public void setBatchReason(String batchReason) {
		this.batchReason = batchReason;
	}
	/**
	 * 流水原因
	 * @return 流水原因
	 */
	public String getFlowReason() {
		return flowReason;
	}

	/**
	 * 流水原因
	 * @param flowReason 流水原因
	 */
	public void setFlowReason(String flowReason) {
		this.flowReason = flowReason;
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
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 创建时间
	 * @param updateTime 创建时间
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
	