package com.egeo.components.user.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2017-12-05 12:05:16
 */
public class CompanyVO implements Serializable {
	private static final long serialVersionUID = 1L;

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
	 *  有效状态
	 */
	private  Integer status;
	
	/**
	 * 备注
	 */
	private String companyContent;
	/**
	 * 公司庆祝时间
	 */
	private Date companyCelebrateTime;
	/**
	 * 企业邮箱后缀
	 */
	private String companyMailSuffix;
	/**
	 * 创建时间:创建记录时数据库会自动set值
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
	private Long enterpriseId;
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
	
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCompanyContent() {
		return companyContent;
	}

	public void setCompanyContent(String companyContent) {
		this.companyContent = companyContent;
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

}
	