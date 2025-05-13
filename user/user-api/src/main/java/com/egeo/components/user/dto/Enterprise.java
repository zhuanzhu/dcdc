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
public class Enterprise {

	private Long id;
	private String name;
	private String address;
	private String contact;
	private String area;
	private Integer auditStatus;
	private Integer cooperationState;
	private Long adminId;
	private String adminLoginName;
	private String pwd;
	private String adminAccount;
	private String mail;
	private Date createMillis;
	private Date updateMillis;
	
	public String getAdminLoginName() {
		return adminLoginName;
	}
	public void setAdminLoginName(String adminLoginName) {
		this.adminLoginName = adminLoginName;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAdminAccount() {
		return adminAccount;
	}
	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}
	
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public void setAudit_status(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getCooperationState() {
		return cooperationState;
	}
	public void setCooperationState(Integer cooperationState) {
		this.cooperationState = cooperationState;
	}
	public void setCooperation_state(Integer cooperationState) {
		this.cooperationState = cooperationState;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public void setAdmin_id(Long adminId) {
		this.adminId = adminId;
	}
	public Date getCreateMillis() {
		return createMillis;
	}
	public void setCreateMillis(Date createMillis) {
		this.createMillis = createMillis;
	}
	public void setCreate_millis(Date createMillis) {
		this.createMillis = createMillis;
	}
	public Date getUpdateMillis() {
		return updateMillis;
	}
	public void setUpdateMillis(Date updateMillis) {
		this.updateMillis = updateMillis;
	}
	public void setUpdate_millis(Date updateMillis) {
		this.updateMillis = updateMillis;
	}



}
