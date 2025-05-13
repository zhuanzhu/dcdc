package com.egeo.components.user.dto;

import java.util.Map;

public class InsertAndSendMessageDTO {
	private Long infoTemplateId; 
	private Map<String, String> params; 
	private Long userId; 
	private String mobile;
	public InsertAndSendMessageDTO(Long infoTemplateId, Map<String, String> params, Long userId, String mobile){
		this.infoTemplateId = infoTemplateId;
		this.params = params;
		this.userId = userId;
		this.mobile = mobile;
	}
	public InsertAndSendMessageDTO() {
		
	}
	public Long getInfoTemplateId() {
		return infoTemplateId;
	}
	public void setInfoTemplateId(Long infoTemplateId) {
		this.infoTemplateId = infoTemplateId;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
