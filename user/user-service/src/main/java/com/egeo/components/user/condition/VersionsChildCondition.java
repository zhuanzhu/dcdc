package com.egeo.components.user.condition;

import java.util.Date;

import com.egeo.components.user.po.VersionsChildPO;

/**
 * 
 * @author min
 * @date 2018-02-04 18:35:18
 */
public class VersionsChildCondition extends VersionsChildPO {
	private static final long serialVersionUID = 1L;
	/**
	 * 渠道名称
	 */
	private String channelName;
	/**
	 * 渠道链接
	 */
	private String path;	
	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}	
	

}
	