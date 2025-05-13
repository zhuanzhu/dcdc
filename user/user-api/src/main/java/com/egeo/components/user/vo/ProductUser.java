package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

public class ProductUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6292042774868076763L;
	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	
	/**
     * 账号
     */
    private String loginName;
    
    /**
	 * 性别
	 */
	private Integer sex;
	
	/**
	 * 出生年月日
	 */
	private Date birthday;
	
	/**
     * 手机
     */
    private String mobile;
    
    /**
     * 邮箱地址
     */
    private String mail;
    
    /**
	 * 用户状态 0:停用 1:启用 2:注销
	 */
	private Integer status;
	
	/**
	 * 
	 */
	private Date createTime;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
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

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
