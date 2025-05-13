package com.egeo.components.order.vo;

import java.io.Serializable;

/**
 * 
 * @author ghw
 * @date 2018-02-07 09:26:27
 */
public class SoCustomerServiceVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 子订单的id
	 */
	private Long soChildId;
	
	private String childCode;
	
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 客服备注
	 */
	private String operateRemark;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 母订单id
	 */
	private Long soId;
	/**
	 * 用户备注
	 */
	private String userRemark;

	public String getChildCode() {
		return childCode;
	}

	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 唯一主键
	 * @param id 唯一主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 子订单的id
	 * @return 子订单的id
	 */
	public Long getSoChildId() {
		return soChildId;
	}

	/**
	 * 子订单的id
	 * @param soChildId 子订单的id
	 */
	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
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
	 * 客服备注
	 * @return 客服备注
	 */
	public String getOperateRemark() {
		return operateRemark;
	}

	/**
	 * 客服备注
	 * @param operateRemark 客服备注
	 */
	public void setOperateRemark(String operateRemark) {
		this.operateRemark = operateRemark;
	}	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 母订单id
	 * @return 母订单id
	 */
	public Long getSoId() {
		return soId;
	}

	/**
	 * 母订单id
	 * @param soId 母订单id
	 */
	public void setSoId(Long soId) {
		this.soId = soId;
	}	
	/**
	 * 用户备注
	 * @return 用户备注
	 */
	public String getUserRemark() {
		return userRemark;
	}

	/**
	 * 用户备注
	 * @param userRemark 用户备注
	 */
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}	
}
	