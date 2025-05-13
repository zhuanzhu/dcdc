package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:51
 */
public class TemplatePO {


	private Long id;

	/**
	 * 模板名
	 */
	private String name;	

	/**
	 * 0:启用 1:停用
	 */
	private Integer status;	

	/**
	 * 客户端类型 0:app 1:微信 2:小程序
	 */
	private Integer clientType;	

	/**
	 * 安卓客户端版本编号
	 */
	private Integer clientVersionA;	

	/**
	 * ios版本编号
	 */
	private Integer clientVersionI;	

	/**
	 * 备注
	 */
	private String remark;	

	/**
	 * 类型 0:商城 1:应用
	 */
	private Integer type;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 修改时间
	 */
	private Date updateTime;
	
	private Integer showFgj;
	/**
	 * 公司类型 0:正式公司(默认值) 1:测试公司 2:竞品公司
	 */
	private Integer companyType;
	/**
	 * 平台id
	 */
	private Long platformId;
	
	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Integer getShowFgj() {
		return showFgj;
	}

	public void setShowFgj(Integer showFgj) {
		this.showFgj = showFgj;
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
	 * 模板名
	 * @return 模板名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 模板名
	 * @param name 模板名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 0:启用 1:停用
	 * @return 0:启用 1:停用
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 0:启用 1:停用
	 * @param status 0:启用 1:停用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 客户端类型 0:app 1:微信 2:小程序
	 * @return 客户端类型 0:app 1:微信 2:小程序
	 */
	public Integer getClientType() {
		return clientType;
	}

	/**
	 * 客户端类型 0:app 1:微信 2:小程序
	 * @param clientType 客户端类型 0:app 1:微信 2:小程序
	 */
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	/**
	 * 安卓客户端版本编号
	 * @return 安卓客户端版本编号
	 */
	public Integer getClientVersionA() {
		return clientVersionA;
	}

	/**
	 * 安卓客户端版本编号
	 * @param clientVersionA 安卓客户端版本编号
	 */
	public void setClientVersionA(Integer clientVersionA) {
		this.clientVersionA = clientVersionA;
	}

	/**
	 * ios版本编号
	 * @return ios版本编号
	 */
	public Integer getClientVersionI() {
		return clientVersionI;
	}

	/**
	 * ios版本编号
	 * @param clientVersionI ios版本编号
	 */
	public void setClientVersionI(Integer clientVersionI) {
		this.clientVersionI = clientVersionI;
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
	 * 类型 0:商城 1:应用
	 * @return 类型 0:商城 1:应用
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型 0:商城 1:应用
	 * @param type 类型 0:商城 1:应用
	 */
	public void setType(Integer type) {
		this.type = type;
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
	 * 修改时间
	 * @return 修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间
	 * @param updateTime 修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
	