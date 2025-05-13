package com.egeo.components.user.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author xiaping
 * @date 2017-08-18 18:50:29
 */
public class UserExtendDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 性别
	 */
	private Integer sex;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 头像
	 */
	private String headPicUrl;

	/**
	 *
	 */
	private String name;

	private String namePy;

	/**
	 * 用户注册时间
	 */
	private Date regtime;

	/**
	 * 出生年月日
	 */
	private Date birthday;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 *
	 */
	private String qq;

	/**
	 * 员工编号
	 */
	private String memberCode;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 *
	 */
	private Date lastlogin;

	/**
	 *
	 */
	private BigDecimal activityInfo;

	/**
	 * 是否进行了完善用户信息操作
	 */
	private Integer isComplete;

	/**
	 * 登录设备编号
	 */
	private String deviceId;

	/**
	 * 设备类型 0pc 1安卓 2ios
	 */
	private Integer deviceType;

	/**
	 *
	 */
	private String weixin;
	/**
	 *
	 */
	private String wxAppId;

	/**
	 * 默认工作地点
	 */
	private Integer workaddressId;

	/**
	 * 用户状态 0:停用 1:启用 2:注销
	 */
	private Integer status;

	/**
	 * 账户状态 0：有效  1：无效'
	 */
	private Integer accountStatus;
	/**
	 * 地址
	 */
	private String address;

	/**
	 * 逻辑删除字段 0 正常 1 已删除
	 */

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;
	/**
	 * 用户所在公司的id
	 */
	private Long companyId;

	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 部门id
	 */
	private Long departmentId;
	/**
	 * 部门名称
	 */
	private String departmentName;
	/**
	 * 入职时间
	 */
	private Date entryTime;
	/**
	 * 邮箱地址
	 */
	private String mail;
	/**
	 * 平台id
	 */
	private Long platformId;
	/**
	 * 出生年月日搜索开始时间
	 */
	private Date birthdayStartTime;
	/**
	 * 出生年月日搜索结束时间
	 */
	private Date birthdayFinishTime;
	/**
	 * 入职搜索开始时间
	 */
	private Date entryStartTime;
	/**
	 * 入职搜索结束时间
	 */
	private Date entryFinishTime;
	/**
	 * 支付密码
	 */
	private String paymentCode;

	/**
	 * 是否在职:默认0否;1是
	 */
	private Integer isAvailable;


	/**
	 * 逻辑删除字段 0 正常 1 已删除
	 */

	private Integer isDeleted;
	/**
	 * 是否是管理员身份，0：是 1：否
	 */
	private Integer isAdministrator;
	/**
	 * 公司背景图片
	 */
	private String backgrondImg;

	/**
	 * 公司logo图片url
	 */
	private String companyLogo;

	/**
	 * 渠道id
	 */
	private Long channelId;

	/**
	 * 离职日期
	 */
	private Date quitTime;

	/**
	 * 失效日期
	 */
	private Date invalidTime;
	/**
	 * 活动短码
	 */
	private String campaignCode;
	/**
	 * 注册门店码
	 *
	 */
	private Long storeId;
	/**
	 * 注册门店id
	 */
	private Long registerStoreId;
	/**
	 * 导入批次ID
	 */
	private Long importRecordsId;

	/**
	 * dlf
	 **/
	private String channelSource;

	private String idCardNo;

	public Long getImportRecordsId() {
		return importRecordsId;
	}

	public void setImportRecordsId(Long importRecordsId) {
		this.importRecordsId = importRecordsId;
	}

	public Long getRegisterStoreId() {
		return registerStoreId;
	}

	public void setRegisterStoreId(Long registerStoreId) {
		this.registerStoreId = registerStoreId;
	}

	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	public Date getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}

	public Date getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}


	public String getWxAppId() {
		return wxAppId;
	}

	public void setWxAppId(String wxAppId) {
		this.wxAppId = wxAppId;
	}

	public String getNamePy() {
		return namePy;
	}

	public void setNamePy(String namePy) {
		this.namePy = namePy;
	}

	public Long getId() {
		return id;
	}

	/**
	 *
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 性别
	 * @return 性别
	 */
	public Integer getSex() {
		return sex;
	}

	/**
	 * 性别
	 * @param sex 性别
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 昵称
	 * @return 昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 昵称
	 * @param nickname 昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 头像
	 * @return 头像
	 */
	public String getHeadPicUrl() {
		return headPicUrl;
	}

	/**
	 * 头像
	 * @param headPicUrl 头像
	 */
	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}
	/**
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 *
	 * @return
	 */
	public Date getRegtime() {
		return regtime;
	}

	/**
	 *
	 * @param regtime
	 */
	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}
	/**
	 * 出生年月日
	 * @return 出生年月日
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * 出生年月日
	 * @param birthday 出生年月日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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
	 *
	 * @return
	 */
	public String getQq() {
		return qq;
	}

	/**
	 *
	 * @param qq
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
	/**
	 * 员工编号
	 * @return 员工编号
	 */
	public String getMemberCode() {
		return memberCode;
	}

	/**
	 * 员工编号
	 * @param memberCode 员工编号
	 */
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	/**
	 * 备注
	 * @return 备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 *
	 * @return
	 */
	public Date getLastlogin() {
		return lastlogin;
	}

	/**
	 *
	 * @param lastlogin
	 */
	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}
	/**
	 *
	 * @return
	 */
	public BigDecimal getActivityInfo() {
		return activityInfo;
	}

	/**
	 *
	 * @param activityInfo
	 */
	public void setActivityInfo(BigDecimal activityInfo) {
		this.activityInfo = activityInfo;
	}
	/**
	 * 是否进行了完善用户信息操作
	 * @return 是否进行了完善用户信息操作
	 */
	public Integer getIsComplete() {
		return isComplete;
	}

	/**
	 * 是否进行了完善用户信息操作
	 * @param isComplete 是否进行了完善用户信息操作
	 */
	public void setIsComplete(Integer isComplete) {
		this.isComplete = isComplete;
	}
	/**
	 * 登录设备编号
	 * @return 登录设备编号
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * 登录设备编号
	 * @param deviceId 登录设备编号
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	/**
	 * 设备类型 0pc 1安卓 2ios
	 * @return 设备类型 0pc 1安卓 2ios
	 */
	public Integer getDeviceType() {
		return deviceType;
	}

	/**
	 * 设备类型 0pc 1安卓 2ios
	 * @param deviceType 设备类型 0pc 1安卓 2ios
	 */
	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 *
	 * @return
	 */
	public String getWeixin() {
		return weixin;
	}

	/**
	 *
	 * @param weixin
	 */
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	/**
	 * 默认工作地点
	 * @return 默认工作地点
	 */
	public Integer getWorkaddressId() {
		return workaddressId;
	}

	/**
	 * 默认工作地点
	 * @param workaddressId 默认工作地点
	 */
	public void setWorkaddressId(Integer workaddressId) {
		this.workaddressId = workaddressId;
	}
	/**
	 * 用户状态 0:停用 1:启用 2:注销
	 * @return 用户状态 0:停用 1:启用 2:注销
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 用户状态 0:停用 1:启用 2:注销
	 * @param status 用户状态 0:停用 1:启用 2:注销
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 地址
	 * @return 地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 地址
	 * @param address 地址
	 */
	public void setAddress(String address) {
		this.address = address;
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getBirthdayStartTime() {
		return birthdayStartTime;
	}

	public void setBirthdayStartTime(Date birthdayStartTime) {
		this.birthdayStartTime = birthdayStartTime;
	}

	public Date getBirthdayFinishTime() {
		return birthdayFinishTime;
	}

	public void setBirthdayFinishTime(Date birthdayFinishTime) {
		this.birthdayFinishTime = birthdayFinishTime;
	}

	public Date getEntryStartTime() {
		return entryStartTime;
	}

	public void setEntryStartTime(Date entryStartTime) {
		this.entryStartTime = entryStartTime;
	}

	public Date getEntryFinishTime() {
		return entryFinishTime;
	}

	public void setEntryFinishTime(Date entryFinishTime) {
		this.entryFinishTime = entryFinishTime;
	}

	public Long getPlatformId() {
		return platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Integer getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Integer isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public Integer getIsAdministrator() {
		return isAdministrator;
	}

	public void setIsAdministrator(Integer isAdministrator) {
		this.isAdministrator = isAdministrator;
	}

	public Integer getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getBackgrondImg() {
		return backgrondImg;
	}

	public void setBackgrondImg(String backgrondImg) {
		this.backgrondImg = backgrondImg;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
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


	public String getChannelSource() {
		return channelSource;
	}

	public void setChannelSource(String channelSource) {
		this.channelSource = channelSource;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
}
