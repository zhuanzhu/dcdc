package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-12-05 12:05:16
 */    
public class CompanyPO {

	private String corpid;
	private String secret;
	private Integer openWorkWechat;
	private Long id;

	/**
	 * 公司名称
	 */
	private String companyName;	
	/**
	 * 创建人的id
	 */
	private Long userId;
	
	/**
	 * 创建人的姓名
	 */
	private String userName;
	

	/**
	 * 代理商Id
	 */
	private Long enterpriseId;
	/**
	 *  有效状态
	 */
	private  Integer status;

	/**
	 * 公司庆祝时间
	 */
	private Date companyCelebrateTime;	

	/**
	 * 企业邮箱后缀
	 */
	private String companyMailSuffix;	

	/**
	 * 注册状态：1、非开放式注册
	 */
	private Integer registrationStatus;	

	/**
	 * 公司描述
	 */
	private String companyContent;	

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
	 * 企业logo图片url
	 */
	private String companyLogo;
	/**
	 * 公司背景图片
	 */
	private String backgrondImg;
	
	/**
	 * 是否是测试公司
	 */
	private Integer isTest;
	/**
	 * 公司类型：0:正式公司(默认值) 1:测试公司 2:竞品公司
	 */
	private Integer companyType;
	
	private Long companyLinkableId;
	
	private Long platformLinkableId;

	/**
	 * 管理员公司标志
	 */
	private Integer isManagementCompany;

	public Integer getIsManagementCompany() {
		return isManagementCompany;
	}

	public void setIsManagementCompany(Integer isManagementCompany) {
		this.isManagementCompany = isManagementCompany;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
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

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
	 * 公司名称
	 * @return 公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 公司名称
	 * @param companyName 公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 公司庆祝时间
	 * @return 公司庆祝时间
	 */
	public Date getCompanyCelebrateTime() {
		return companyCelebrateTime;
	}

	/**
	 * 公司庆祝时间
	 * @param companyCelebrateTime 公司庆祝时间
	 */
	public void setCompanyCelebrateTime(Date companyCelebrateTime) {
		this.companyCelebrateTime = companyCelebrateTime;
	}

	/**
	 * 企业邮箱后缀
	 * @return 企业邮箱后缀
	 */
	public String getCompanyMailSuffix() {
		return companyMailSuffix;
	}

	/**
	 * 企业邮箱后缀
	 * @param companyMailSuffix 企业邮箱后缀
	 */
	public void setCompanyMailSuffix(String companyMailSuffix) {
		this.companyMailSuffix = companyMailSuffix;
	}

	/**
	 * 注册状态：1、非开放式注册
	 * @return 注册状态：1、非开放式注册
	 */
	public Integer getRegistrationStatus() {
		return registrationStatus;
	}

	/**
	 * 注册状态：1、非开放式注册
	 * @param registrationStatus 注册状态：1、非开放式注册
	 */
	public void setRegistrationStatus(Integer registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	/**
	 * 公司描述
	 * @return 公司描述
	 */
	public String getCompanyContent() {
		return companyContent;
	}

	/**
	 * 公司描述
	 * @param companyContent 公司描述
	 */
	public void setCompanyContent(String companyContent) {
		this.companyContent = companyContent;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getBackgrondImg() {
		return backgrondImg;
	}

	public void setBackgrondImg(String backgrondImg) {
		this.backgrondImg = backgrondImg;
	}

	public Integer getIsTest() {
		return isTest;
	}

	public void setIsTest(Integer isTest) {
		this.isTest = isTest;
	}

	public Long getCompanyLinkableId() {
		return companyLinkableId;
	}

	public void setCompanyLinkableId(Long companyLinkableId) {
		this.companyLinkableId = companyLinkableId;
	}

	public Long getPlatformLinkableId() {
		return platformLinkableId;
	}

	public void setPlatformLinkableId(Long platformLinkableId) {
		this.platformLinkableId = platformLinkableId;
	}

	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public Integer getOpenWorkWechat() {
		return openWorkWechat;
	}

	public void setOpenWorkWechat(Integer openWorkWechat) {
		this.openWorkWechat = openWorkWechat;
	}
}
	