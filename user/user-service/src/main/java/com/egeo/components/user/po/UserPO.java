package com.egeo.components.user.po;


import java.util.Date;

/**
 *
 * @author xiaping
 * @date 2017-08-19 11:18:51
 */
public class UserPO {


	private String wechatUserId;

	public String getWechatUserId() {
		return wechatUserId;
	}

	public void setWechatUserId(String wechatUserId) {
		this.wechatUserId = wechatUserId;
	}

	private Long id;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 *
	 */
	private String name;

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
	 * 代理商的id
	 */
	private Long enterpriseId;
	/**
	 * 代理商的id
	 */
	private Integer type;

    // 创建开始时间
    private String beginTime;

    // 创建结束时间
    private String finishTime;
	/**
	 * 是否可用:默认0否;1是
	 */
	private Integer isAvailable;

	/**
	 * 逻辑删除字段 0 正常 1 已删除
	 */
	private Integer isDeleted = 0;

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	/**
	 * 支付密码
	 */
	private String paymentCode;

	/**
	 * 支付密码加密盐Id
	 */
	private String paymentCodeUuid;
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
	 * 渠道id
	 */
	private Long channelId;

	/**
	 * 离职日期
	 */
	private Date quitTime;
	private Long platformId;
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

	private String channelSource;

	private Long importRecordsId;

	private String channelUserUnique;

	public Long getImportRecordsId() {
		return importRecordsId;
	}
	public void setImportRecordsId(Long importRecordsId) {
		this.importRecordsId = importRecordsId;
	}

	public String getRegisterStoreId() {
		return registerStoreId;
	}

	public void setRegisterStoreId(String registerStoreId) {
		this.registerStoreId = registerStoreId;
	}

	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public Long getId() {
		return id;
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
	 * 逻辑删除字段 0 正常 1 已删除
	 * @return 逻辑删除字段 0 正常 1 已删除
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}

	/**
	 * 逻辑删除字段 0 正常 1 已删除
	 * @param isDeleted 逻辑删除字段 0 正常 1 已删除
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 * @return 创建时间:创建记录时数据库会自动set值
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getPaymentCodeUuid() {
		return paymentCodeUuid;
	}

	public void setPaymentCodeUuid(String paymentCodeUuid) {
		this.paymentCodeUuid = paymentCodeUuid;
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

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
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
