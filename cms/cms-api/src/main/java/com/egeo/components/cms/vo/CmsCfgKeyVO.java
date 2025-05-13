package com.egeo.components.cms.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tan
 * @date 2018-12-14 13:47:50
 */
public class CmsCfgKeyVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */
	private Long elementId;
	/**
	 * 配置项名称
	 */
	private String name;
	/**
	 * 配置项代码
	 */
	private String code;
	/**
	 * 文本类型 0:Banner图（单个），1：下拉框 ,2 ： 文本框，3:轮播图（多个）
	 */
	private Integer type;
	/**
	 * 配置项说明
	 */
	private Integer sort;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 可选值列表
	 */
	private List<Map<String, Object>> cfgValueList;
	/**
	 * 模板ID
	 */
	private Long templateId;
	/**
	 * 单位
	 */
	private String units;
	/**
	 * 配置项默认值
	 */
	private String defaultValue;
	/**
	 * 配置项格式验证正则
	 */
	private String verifyRegex;/**
	 * 配置项分组
	 */
	private String cfgGroup;
	/**
	 * 配置项描述
	 */
	private String description;
	/**
	 * 配置项类型 1-基础属性
	 */
	private Integer cfgKind;
	/**
	 * 上级属性ID
	 */
	private Long parentId;
	/**
	 * 配置项标签（页面展示备注用）
	 */
	private String label;
	/**
	 * 是否强制属性（0-否 1-是）
	 */
	private Integer mandatory;
	/**
	 * json形式存储条件字段列表，例：[{"leftVar":"左变量（$1表示配置项1的值，1表示字符串1）","operator":"运算符（eq/ne）","rightVar":"右变量"}]
	 */
	private String mandatoryCondition;

	public String getMandatoryCondition() {
		return mandatoryCondition;
	}

	public void setMandatoryCondition(String mandatoryCondition) {
		this.mandatoryCondition = mandatoryCondition;
	}

	public Integer getMandatory() {
		return mandatory;
	}

	public void setMandatory(Integer mandatory) {
		this.mandatory = mandatory;
	}

	public String getCfgGroup() {
		return cfgGroup;
	}

	public void setCfgGroup(String cfgGroup) {
		this.cfgGroup = cfgGroup;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCfgKind() {
		return cfgKind;
	}

	public void setCfgKind(Integer cfgKind) {
		this.cfgKind = cfgKind;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getVerifyRegex() {
		return verifyRegex;
	}

	public void setVerifyRegex(String verifyRegex) {
		this.verifyRegex = verifyRegex;
	}
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public List<Map<String, Object>> getCfgValueList() {
		return cfgValueList;
	}

	public void setCfgValueList(List<Map<String, Object>> cfgValueList) {
		this.cfgValueList = cfgValueList;
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
	 * 
	 * @return 
	 */
	public Long getElementId() {
		return elementId;
	}

	/**
	 * 
	 * @param elementId 
	 */
	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}	
	/**
	 * 配置项名称
	 * @return 配置项名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 配置项名称
	 * @param name 配置项名称
	 */
	public void setName(String name) {
		this.name = name;
	}	
	/**
	 * 配置项代码
	 * @return 配置项代码
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 配置项代码
	 * @param code 配置项代码
	 */
	public void setCode(String code) {
		this.code = code;
	}	
	/**
	 * 文本类型 0:Banner图（单个），1：下拉框 ,2 ： 文本框，3:轮播图（多个）
	 * @return 文本类型 0:Banner图（单个），1：下拉框 ,2 ： 文本框，3:轮播图（多个）
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 文本类型 0:Banner图（单个），1：下拉框 ,2 ： 文本框，3:轮播图（多个）
	 * @param type 文本类型 0:Banner图（单个），1：下拉框 ,2 ： 文本框，3:轮播图（多个）
	 */
	public void setType(Integer type) {
		this.type = type;
	}	
	/**
	 * 配置项说明
	 * @return 配置项说明
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 配置项说明
	 * @param sort 配置项说明
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}	
	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}	
	/**
	 * 
	 * @return 
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 
	 * @param updateTime 
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
}
	