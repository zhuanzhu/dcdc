package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-08-15 10:44:50
 */
public class InfoPO {


	private Long id;

	/**
	 * 模版名称
	 */
	private String name;	

	/**
	 * 触发条件
	 */
	private String triggers;	

	/**
	 * 用户消息类型：1、系统通知 2、消息通知
	 */
	private Long type;	

	/**
	 * 是否启用
	 */
	private Integer isStart;	

	/**
	 * 系统消息
	 */
	private String systemInfo;	

	/**
	 * 手机短信
	 */
	private String moblieInfo;	

	/**
	 * 微信公众号推送信息
	 */
	private String weChatOfficialInfo;	

	/**
	 * 邮件信息标题
	 */
	private String mailInfoTitle;	

	/**
	 * 邮件信息
	 */
	private String mailInfo;	

	/**
	 * 邮件备注
	 */
	private String mailRemark;	

	/**
	 * 消息备注
	 */
	private String infoTemplateRemark;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	/**
	 * 消息通知
	 */
	private String infoInform;
	/**
	 * 是否是管理员 0 否1是
	 */
	private Integer isAdmin;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 消息标题
	 */
	private String title;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getInfoInform() {
		return infoInform;
	}

	public void setInfoInform(String infoInform) {
		this.infoInform = infoInform;
	}

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
	 * 模版名称
	 * @return 模版名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 模版名称
	 * @param name 模版名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 触发条件
	 * @return 触发条件
	 */
	public String getTriggers() {
		return triggers;
	}

	/**
	 * 触发条件
	 * @param triggers 触发条件
	 */
	public void setTriggers(String triggers) {
		this.triggers = triggers;
	}

	/**
	 * 用户消息类型：1、系统通知 2、消息通知
	 * @return 用户消息类型：1、系统通知 2、消息通知
	 */
	public Long getType() {
		return type;
	}

	/**
	 * 用户消息类型：1、系统通知 2、消息通知
	 * @param type 用户消息类型：1、系统通知 2、消息通知
	 */
	public void setType(Long type) {
		this.type = type;
	}

	/**
	 * 是否启用
	 * @return 是否启用
	 */
	public Integer getIsStart() {
		return isStart;
	}

	/**
	 * 是否启用
	 * @param isStart 是否启用
	 */
	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}

	/**
	 * 系统消息
	 * @return 系统消息
	 */
	public String getSystemInfo() {
		return systemInfo;
	}

	/**
	 * 系统消息
	 * @param systemInfo 系统消息
	 */
	public void setSystemInfo(String systemInfo) {
		this.systemInfo = systemInfo;
	}

	/**
	 * 手机短信
	 * @return 手机短信
	 */
	public String getMoblieInfo() {
		return moblieInfo;
	}

	/**
	 * 手机短信
	 * @param moblieInfo 手机短信
	 */
	public void setMoblieInfo(String moblieInfo) {
		this.moblieInfo = moblieInfo;
	}

	/**
	 * 微信公众号推送信息
	 * @return 微信公众号推送信息
	 */
	public String getWeChatOfficialInfo() {
		return weChatOfficialInfo;
	}

	/**
	 * 微信公众号推送信息
	 * @param weChatOfficialInfo 微信公众号推送信息
	 */
	public void setWeChatOfficialInfo(String weChatOfficialInfo) {
		this.weChatOfficialInfo = weChatOfficialInfo;
	}

	/**
	 * 邮件信息标题
	 * @return 邮件信息标题
	 */
	public String getMailInfoTitle() {
		return mailInfoTitle;
	}

	/**
	 * 邮件信息标题
	 * @param mailInfoTitle 邮件信息标题
	 */
	public void setMailInfoTitle(String mailInfoTitle) {
		this.mailInfoTitle = mailInfoTitle;
	}

	/**
	 * 邮件信息
	 * @return 邮件信息
	 */
	public String getMailInfo() {
		return mailInfo;
	}

	/**
	 * 邮件信息
	 * @param mailInfo 邮件信息
	 */
	public void setMailInfo(String mailInfo) {
		this.mailInfo = mailInfo;
	}

	/**
	 * 邮件备注
	 * @return 邮件备注
	 */
	public String getMailRemark() {
		return mailRemark;
	}

	/**
	 * 邮件备注
	 * @param mailRemark 邮件备注
	 */
	public void setMailRemark(String mailRemark) {
		this.mailRemark = mailRemark;
	}

	/**
	 * 消息备注
	 * @return 消息备注
	 */
	public String getInfoTemplateRemark() {
		return infoTemplateRemark;
	}

	/**
	 * 消息备注
	 * @param infoTemplateRemark 消息备注
	 */
	public void setInfoTemplateRemark(String infoTemplateRemark) {
		this.infoTemplateRemark = infoTemplateRemark;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
	