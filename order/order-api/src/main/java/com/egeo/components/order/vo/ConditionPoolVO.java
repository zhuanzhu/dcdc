package com.egeo.components.order.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:00:00
 */
public class ConditionPoolVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 菜单code
	 */

	private String menuCode;		 
	/**
	 * 菜单名称
	 */

	private String menuName;		 
	/**
	 * 页面名称
	 */

	private String pageName;		 
	/**
	 * 字段中文名称
	 */

	private String conditionName;		 
	/**
	 * 字段名称
	 */

	private String conditionValue;		 
	/**
	 * 条件类型(1.查询条件/2导出/3查询结果集列)
	 */

	private String conditionType;		 
	/**
	 * 字段使用规则JSON格式 (数据格式校验、class等等、div布局等)规则
	 */

	private String rule;		 
	/**
	 * 平台id
	 */

	private Long platformId;		 
	/**
	 * 列表bodyDOM元素
	 */

	private String tbodyValue;		 
	/**
	 * 是否系统默认配置条件
	 */

	private Integer isDft;		 
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
	 * 菜单code
	 * @return 菜单code
	 */
	public String getMenuCode() {
		return menuCode;
	}

	/**
	 * 菜单code
	 * @param menuCode 菜单code
	 */
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}	
	/**
	 * 菜单名称
	 * @return 菜单名称
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * 菜单名称
	 * @param menuName 菜单名称
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}	
	/**
	 * 页面名称
	 * @return 页面名称
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * 页面名称
	 * @param pageName 页面名称
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}	
	/**
	 * 字段中文名称
	 * @return 字段中文名称
	 */
	public String getConditionName() {
		return conditionName;
	}

	/**
	 * 字段中文名称
	 * @param conditionName 字段中文名称
	 */
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}	
	/**
	 * 字段名称
	 * @return 字段名称
	 */
	public String getConditionValue() {
		return conditionValue;
	}

	/**
	 * 字段名称
	 * @param conditionValue 字段名称
	 */
	public void setConditionValue(String conditionValue) {
		this.conditionValue = conditionValue;
	}	
	/**
	 * 条件类型(1.查询条件/2导出/3查询结果集列)
	 * @return 条件类型(1.查询条件/2导出/3查询结果集列)
	 */
	public String getConditionType() {
		return conditionType;
	}

	/**
	 * 条件类型(1.查询条件/2导出/3查询结果集列)
	 * @param conditionType 条件类型(1.查询条件/2导出/3查询结果集列)
	 */
	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}	
	/**
	 * 字段使用规则JSON格式 (数据格式校验、class等等、div布局等)规则
	 * @return 字段使用规则JSON格式 (数据格式校验、class等等、div布局等)规则
	 */
	public String getRule() {
		return rule;
	}

	/**
	 * 字段使用规则JSON格式 (数据格式校验、class等等、div布局等)规则
	 * @param rule 字段使用规则JSON格式 (数据格式校验、class等等、div布局等)规则
	 */
	public void setRule(String rule) {
		this.rule = rule;
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
	 * 列表bodyDOM元素
	 * @return 列表bodyDOM元素
	 */
	public String getTbodyValue() {
		return tbodyValue;
	}

	/**
	 * 列表bodyDOM元素
	 * @param tbodyValue 列表bodyDOM元素
	 */
	public void setTbodyValue(String tbodyValue) {
		this.tbodyValue = tbodyValue;
	}	
	/**
	 * 是否系统默认配置条件
	 * @return 是否系统默认配置条件
	 */
	public Integer getIsDft() {
		return isDft;
	}

	/**
	 * 是否系统默认配置条件
	 * @param isDft 是否系统默认配置条件
	 */
	public void setIsDft(Integer isDft) {
		this.isDft = isDft;
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
	