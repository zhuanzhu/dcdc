package com.egeo.components.config.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xia
 * @date 2018-05-25 10:21:14
 */
public class LogInfoVO extends LogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String oper;	// 操作
	private Integer operType;	// 操作类型  0:增加  1: 修改  2:删除
	private String pageName;	// 页面名称
	private String module;	// 功能模块
	private Long pageNameId;
	private Long moduleId;
	private Long startTime;
	private Long endTime;
	
	private Object content;
	
	/**
	 * 消息内容
	 * @return 消息内容
	 */
	public Object getContent() {
		return content;
	}

	/**
	 * 消息内容
	 * @param msgContent 消息内容
	 */
	public void setContent(Object content) {
		this.content = content;
	}	
	
	
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
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	
	
}
	