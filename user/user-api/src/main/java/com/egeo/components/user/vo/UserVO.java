package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author xiaping
 * @date 2017-08-19 11:18:23
 */
public class UserVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer isWorkWechat=0;
	private Long id;
	/**
	 * 登录名
	 */

	private String loginName;

    private String name;

	/**
	 * 密码
	 */

	private String password;
	/**
	 * 手机
	 */

	private String mobile;
	/**
	 * 邮箱地址
	 */

	private String mail;
	/**
	 * 二维码
	 */

	private String qrcode;
	/**
	 * 加密盐
	 */

	private String salt;
	/**
	 * 最新一次更新盐的时间
	 */

	private Date updateSalt;
	/**
	 * 用户所在公司的id
	 */

	private Long companyId;
	/**
	 * 公司类型
	 */
	private Integer companyType;

	private Long platformId;
	private Long enterpriseId;
	private Integer type;
	/**
	 * 是否在职:默认0否;1是
	 */
	private Integer isAvailable;
	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */

	private Date createTime;
	/**
	 * 修改时间:更新时数据库会自动set值
	 */

	private Date updateTime;

    // 创建开始时间
    private String beginTime;

    // 创建结束时间
    private String finishTime;

    /**
	 * 用户注册时间
	 */
	private Date regtime;
	/**
	 * 用户状态 0:未注册 1:已注册
	 */
	private Integer status;
	/**
	 * 设备类型 0、安卓 1、ios 2、web端
	 */
	private Integer deviceType;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 手机唯一标识符
	 */
	private String deviceId;

	/**
	 * 离职日期
	 */
	private Date quitTime;
	/**
	 * 微信OpenId
	 */
	private String weiXinOpenId;

	/**
	 * 是否是管理员身份，0：是 1：否
	 */
	private Integer isAdministrator;
	/**
	 * 活动短码
	 */
	private String campaignCode;
	/**
	 * 注册门店码
	 */
	private String registerStoreId;

	private Long channelId;

	/**
	 * 门店Id
	 */
	private Long storeId;

	/**
	 * 客户端类型
	 */
	private Integer clientType;

	/**
	 * 登陆源
	 */
	private String loginType;

	/**
	 *
	 */
	private Long keyMessage;

	private String channelSource;

	/**
	 * 渠道用户唯一标识
	 */
	private String channelUserUnique;

	public String getRegisterStoreId() {
		return registerStoreId;
	}

	public void setRegisterStoreId(String registerStoreId) {
		this.registerStoreId = registerStoreId;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	public String getWeiXinOpenId() {
		return weiXinOpenId;
	}

	public void setWeiXinOpenId(String weiXinOpenId) {
		this.weiXinOpenId = weiXinOpenId;
	}

	public Long getId() {
		return id;
	}

	public Integer getIsWorkWechat() {
		return isWorkWechat;
	}

	public void setIsWorkWechat(Integer isWorkWechat) {
		this.isWorkWechat = isWorkWechat;
	}

	/**
	 * 编号
	 * @param id 编号
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 登录名
	 * @return 登录名
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 登录名
	 * @param loginName 登录名
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * 密码
	 * @return 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 手机
	 * @return 手机
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 手机
	 * @param mobile 手机
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 邮箱地址
	 * @return 邮箱地址
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * 邮箱地址
	 * @param mail 邮箱地址
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	/**
	 * 二维码
	 * @return 二维码
	 */
	public String getQrcode() {
		return qrcode;
	}

	/**
	 * 二维码
	 * @param qrcode 二维码
	 */
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
	/**
	 * 加密盐
	 * @return 加密盐
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * 加密盐
	 * @param salt 加密盐
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * 最新一次更新盐的时间
	 * @return 最新一次更新盐的时间
	 */
	public Date getUpdateSalt() {
		return updateSalt;
	}

	/**
	 * 最新一次更新盐的时间
	 * @param updateSalt 最新一次更新盐的时间
	 */
	public void setUpdateSalt(Date updateSalt) {
		this.updateSalt = updateSalt;
	}
	/**
	 * 用户所在公司的id
	 * @return 用户所在公司的id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 用户所在公司的id
	 * @param companyId 用户所在公司的id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	/**
	 * 是否在职:默认0否;1是
	 * @return 是否在职:默认0否;1是
	 */
	public Integer getIsAvailable() {
		return isAvailable;
	}

	/**
	 * 是否可用:默认0否;1是
	 * @param isAvailable 是否可用:默认0否;1是
	 */
	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
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

	public String getName() {
		return name;
}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}

	public Integer getIsAdministrator() {
		return isAdministrator;
	}

	public void setIsAdministrator(Integer isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}


	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public Long getKeyMessage() {
		return keyMessage;
	}

	public void setKeyMessage(Long keyMessage) {
		this.keyMessage = keyMessage;
	}

	public String getChannelSource() {
		return channelSource;
	}

	public void setChannelSource(String channelSource) {
		this.channelSource = channelSource;
	}

	public String getChannelUserUnique() {
		return channelUserUnique;
	}

	public void setChannelUserUnique(String channelUserUnique) {
		this.channelUserUnique = channelUserUnique;
	}
}
