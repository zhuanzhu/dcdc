package com.egeo.components.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-06-08 11:21:08
 */
public class SoThirdpartyVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date thirdpartyPayTime;
	private BigDecimal thirdpartyPayAmount;
	private Long id;
	/**
	 * 子订单id
	 */
	private Long soChildId;
	/**
	 * 第三订单id
	 */
	private String thirdpartyId;
	/**
	 * 第三方订单状态   0:处理中  1:成功  2:失败
	 */
	private Integer thirdpartyStatus;
	/**
	 * 用户姓名
	 */
	private String userName;
	/**
	 * 身份证号
	 */
	private String idCardNum;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 油卡号
	 */
	private String oidCardNum;
	/**
	 * 备注
	 */
	private String comment;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

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
	 * 子订单id
	 * @return 子订单id
	 */
	public Long getSoChildId() {
		return soChildId;
	}

	/**
	 * 子订单id
	 * @param soChildId 子订单id
	 */
	public void setSoChildId(Long soChildId) {
		this.soChildId = soChildId;
	}	
	/**
	 * 第三订单id
	 * @return 第三订单id
	 */
	public String getThirdpartyId() {
		return thirdpartyId;
	}

	/**
	 * 第三订单id
	 * @param thirdpartyId 第三订单id
	 */
	public void setThirdpartyId(String thirdpartyId) {
		this.thirdpartyId = thirdpartyId;
	}	
	/**
	 * 第三方订单状态   0:处理中  1:成功  2:失败
	 * @return 第三方订单状态   0:处理中  1:成功  2:失败
	 */
	public Integer getThirdpartyStatus() {
		return thirdpartyStatus;
	}

	/**
	 * 第三方订单状态   0:处理中  1:成功  2:失败
	 * @param thirdpartyStatus 第三方订单状态   0:处理中  1:成功  2:失败
	 */
	public void setThirdpartyStatus(Integer thirdpartyStatus) {
		this.thirdpartyStatus = thirdpartyStatus;
	}	
	/**
	 * 用户姓名
	 * @return 用户姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用户姓名
	 * @param userName 用户姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	/**
	 * 身份证号
	 * @return 身份证号
	 */
	public String getIdCardNum() {
		return idCardNum;
	}

	/**
	 * 身份证号
	 * @param idCardNum 身份证号
	 */
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}	
	/**
	 * 手机号码
	 * @return 手机号码
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 手机号码
	 * @param phone 手机号码
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	/**
	 * 油卡号
	 * @return 油卡号
	 */
	public String getOidCardNum() {
		return oidCardNum;
	}

	/**
	 * 油卡号
	 * @param oidCardNum 油卡号
	 */
	public void setOidCardNum(String oidCardNum) {
		this.oidCardNum = oidCardNum;
	}	
	/**
	 * 备注
	 * @return 备注
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * 备注
	 * @param comment 备注
	 */
	public void setComment(String comment) {
		this.comment = comment;
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

	public Date getThirdpartyPayTime() {
		return thirdpartyPayTime;
	}

	public void setThirdpartyPayTime(Date thirdpartyPayTime) {
		this.thirdpartyPayTime = thirdpartyPayTime;
	}

	public BigDecimal getThirdpartyPayAmount() {
		return thirdpartyPayAmount;
	}

	public void setThirdpartyPayAmount(BigDecimal thirdpartyPayAmount) {
		this.thirdpartyPayAmount = thirdpartyPayAmount;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
	 */
	public void setUpdateTime(Date updateTime) {

		this.updateTime = updateTime;
	}	
}
	