package com.egeo.components.user.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



/**
 * 登录user
 * 
 * @author zhou_k 2017年5月26日
 */
public class WxOpenidDTO {

	private Long id;
	private Long userId;
	private String wxAppid;
	private String wxOpenid;
	private Long companyId;
	private Date createTime;
	private Date updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getWxAppid() {
		return wxAppid;
	}
	public void setWxAppid(String wxAppid) {
		this.wxAppid = wxAppid;
	}
	public String getWxOpenid() {
		return wxOpenid;
	}
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
