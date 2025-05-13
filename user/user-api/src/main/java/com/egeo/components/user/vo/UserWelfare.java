package com.egeo.components.user.vo;

import java.io.Serializable;

public class UserWelfare implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1507981286100386397L;
	private Long userId;//用户id
	private String name;//用户名称
	private String sex;//用户性别
	private String birthday;//用户出生日期
	private String mobile;//用户手机
	private String entryTime;//用户入职日期
	private String mail;//用户邮箱
	private String departmentName;//用户所在部门名称
	private String error;//失败原因
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
	
}
