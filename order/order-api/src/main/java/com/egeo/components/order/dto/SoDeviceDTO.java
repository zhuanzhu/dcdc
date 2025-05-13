package com.egeo.components.order.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jiang
 * @date 2018-01-29 09:55:24
 */
public class SoDeviceDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 订单id
	 */
	private Long orderId;	

	/**
	 * 设备编号 IDFA/IMEI
	 */
	private String deviceId;	

	/**
	 * 用户ip
	 */
	private String ip;	

	/**
	 * 版本号
	 */
	private String versionCode;	

	/**
	 * 操作系统及版本
	 */
	private String os;	

	/**
	 * 手机型号
	 */
	private String phoneModel;	

	/**
	 * 接口id
	 */
	private Long interId;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
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
	 * 订单id
	 * @return 订单id
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * 订单id
	 * @param orderId 订单id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	/**
	 * 设备编号 IDFA/IMEI
	 * @return 设备编号 IDFA/IMEI
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * 设备编号 IDFA/IMEI
	 * @param deviceId 设备编号 IDFA/IMEI
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 用户ip
	 * @return 用户ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 用户ip
	 * @param ip 用户ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 版本号
	 * @return 版本号
	 */
	public String getVersionCode() {
		return versionCode;
	}

	/**
	 * 版本号
	 * @param versionCode 版本号
	 */
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	/**
	 * 操作系统及版本
	 * @return 操作系统及版本
	 */
	public String getOs() {
		return os;
	}

	/**
	 * 操作系统及版本
	 * @param os 操作系统及版本
	 */
	public void setOs(String os) {
		this.os = os;
	}
	/**
	 * 手机型号
	 * @return 手机型号
	 */
	public String getPhoneModel() {
		return phoneModel;
	}

	/**
	 * 手机型号
	 * @param phoneModel 手机型号
	 */
	public void setPhoneModel(String phoneModel) {
		this.phoneModel = phoneModel;
	}
	/**
	 * 接口id
	 * @return 接口id
	 */
	public Long getInterId() {
		return interId;
	}

	/**
	 * 接口id
	 * @param interId 接口id
	 */
	public void setInterId(Long interId) {
		this.interId = interId;
	}
	/**
	 * 创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 更新时间
	 * @return 更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * @param updateTime 更新时间
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
	