package com.egeo.components.user.dto;

import java.io.Serializable;

public class UserWelfareApp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7550375948152272531L;
	/**
	 * 用户id
	 */
	private Long id;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 头像
	 */
	private String headPicUrl;
	/**
	 * 手机
	 */
	private String mobile;	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadPicUrl() {
		return headPicUrl;
	}
	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
