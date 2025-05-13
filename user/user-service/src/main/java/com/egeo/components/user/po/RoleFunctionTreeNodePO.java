package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author tan
 * @date 2018-11-13 17:08:59
 */
public class RoleFunctionTreeNodePO {


	private Long id;

	/**
	 * 角色ID
	 */
	private Long roleId;	

	/**
	 * 功能树节点ID
	 */
	private Long functionTreeNodeId;	

	/**
	 * 是否全选 0-否 1-是
	 */
	private Integer fullCheck;	

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

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

	/**
	 * 功能模块ID
	 */
	/*private Long functionModuleId;*/

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
	 * 角色ID
	 * @return 角色ID
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * 角色ID
	 * @param roleId 角色ID
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	 * 是否全选 0-否 1-是
	 * @return 是否全选 0-否 1-是
	 */
	public Integer getFullCheck() {
		return fullCheck;
	}

	/**
	 * 是否全选 0-否 1-是
	 * @param fullCheck 是否全选 0-否 1-是
	 */
	public void setFullCheck(Integer fullCheck) {
		this.fullCheck = fullCheck;
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

	/**
	 * 功能模块ID
	 * @return 功能模块ID
	 */
	/*public Long getFunctionModuleId() {
		return functionModuleId;
	}*/

	/**
	 * 功能模块ID
	 * @param functionModuleId 功能模块ID
	 */
	/*public void setFunctionModuleId(Long functionModuleId) {
		this.functionModuleId = functionModuleId;
	}*/
}
	