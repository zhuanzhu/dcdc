package com.egeo.components.user.vo;


public class StaffQuitVO {
	
	

	/**
	 * 员工编号
	 */
	private String memberCode;
	/**
	 * 真实姓名
	 */
	private String name;
	/**
	 * 员工邮箱
	 */
	private String mail;
	/**
	 * 离职日期
	 */
	private String quitTime;
	/**
	 * 失效日期
	 */
	private String invalidTime;
	
	
	/**
	 * 用户id
	 */
	private Long UserId;
	
	
	public Long getUserId() {
		return UserId;
	}
	public void setUserId(Long userId) {
		UserId = userId;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getQuitTime() {
		return quitTime;
	}
	public void setQuitTime(String quitTime) {
		this.quitTime = quitTime;
	}
	public String getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}
	
	
	
}
