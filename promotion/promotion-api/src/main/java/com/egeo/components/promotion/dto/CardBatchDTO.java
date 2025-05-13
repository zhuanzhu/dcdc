package com.egeo.components.promotion.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-07-16 09:23:47
 */
public class CardBatchDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 卡密批次编号
	 */
	private String batchCode;	

	/**
	 * 导入数量
	 */
	private Long importSum;	

	/**
	 * 来源
	 */
	private String source;	

	/**
	 * 备注
	 */
	private String remark;	

	/**
	 * 创建人id
	 */
	private Long createUserid;	

	/**
	 * 创建人姓名
	 */
	private String createUsername;	

	/**
	 * 创建人IP
	 */
	private String createUserip;	

	/**
	 * 创建人MAC地址
	 */
	private String createUsermac;	

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
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 卡密批次编号
	 * @return 卡密批次编号
	 */
	public String getBatchCode() {
		return batchCode;
	}

	/**
	 * 卡密批次编号
	 * @param batchCode 卡密批次编号
	 */
	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}
	/**
	 * 导入数量
	 * @return 导入数量
	 */
	public Long getImportSum() {
		return importSum;
	}

	/**
	 * 导入数量
	 * @param importSum 导入数量
	 */
	public void setImportSum(Long importSum) {
		this.importSum = importSum;
	}
	/**
	 * 来源
	 * @return 来源
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 来源
	 * @param source 来源
	 */
	public void setSource(String source) {
		this.source = source;
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
	 * 创建人id
	 * @return 创建人id
	 */
	public Long getCreateUserid() {
		return createUserid;
	}

	/**
	 * 创建人id
	 * @param createUserid 创建人id
	 */
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}
	/**
	 * 创建人姓名
	 * @return 创建人姓名
	 */
	public String getCreateUsername() {
		return createUsername;
	}

	/**
	 * 创建人姓名
	 * @param createUsername 创建人姓名
	 */
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}
	/**
	 * 创建人IP
	 * @return 创建人IP
	 */
	public String getCreateUserip() {
		return createUserip;
	}

	/**
	 * 创建人IP
	 * @param createUserip 创建人IP
	 */
	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}
	/**
	 * 创建人MAC地址
	 * @return 创建人MAC地址
	 */
	public String getCreateUsermac() {
		return createUsermac;
	}

	/**
	 * 创建人MAC地址
	 * @param createUsermac 创建人MAC地址
	 */
	public void setCreateUsermac(String createUsermac) {
		this.createUsermac = createUsermac;
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
	