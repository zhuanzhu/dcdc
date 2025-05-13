package com.egeo.components.product.po;


import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class CategoryTreePO {


	private Long id;

	/**
	 * 1是后台类目树
	 */
	private Integer type;	

	/**
	 * 
	 */
	private String name;	

	/**
	 * 公司id
	 */
	private Long companyId;

	/**
	 * 代理商id
	 */
	private Long enterpriseId;

	/**
	 * 平台id
	 */
	private Long platformId;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	
	/**
	 * 几级类目：1、一级、2、二级、依次类推
	 */
	private Integer seriesType;
	/**
	 * 是否启用：0否1是
	 */
	private Integer startUsing;
	/**
	 * 备注
	 */
	private String content;
	/**
	 * 类目树模版id
	 */
	private Long categoryTreeTemplateId;
	/**
	 * 公司类型 0:正式公司(默认值) 1:测试公司 2:竞品公司
	 */
	private Integer companyType;
	/**
	 * web是否启用：0否1是
	 */
	private Integer webStart;

	/**
	 * 是否兜底配置 1-是；0-否
	 */
	private Integer isDefault;

	public Integer getWebStart() {
		return webStart;
	}

	public void setWebStart(Integer webStart) {
		this.webStart = webStart;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
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
	 * 1是后台类目树
	 * @return 1是后台类目树
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 1是后台类目树
	 * @param type 1是后台类目树
	 */
	public void setType(Integer type) {
		this.type = type;
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

	public Integer getSeriesType() {
		return seriesType;
	}

	public void setSeriesType(Integer seriesType) {
		this.seriesType = seriesType;
	}

	public Integer getStartUsing() {
		return startUsing;
	}

	public void setStartUsing(Integer startUsing) {
		this.startUsing = startUsing;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCategoryTreeTemplateId() {
		return categoryTreeTemplateId;
	}

	public void setCategoryTreeTemplateId(Long categoryTreeTemplateId) {
		this.categoryTreeTemplateId = categoryTreeTemplateId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
}
	