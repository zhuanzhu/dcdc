package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-12-13 17:01:38
 */
public class CmsElementDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 组件名
	 */
	private String name;	

	/**
	 * 组件编码
	 */
	private String code;	

	/**
	 * 组件分组类型 0:商品列表页组件
	 */
	private Integer groupType;	

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
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * 组件顺序
	 */
	private Integer sort;	
	
	private String sampleImgUrl;

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
	 * 组件名
	 * @return 组件名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 组件名
	 * @param name 组件名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 组件编码
	 * @return 组件编码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 组件编码
	 * @param code 组件编码
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * 组件顺序
	 * @return 组件顺序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 组件顺序
	 * @param sort 组件顺序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getGroupType() {
		return groupType;
	}

	public void setGroupType(Integer groupType) {
		this.groupType = groupType;
	}

	public String getSampleImgUrl() {
		return sampleImgUrl;
	}

	public void setSampleImgUrl(String sampleImgUrl) {
		this.sampleImgUrl = sampleImgUrl;
	}
	
}
	