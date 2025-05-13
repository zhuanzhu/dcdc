package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.egeo.components.product.dto.AttValueDTO;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:41:07
 */
public class AttributeNameVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */

	private String name;		
	/**
	 * 1、产品属性 2、商品规格属性、基本属性
	 */
	private Integer type;	
	/**
	 * 规格属性:0否1是
	 */
	private Integer specificationProperty;	
	/**
	 * 参数属性：0否1是
	 */
	private Integer parameterProperty;	 
	/**
	 * 平台id
	 */

	private Long platformId;		 
	/**
	 * 属性值填写方式：1是下拉单选框,2文本输入框，3列表复选框，4日期型、5整型、6数字型
	 */

	private Integer mode;		 
	/**
	 * 浮点型开始范围
	 */
	private String begin;	

	/**
	 * 浮点型结束范围
	 */
	private String finish;	
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */

	private Date createTime;		 
	/**
	 * 修改时间:更新时数据库会自动set值
	 */

	private Date updateTime;		 

	private List<AttValueDTO> lists;
	/**
	 * 输入提示
	 */
	private String importHint;
	/**
	 * 提示
	 */
	private String reminder;
	/**
	 * 
	 */
	private Long categoryId;
	//根据类目id和属性id查询类目属性是否必填写 0否、1是
	private int isRequired;
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
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
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
	 * 属性值填写方式：1是下拉单选框,2文本输入框，3列表复选框，4日期型、5整型、6数字型
	 * @return 属性值填写方式：1是下拉单选框,2文本输入框，3列表复选框，4日期型、5整型、6数字型
	 */
	public Integer getMode() {
		return mode;
	}

	/**
	 * 属性值填写方式：1是下拉单选框,2文本输入框，3列表复选框，4日期型、5整型、6数字型
	 * @param mode 属性值填写方式：1是下拉单选框,2文本输入框，3列表复选框，4日期型、5整型、6数字型
	 */
	public void setMode(Integer mode) {
		this.mode = mode;
	}	
	public List<AttValueDTO> getLists() {
		return lists;
	}

	public void setLists(List<AttValueDTO> lists) {
		this.lists = lists;
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

	public String getBegin() {
		return begin;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getImportHint() {
		return importHint;
	}

	public void setImportHint(String importHint) {
		this.importHint = importHint;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public int getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(int isRequired) {
		this.isRequired = isRequired;
	}

	public Integer getSpecificationProperty() {
		return specificationProperty;
	}

	public void setSpecificationProperty(Integer specificationProperty) {
		this.specificationProperty = specificationProperty;
	}

	public Integer getParameterProperty() {
		return parameterProperty;
	}

	public void setParameterProperty(Integer parameterProperty) {
		this.parameterProperty = parameterProperty;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
	
}
	