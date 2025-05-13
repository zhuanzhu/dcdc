package com.egeo.components.config.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xia
 * @date 2018-05-25 10:21:14
 */
public class LogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 消息号:日志数据字典的id
	 */
	private Long msgId;
	/**
	 * 消息内容
	 */
	private String msgContent;
	/**
	 * 操作人姓名
	 */
	private String operatorName;
	/**
	 * 操作人Id
	 */
	private Long operatorId;
	/**
	 * 操作对象
	 */
	private String operObject;
	/**
	 * 模块名
	 */
	private String moduleName;
	/**
	 * 操作时间，自动set值
	 */
	private Date time;
	/**
	 * 操作Ip
	 */
	private String ip;
	/**
	 * 客户端类型:0、安卓 1、ios 2、web端
	 */
	private Integer clientType;
	/**
	 * 移动端版本号
	 */
	private String mobileVersion;
	/**
	 * 操作对象id
	 */
	private Long operatorObjId;
	/**
	 * 操作对象名称
	 */
	private String operatorObjName;
	/**
	 * 操作对象编号
	 */
	private String operatorObjCode;
	/**
	 * 日志类型
	 */
	private Integer type;
	/**
	 * 平台id
	 */
	private Long platformId;

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id 
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 消息号:日志数据字典的id
	 * @return 消息号:日志数据字典的id
	 */
	public Long getMsgId() {
		return msgId;
	}

	/**
	 * 消息号:日志数据字典的id
	 * @param msgId 消息号:日志数据字典的id
	 */
	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}	
	/**
	 * 消息内容
	 * @return 消息内容
	 */
	public String getMsgContent() {
		return msgContent;
	}

	/**
	 * 消息内容
	 * @param msgContent 消息内容
	 */
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}	
	/**
	 * 操作人姓名
	 * @return 操作人姓名
	 */
	public String getOperatorName() {
		return operatorName;
	}

	/**
	 * 操作人姓名
	 * @param operatorName 操作人姓名
	 */
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}	
	/**
	 * 操作人Id
	 * @return 操作人Id
	 */
	public Long getOperatorId() {
		return operatorId;
	}

	/**
	 * 操作人Id
	 * @param operatorId 操作人Id
	 */
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}	
	/**
	 * 操作对象
	 * @return 操作对象
	 */
	public String getOperObject() {
		return operObject;
	}

	/**
	 * 操作对象
	 * @param operObject 操作对象
	 */
	public void setOperObject(String operObject) {
		this.operObject = operObject;
	}	
	/**
	 * 模块名
	 * @return 模块名
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * 模块名
	 * @param moduleName 模块名
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}	
	/**
	 * 操作时间，自动set值
	 * @return 操作时间，自动set值
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * 操作时间，自动set值
	 * @param time 操作时间，自动set值
	 */
	public void setTime(Date time) {
		this.time = time;
	}	
	/**
	 * 操作Ip
	 * @return 操作Ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 操作Ip
	 * @param ip 操作Ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}	
	/**
	 * 客户端类型:0、安卓 1、ios 2、web端
	 * @return 客户端类型:0、安卓 1、ios 2、web端
	 */
	public Integer getClientType() {
		return clientType;
	}

	/**
	 * 客户端类型:0、安卓 1、ios 2、web端
	 * @param clientType 客户端类型:0、安卓 1、ios 2、web端
	 */
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}	
	/**
	 * 移动端版本号
	 * @return 移动端版本号
	 */
	public String getMobileVersion() {
		return mobileVersion;
	}

	/**
	 * 移动端版本号
	 * @param mobileVersion 移动端版本号
	 */
	public void setMobileVersion(String mobileVersion) {
		this.mobileVersion = mobileVersion;
	}	
	/**
	 * 操作对象id
	 * @return 操作对象id
	 */
	public Long getOperatorObjId() {
		return operatorObjId;
	}

	/**
	 * 操作对象id
	 * @param operatorObjId 操作对象id
	 */
	public void setOperatorObjId(Long operatorObjId) {
		this.operatorObjId = operatorObjId;
	}	
	/**
	 * 操作对象名称
	 * @return 操作对象名称
	 */
	public String getOperatorObjName() {
		return operatorObjName;
	}

	/**
	 * 操作对象名称
	 * @param operatorObjName 操作对象名称
	 */
	public void setOperatorObjName(String operatorObjName) {
		this.operatorObjName = operatorObjName;
	}	
	/**
	 * 操作对象编号
	 * @return 操作对象编号
	 */
	public String getOperatorObjCode() {
		return operatorObjCode;
	}

	/**
	 * 操作对象编号
	 * @param operatorObjCode 操作对象编号
	 */
	public void setOperatorObjCode(String operatorObjCode) {
		this.operatorObjCode = operatorObjCode;
	}	
}
	