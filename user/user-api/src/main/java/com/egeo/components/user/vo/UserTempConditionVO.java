package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

public class UserTempConditionVO extends UserTempVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 导入类型
	 */
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 出生年月日
	 */
	private Date birthday;	

	/**
	 * 手机
	 */
	private String mobile;	
	
	/**
	 * 密码
	 */
	private String password;
	
	/**
	 * 当前用户id
	 */
	private Long currUserId;
	
	
	/**
	 * 是否管理员 0：否    1：是
	 */
	private Integer isAdministrator;
	/**
	 * 活动短码
	 */
	private String campaignCode;
	/**
	 * 注册门店码
	 */
	private String registerShopCode;
	private String loginName;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCampaignCode() {
		return campaignCode;
	}


	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}


	public String getRegisterShopCode() {
		return registerShopCode;
	}


	public void setRegisterShopCode(String registerShopCode) {
		this.registerShopCode = registerShopCode;
	}


	public Integer getSex() {
		return sex;
	}


	public void setSex(Integer sex) {
		this.sex = sex;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Long birthday) {
		this.birthday = new Date(birthday);
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getIsAdministrator() {
		return isAdministrator;
	}


	public void setIsAdministrator(Integer isAdministrator) {
		this.isAdministrator = isAdministrator;
	}


	public Long getCurrUserId() {
		return currUserId;
	}


	public void setCurrUserId(Long currUserId) {
		this.currUserId = currUserId;
	}
	
	
	
}
