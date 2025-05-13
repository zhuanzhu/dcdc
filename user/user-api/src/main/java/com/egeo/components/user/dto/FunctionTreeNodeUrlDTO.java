package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-11-13 11:50:21
 */
public class FunctionTreeNodeUrlDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * url ID
	 */
	private Long urlId;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 功能树节点ID
	 */
	private Long functionTreeNodeId;	

	/**
	 * 最后操作用户ID
	 */
	private Long updateUserId;	

	/**
	 * 最后操作用户名称
	 */
	private String updateUserName;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	
	
	private String functionName;
	
	private String urlName;
	
	private String url;

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	 * url ID
	 * @return url ID
	 */
	public Long getUrlId() {
		return urlId;
	}

	/**
	 * url ID
	 * @param urlId url ID
	 */
	public void setUrlId(Long urlId) {
		this.urlId = urlId;
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
	 * 功能树节点ID
	 * @return 功能树节点ID
	 */
	public Long getFunctionTreeNodeId() {
		return functionTreeNodeId;
	}

	/**
	 * 功能树节点ID
	 * @param functionTreeNodeId 功能树节点ID
	 */
	public void setFunctionTreeNodeId(Long functionTreeNodeId) {
		this.functionTreeNodeId = functionTreeNodeId;
	}
	/**
	 * 最后操作用户ID
	 * @return 最后操作用户ID
	 */
	public Long getUpdateUserId() {
		return updateUserId;
	}

	/**
	 * 最后操作用户ID
	 * @param updateUserId 最后操作用户ID
	 */
	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	/**
	 * 最后操作用户名称
	 * @return 最后操作用户名称
	 */
	public String getUpdateUserName() {
		return updateUserName;
	}

	/**
	 * 最后操作用户名称
	 * @param updateUserName 最后操作用户名称
	 */
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
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
}
	