package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-02-04 16:05:50
 * 后台版本列表VO
 * 查询版本列表传参VO
 */
public class VersionsVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 版本号
	 */
	private String versionsMark;
	/**
	 * 版本类型：0、安卓 1、ios
	 */
	private Integer type;
	/**
	 * 发布日期
	 */
	private Date releaseDate;
	/**
	 * 是否强制升级：0否1是
	 */
	private Integer isConstraint;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;
	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	
	private String url;
	
	private String description;
	
	private Integer versionCode;
	
	/**
	 * 安装包名称
	 */
	private String installName;
	
	/**
	 * 版本简述
	 */
	private String resume;
	
	/**
	 * 是否是官网版本 0:否,1:是
	 */
	private Integer isOfficial;
	
	/**
	 * 版本状态 0:停用 1:启用
	 */
	private Integer versionStatus;

	/**
	 * 平台id
	 */
	private Long platformId;
	/**
	 * 使用方：1、c端 2、b端
	 */
	private Integer user;
	
	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public Integer getVersionStatus() {
		return versionStatus;
	}

	public void setVersionStatus(Integer versionStatus) {
		this.versionStatus = versionStatus;
	}
	
	public String getInstallName() {
		return installName;
	}

	public void setInstallName(String installName) {
		this.installName = installName;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Integer getIsOfficial() {
		return isOfficial;
	}

	public void setIsOfficial(Integer isOfficial) {
		this.isOfficial = isOfficial;
	}

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
	 * 版本号
	 * @return 版本号
	 */
	public String getVersionsMark() {
		return versionsMark;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	/**
	 * 版本号
	 * @param versionsMark 版本号
	 */
	public void setVersionsMark(String versionsMark) {
		this.versionsMark = versionsMark;
	}	
	/**
	 * 版本类型：1、安卓 2、ios
	 * @return 版本类型：1、安卓 2、ios
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 版本类型：1、安卓 2、ios
	 * @param type 版本类型：1、安卓 2、ios
	 */
	public void setType(Integer type) {
		this.type = type;
	}	
	/**
	 * 发布日期
	 * @return 发布日期
	 */
	public Date getReleaseDate() {
		return releaseDate;
	}

	/**
	 * 发布日期
	 * @param releaseDate 发布日期
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}	
	/**
	 * 是否强制升级：0否1是
	 * @return 是否强制升级：0否1是
	 */
	public Integer getIsConstraint() {
		return isConstraint;
	}

	/**
	 * 是否强制升级：0否1是
	 * @param isConstraint 是否强制升级：0否1是
	 */
	public void setIsConstraint(Integer isConstraint) {
		this.isConstraint = isConstraint;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	