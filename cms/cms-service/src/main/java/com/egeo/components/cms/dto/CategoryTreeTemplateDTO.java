package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-04-23 18:00:45
 */
public class CategoryTreeTemplateDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 模版名称
	 */
	private String templateName;	

	/**
	 * 模版类型：1、一级类目 2、二级类目
	 */
	private Integer templateType;	

	/**
	 * 安卓版本编号
	 */
	private Integer versionCodeA;	

	/**
	 * ios版本编号
	 */
	private Integer versionCodeI;	

	/**
	 * 是否显示：0、否 1、是
	 */
	private Integer showTemplate;	

	/**
	 * 类目树预览图片url
	 */
	private String imgUrl;	

	/**
	 * 备注
	 */
	private String content;	

	/**
	 * 创建用户id
	 */
	private Long createUserid;	

	/**
	 * 创建人名称
	 */
	private String createUsername;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改人id
	 */
	private Long updateUserid;	

	/**
	 * 修改人名称
	 */
	private String updateUsername;	

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
	 * 模版名称
	 * @return 模版名称
	 */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * 模版名称
	 * @param templateName 模版名称
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	/**
	 * 模版类型：1、一级类目 2、二级类目
	 * @return 模版类型：1、一级类目 2、二级类目
	 */
	public Integer getTemplateType() {
		return templateType;
	}

	/**
	 * 模版类型：1、一级类目 2、二级类目
	 * @param templateType 模版类型：1、一级类目 2、二级类目
	 */
	public void setTemplateType(Integer templateType) {
		this.templateType = templateType;
	}
	/**
	 * 安卓版本编号
	 * @return 安卓版本编号
	 */
	public Integer getVersionCodeA() {
		return versionCodeA;
	}

	/**
	 * 安卓版本编号
	 * @param versionCodeA 安卓版本编号
	 */
	public void setVersionCodeA(Integer versionCodeA) {
		this.versionCodeA = versionCodeA;
	}
	/**
	 * ios版本编号
	 * @return ios版本编号
	 */
	public Integer getVersionCodeI() {
		return versionCodeI;
	}

	/**
	 * ios版本编号
	 * @param versionCodeI ios版本编号
	 */
	public void setVersionCodeI(Integer versionCodeI) {
		this.versionCodeI = versionCodeI;
	}
	/**
	 * 是否显示：0、否 1、是
	 * @return 是否显示：0、否 1、是
	 */
	public Integer getShowTemplate() {
		return showTemplate;
	}

	/**
	 * 是否显示：0、否 1、是
	 * @param showTemplate 是否显示：0、否 1、是
	 */
	public void setShowTemplate(Integer showTemplate) {
		this.showTemplate = showTemplate;
	}
	/**
	 * 类目树预览图片url
	 * @return 类目树预览图片url
	 */
	public String getImgUrl() {
		return imgUrl;
	}

	/**
	 * 类目树预览图片url
	 * @param imgUrl 类目树预览图片url
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 备注
	 * @param content 备注
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 创建用户id
	 * @return 创建用户id
	 */
	public Long getCreateUserid() {
		return createUserid;
	}

	/**
	 * 创建用户id
	 * @param createUserid 创建用户id
	 */
	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}
	/**
	 * 创建人名称
	 * @return 创建人名称
	 */
	public String getCreateUsername() {
		return createUsername;
	}

	/**
	 * 创建人名称
	 * @param createUsername 创建人名称
	 */
	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
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
	 * 修改人id
	 * @return 修改人id
	 */
	public Long getUpdateUserid() {
		return updateUserid;
	}

	/**
	 * 修改人id
	 * @param updateUserid 修改人id
	 */
	public void setUpdateUserid(Long updateUserid) {
		this.updateUserid = updateUserid;
	}
	/**
	 * 修改人名称
	 * @return 修改人名称
	 */
	public String getUpdateUsername() {
		return updateUsername;
	}

	/**
	 * 修改人名称
	 * @param updateUsername 修改人名称
	 */
	public void setUpdateUsername(String updateUsername) {
		this.updateUsername = updateUsername;
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
	