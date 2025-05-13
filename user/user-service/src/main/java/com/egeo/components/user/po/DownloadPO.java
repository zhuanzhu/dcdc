package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-05-15 15:14:50
 */
public class DownloadPO {


	private Long id;

	/**
	 * 推广渠道名称
	 */
	private String name;	

	/**
	 * session
	 */
	private String session;	

	/**
	 * 二维码链接
	 */
	private String url;	

	/**
	 * 日下载量
	 */
	private Long dailyDownloads;	

	/**
	 * 总下载量
	 */
	private Long downloads;	

	/**
	 * 
	 */
	private Date createTime;	

	/**
	 * 
	 */
	private Date uodateTime;	

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
	 * 推广渠道名称
	 * @return 推广渠道名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 推广渠道名称
	 * @param name 推广渠道名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * session
	 * @return session
	 */
	public String getSession() {
		return session;
	}

	/**
	 * session
	 * @param session session
	 */
	public void setSession(String session) {
		this.session = session;
	}

	/**
	 * 二维码链接
	 * @return 二维码链接
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 二维码链接
	 * @param url 二维码链接
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 日下载量
	 * @return 日下载量
	 */
	public Long getDailyDownloads() {
		return dailyDownloads;
	}

	/**
	 * 日下载量
	 * @param dailyDownloads 日下载量
	 */
	public void setDailyDownloads(Long dailyDownloads) {
		this.dailyDownloads = dailyDownloads;
	}

	/**
	 * 总下载量
	 * @return 总下载量
	 */
	public Long getDownloads() {
		return downloads;
	}

	/**
	 * 总下载量
	 * @param downloads 总下载量
	 */
	public void setDownloads(Long downloads) {
		this.downloads = downloads;
	}

	/**
	 * 
	 * @return 
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime 
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 
	 * @return 
	 */
	public Date getUodateTime() {
		return uodateTime;
	}

	/**
	 * 
	 * @param uodateTime 
	 */
	public void setUodateTime(Date uodateTime) {
		this.uodateTime = uodateTime;
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
	