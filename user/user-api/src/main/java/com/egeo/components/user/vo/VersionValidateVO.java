package com.egeo.components.user.vo;

/**
 * 客户端版本校验VO
 * @author graci
 *
 */
public class VersionValidateVO {

	private String versionName;
	private boolean needUpdate;
	private String url;
	private String description;
	private Integer vCode;

	/**
	 * 版本简述
	 */
	private String resume;
	
	public Integer getvCode() {
		return vCode;
	}
	public void setvCode(Integer vCode) {
		this.vCode = vCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	public boolean isNeedUpdate() {
		return needUpdate;
	}
	public void setNeedUpdate(boolean needUpdate) {
		this.needUpdate = needUpdate;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	
	
}
