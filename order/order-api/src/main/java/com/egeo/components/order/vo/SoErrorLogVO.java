package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:00:01
 */
public class SoErrorLogVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 订单编号
	 */

	private String orderCode;		 
	/**
	 * 操作类型 ：1 库存 2 促销 3 礼金卡
	 */

	private Integer type;		 
	/**
	 * 操作人id
	 */

	private Long operId;		 
	/**
	 * 操作人名称
	 */

	private String operName;		 
	/**
	 * 描述
	 */

	private String description;		 
	/**
	 * 描述
	 */

	private String exception;		 
	/**
	 * 异常编码
	 */

	private String errorCode;		 
	/**
	 * 异常信息
	 */

	private String errorMessage;		 
	/**
	 * 入参
	 */

	private String inputParameter;		 
	/**
	 * 出参
	 */

	private String outputParameter;		 
	/**
	 * 平台id
	 */

	private Long platformId;		 
	/**
	 * 修改时间:更新时数据库会自动set值
	 */

	private Date updateTime;		 
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */

	private Date createTime;		 

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
	 * 订单编号
	 * @return 订单编号
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单编号
	 * @param orderCode 订单编号
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}	
	/**
	 * 操作类型 ：1 库存 2 促销 3 礼金卡
	 * @return 操作类型 ：1 库存 2 促销 3 礼金卡
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 操作类型 ：1 库存 2 促销 3 礼金卡
	 * @param type 操作类型 ：1 库存 2 促销 3 礼金卡
	 */
	public void setType(Integer type) {
		this.type = type;
	}	
	/**
	 * 操作人id
	 * @return 操作人id
	 */
	public Long getOperId() {
		return operId;
	}

	/**
	 * 操作人id
	 * @param operId 操作人id
	 */
	public void setOperId(Long operId) {
		this.operId = operId;
	}	
	/**
	 * 操作人名称
	 * @return 操作人名称
	 */
	public String getOperName() {
		return operName;
	}

	/**
	 * 操作人名称
	 * @param operName 操作人名称
	 */
	public void setOperName(String operName) {
		this.operName = operName;
	}	
	/**
	 * 描述
	 * @return 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 描述
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}	
	/**
	 * 描述
	 * @return 描述
	 */
	public String getException() {
		return exception;
	}

	/**
	 * 描述
	 * @param exception 描述
	 */
	public void setException(String exception) {
		this.exception = exception;
	}	
	/**
	 * 异常编码
	 * @return 异常编码
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 异常编码
	 * @param errorCode 异常编码
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}	
	/**
	 * 异常信息
	 * @return 异常信息
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * 异常信息
	 * @param errorMessage 异常信息
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
	/**
	 * 入参
	 * @return 入参
	 */
	public String getInputParameter() {
		return inputParameter;
	}

	/**
	 * 入参
	 * @param inputParameter 入参
	 */
	public void setInputParameter(String inputParameter) {
		this.inputParameter = inputParameter;
	}	
	/**
	 * 出参
	 * @return 出参
	 */
	public String getOutputParameter() {
		return outputParameter;
	}

	/**
	 * 出参
	 * @param outputParameter 出参
	 */
	public void setOutputParameter(String outputParameter) {
		this.outputParameter = outputParameter;
	}	
	/**
	 * 平台id
	 * @return 平台id
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台id
	 * @param platformId 平台id
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}	
	/**
	 * 修改时间:更新时数据库会自动set值
	 * @return 修改时间:更新时数据库会自动set值
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间:更新时数据库会自动set值
	 * @param updateTime 修改时间:更新时数据库会自动set值
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @param createTime 创建时间:创建记录时数据库会自动set值
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
}
	