package com.egeo.components.order.po;


import java.util.Date;

/**
 * 
 * @author ghw
 * @date 2018-02-07 09:26:26
 */
public class SoCustomerServicePO {


	private Long id;

	/**
	 * 子订单的id
	 */
	private Long soChildId;	

	/**
	 * 用户名
	 */
	private String userName;	

	/**
	 * 客服备注
	 */
	private String operateRemark;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date creatTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	

	/**
	 * 母订单id
	 */
	private Long soId;	

	/**
	 * 用户备注
	 */
	private String userRemark;	

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

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreatTime() {
		return creatTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param creatTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
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
	