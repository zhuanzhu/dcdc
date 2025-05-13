package com.egeo.components.config.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xia
 * @date 2018-05-25 10:21:14
 */
public class LogInfoDTO extends LogDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String oper;	// 操作
	private Integer operType;	// 操作类型  0:增加  1: 修改  2:删除
	private String pageName;	// 页面名称
	private String module;	// 功能模块
	private Long pageNameId;
	private Long moduleId;
	private Date startTime;
	private Date endTime;
	
	
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	public Integer getOperType() {
		return operType;
	}
	public void setOperType(Integer operType) {
		this.operType = operType;
	}
	public Long getPageNameId() {
		return pageNameId;
	}
	public void setPageNameId(Long pageNameId) {
		this.pageNameId = pageNameId;
	}
	public Long getModuleId() {
		return moduleId;
	}
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
}
	