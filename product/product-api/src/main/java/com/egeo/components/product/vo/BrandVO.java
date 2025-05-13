package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author xiaping
 * @date 2017-08-12 13:41:07
 */
public class BrandVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 
	 */

	private String name;		 
	/**
	 * 品牌中文名称
	 */

	private String chineseName;		 
	/**
	 * 品牌英文名称
	 */

	private String englishName;		 
	/**
	 * 品牌别名
	 */

	private String alias;		 
	/**
	 * 品牌url地址
	 */

	private String logUrl;		 
	/**
	 * 品牌所属平台中文名称
	 */

	private String ownedcompanyChineseName;		 
	/**
	 * 品牌所属平台英文名称
	 */

	private String ownedcompanyEnglishName;		 
	/**
	 * 品牌介绍
	 */

	private String introduction;		 
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
	 * 品牌中文名称
	 * @return 品牌中文名称
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * 品牌中文名称
	 * @param chineseName 品牌中文名称
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}	
	/**
	 * 品牌英文名称
	 * @return 品牌英文名称
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * 品牌英文名称
	 * @param englishName 品牌英文名称
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}	
	/**
	 * 品牌别名
	 * @return 品牌别名
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * 品牌别名
	 * @param alias 品牌别名
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}	
	/**
	 * 品牌url地址
	 * @return 品牌url地址
	 */
	public String getLogUrl() {
		return logUrl;
	}

	/**
	 * 品牌url地址
	 * @param logUrl 品牌url地址
	 */
	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}	
	/**
	 * 品牌所属平台中文名称
	 * @return 品牌所属平台中文名称
	 */
	public String getOwnedcompanyChineseName() {
		return ownedcompanyChineseName;
	}

	/**
	 * 品牌所属平台中文名称
	 * @param ownedcompanyChineseName 品牌所属平台中文名称
	 */
	public void setOwnedcompanyChineseName(String ownedcompanyChineseName) {
		this.ownedcompanyChineseName = ownedcompanyChineseName;
	}	
	/**
	 * 品牌所属平台英文名称
	 * @return 品牌所属平台英文名称
	 */
	public String getOwnedcompanyEnglishName() {
		return ownedcompanyEnglishName;
	}

	/**
	 * 品牌所属平台英文名称
	 * @param ownedcompanyEnglishName 品牌所属平台英文名称
	 */
	public void setOwnedcompanyEnglishName(String ownedcompanyEnglishName) {
		this.ownedcompanyEnglishName = ownedcompanyEnglishName;
	}	
	/**
	 * 品牌介绍
	 * @return 品牌介绍
	 */
	public String getIntroduction() {
		return introduction;
	}

	/**
	 * 品牌介绍
	 * @param introduction 品牌介绍
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
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
}
	