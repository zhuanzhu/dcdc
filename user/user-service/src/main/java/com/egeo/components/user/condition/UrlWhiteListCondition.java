package com.egeo.components.user.condition;

import com.egeo.components.user.po.UrlWhiteListPO;

/**
 * 
 * @author xia
 * @date 2018-11-08 14:15:43
 */
public class UrlWhiteListCondition extends UrlWhiteListPO {
	
	private static final long serialVersionUID = 1L;

	private String url;
	
	private String name;
	
	private String platformName;
	
	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
	