package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-05-31 14:35:28
 */
public class UrlTypePO {


	private Long id;

	/**
	 * urlId
	 */
	private Long urlId;	

	/**
	 * url类型
	 */
	private Long urlTypeDictId;	

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
	 * urlId
	 * @return urlId
	 */
	public Long getUrlId() {
		return urlId;
	}

	/**
	 * urlId
	 * @param urlId urlId
	 */
	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}

	/**
	 * url类型
	 * @return url类型
	 */
	public Long getUrlTypeDictId() {
		return urlTypeDictId;
	}

	/**
	 * url类型
	 * @param urlTypeDictId url类型
	 */
	public void setUrlTypeDictId(Long urlTypeDictId) {
		this.urlTypeDictId = urlTypeDictId;
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
	