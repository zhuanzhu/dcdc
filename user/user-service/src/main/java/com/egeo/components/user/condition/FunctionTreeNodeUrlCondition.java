package com.egeo.components.user.condition;

import com.egeo.components.user.po.FunctionTreeNodeUrlPO;

/**
 * 
 * @author tan
 * @date 2018-11-13 11:50:21
 */
public class FunctionTreeNodeUrlCondition extends FunctionTreeNodeUrlPO {
	
	private static final long serialVersionUID = 1L;
	
	private String functionName;
	
	private String urlName;
	
	private String url;

	/**
	 * 平台id
	 */
	private Long platformId;

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getPlatformId() { return platformId; }

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	
}
	