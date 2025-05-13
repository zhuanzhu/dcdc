package com.egeo.components.stock.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-09-13 16:49:27
 */
public class StockDictPO {


	private Long id;

	/**
	 * 字典类型：1、库存调整原因
	 */
	private Integer type;	

	/**
	 * 自定义的id
	 */
	private Integer customId;	

	/**
	 * 字典名称
	 */
	private String name;	

	/**
	 * 描述
	 */
	private String description;	

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

	public Long getId() {
		return id;
	}

	/**
	 * 主键
	 * @param id 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 字典类型：1、库存调整原因
	 * @return 字典类型：1、库存调整原因
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 字典类型：1、库存调整原因
	 * @param type 字典类型：1、库存调整原因
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 自定义的id
	 * @return 自定义的id
	 */
	public Integer getCustomId() {
		return customId;
	}

	/**
	 * 自定义的id
	 * @param customId 自定义的id
	 */
	public void setCustomId(Integer customId) {
		this.customId = customId;
	}

	/**
	 * 字典名称
	 * @return 字典名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 字典名称
	 * @param name 字典名称
	 */
	public void setName(String name) {
		this.name = name;
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
}
	