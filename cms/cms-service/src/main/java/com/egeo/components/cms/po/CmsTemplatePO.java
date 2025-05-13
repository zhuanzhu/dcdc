package com.egeo.components.cms.po;


import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-12-13 16:58:47
 */
public class CmsTemplatePO {


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
	 * 客户端类型 1:app 2:微信 3:小程序 4:web商城 5:PC端 6:移动端
	 */
	private Integer clientType;	

	/**
	 * 安卓客户端版本编号ID -1:全部
	 */
	private Long clientVersionAId;	

	/**
	 * ios版本编号ID -1:全部
	 */
	private Long clientVersionIId;	

	/**
	 * 安卓客户端版本名称
	 */
	private String clientVersionARemark;	

	/**
	 * ios客户端版本名称
	 */
	private String clientVersionIRemark;	

	/**
	 * 安卓客户端版本名称
	 */
	private Integer clientVersionACode;	

	/**
	 * ios客户端版本名称
	 */
	private Integer clientVersionICode;	

	/**
	 * 备注
	 */
	private String remark;	

	/**
	 * 类型 0:商城 1:应用 2:商品列表页
	 */
	private Integer type;	

	/**
	 * 是否显示福管家logo 0:否 1:是
	 */
	private Integer showFgj;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 修改时间
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
	 * 客户端类型 1:app 2:微信 3:小程序 4:web商城 5:PC端 6:移动端
	 * @return 客户端类型 1:app 2:微信 3:小程序 4:web商城 5:PC端 6:移动端
	 */
	public Integer getClientType() {
		return clientType;
	}

	/**
	 * 客户端类型 1:app 2:微信 3:小程序 4:web商城 5:PC端 6:移动端
	 * @param clientType 客户端类型 1:app 2:微信 3:小程序 4:web商城 5:PC端 6:移动端
	 */
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	/**
	 * 安卓客户端版本编号ID -1:全部
	 * @return 安卓客户端版本编号ID -1:全部
	 */
	public Long getClientVersionAId() {
		return clientVersionAId;
	}

	/**
	 * 安卓客户端版本编号ID -1:全部
	 * @param clientVersionAId 安卓客户端版本编号ID -1:全部
	 */
	public void setClientVersionAId(Long clientVersionAId) {
		this.clientVersionAId = clientVersionAId;
	}

	/**
	 * ios版本编号ID -1:全部
	 * @return ios版本编号ID -1:全部
	 */
	public Long getClientVersionIId() {
		return clientVersionIId;
	}

	/**
	 * ios版本编号ID -1:全部
	 * @param clientVersionIId ios版本编号ID -1:全部
	 */
	public void setClientVersionIId(Long clientVersionIId) {
		this.clientVersionIId = clientVersionIId;
	}

	/**
	 * 安卓客户端版本名称
	 * @return 安卓客户端版本名称
	 */
	public String getClientVersionARemark() {
		return clientVersionARemark;
	}

	/**
	 * 安卓客户端版本名称
	 * @param clientVersionARemark 安卓客户端版本名称
	 */
	public void setClientVersionARemark(String clientVersionARemark) {
		this.clientVersionARemark = clientVersionARemark;
	}

	/**
	 * ios客户端版本名称
	 * @return ios客户端版本名称
	 */
	public String getClientVersionIRemark() {
		return clientVersionIRemark;
	}

	/**
	 * ios客户端版本名称
	 * @param clientVersionIRemark ios客户端版本名称
	 */
	public void setClientVersionIRemark(String clientVersionIRemark) {
		this.clientVersionIRemark = clientVersionIRemark;
	}

	
	public Integer getClientVersionACode() {
		return clientVersionACode;
	}

	public void setClientVersionACode(Integer clientVersionACode) {
		this.clientVersionACode = clientVersionACode;
	}

	public Integer getClientVersionICode() {
		return clientVersionICode;
	}

	public void setClientVersionICode(Integer clientVersionICode) {
		this.clientVersionICode = clientVersionICode;
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
	 * 类型 0:商城 1:应用 2:商品列表页
	 * @return 类型 0:商城 1:应用 2:商品列表页
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 类型 0:商城 1:应用 2:商品列表页
	 * @param type 类型 0:商城 1:应用 2:商品列表页
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 是否显示福管家logo 0:否 1:是
	 * @return 是否显示福管家logo 0:否 1:是
	 */
	public Integer getShowFgj() {
		return showFgj;
	}

	/**
	 * 是否显示福管家logo 0:否 1:是
	 * @param showFgj 是否显示福管家logo 0:否 1:是
	 */
	public void setShowFgj(Integer showFgj) {
		this.showFgj = showFgj;
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
	