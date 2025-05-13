package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author min
 * @date 2018-01-05 18:51:02
 */
public class StandardProductUnitAttNameDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 
	 */
	private Long parentId;	

	/**
	 * spu草稿id
	 */
	private Long standardProductUnitId;	

	/**
	 * 
	 */
	private Long attNameId;	
	/**
	 * 属性名称
	 */
	private String attName;

	/**
	 * 
	 */
	private Integer sortValue;	

	/**
	 * 产品属性类型：1、属性 、规格
	 */
	private Integer type;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 属性值填写方式：1是下拉单选框,2文本输入框，3列表复选框，4日期型、5整型、6数字型
	 */
	private int mode;
	/**
	 * 是否显示规格图片：0否1是
	 */
	private Integer showPicture;
	
	private List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList;

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
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 
	 * @param parentId 
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * spu草稿id
	 * @return spu草稿id
	 */
	public Long getStandardProductUnitId() {
		return standardProductUnitId;
	}

	/**
	 * spu草稿id
	 * @param standardProductUnitId spu草稿id
	 */
	public void setStandardProductUnitId(Long standardProductUnitId) {
		this.standardProductUnitId = standardProductUnitId;
	}
	/**
	 * 
	 * @return 
	 */
	public Long getAttNameId() {
		return attNameId;
	}

	/**
	 * 
	 * @param attNameId 
	 */
	public void setAttNameId(Long attNameId) {
		this.attNameId = attNameId;
	}
	/**
	 * 
	 * @return 
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 
	 * @param sortValue 
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}
	/**
	 * 产品属性类型：1、属性 、规格
	 * @return 产品属性类型：1、属性 、规格
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 产品属性类型：1、属性 、规格
	 * @param type 产品属性类型：1、属性 、规格
	 */
	public void setType(Integer type) {
		this.type = type;
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

	public List<StandardProductUnitAttValueDTO> getStandardProductUnitAttValueList() {
		return standardProductUnitAttValueList;
	}

	public void setStandardProductUnitAttValueList(List<StandardProductUnitAttValueDTO> standardProductUnitAttValueList) {
		this.standardProductUnitAttValueList = standardProductUnitAttValueList;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public Integer getShowPicture() {
		return showPicture;
	}

	public void setShowPicture(Integer showPicture) {
		this.showPicture = showPicture;
	}
	
	
	
}
	