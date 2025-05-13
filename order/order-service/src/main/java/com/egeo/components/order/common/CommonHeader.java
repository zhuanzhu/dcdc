package com.egeo.components.order.common;

public class CommonHeader {
	private String appCode;
	private String appPath;
	private String insideVersion;
	private String channelId;
	/**
	 * 设备型号
	 */
	private String deviceModel;
	/**
	 * 设备版本号
	 */
	private String deviceVersion;
	/**
	 * 版本号
	 */
	private String version;
	private String deviceId;
	private String deviceType;

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	public String getInsideVersion() {
		return insideVersion;
	}

	public void setInsideVersion(String insideVersion) {
		this.insideVersion = insideVersion;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
