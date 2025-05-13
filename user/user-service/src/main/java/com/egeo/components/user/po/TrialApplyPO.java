package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author wyy
 * @date 2018-05-31 16:09:49
 */
public class TrialApplyPO {


	private Long id;

	/**
	 * 企业名称
	 */
	private String companyName;	

	/**
	 * 企业规模 0:1-50 1:51-100 2:101-500 3:501-2000 4:2001-5000 5:5000以上
	 */
	private Integer companyScale;	

	/**
	 * 姓名
	 */
	private String userName;	

	/**
	 * 电话
	 */
	private String phone;	

	/**
	 * 职位
	 */
	private String position;	

	/**
	 * 邮箱
	 */
	private String mail;	

	/**
	 * 留言
	 */
	private String message;	

	/**
	 * 数据来源   0:web 1:h5  2:微信
	 */
	private Integer clientType;	

	/**
	 * 申请状态   0::未处理   1:已处理
	 */
	private Integer status;	

	/**
	 * 处理人
	 */
	private Long dealUser;	

	/**
	 * 处理时间
	 */
	private Date dealTime;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	
	
	private String dealUserName;	// 处理人姓名
	/**
	 * 平台id
	 */
	private Long platformId;

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
	 * 企业名称
	 * @return 企业名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 企业名称
	 * @param companyName 企业名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 企业规模 0:1-50 1:51-100 2:101-500 3:501-2000 4:2001-5000 5:5000以上
	 * @return 企业规模 0:1-50 1:51-100 2:101-500 3:501-2000 4:2001-5000 5:5000以上
	 */
	public Integer getCompanyScale() {
		return companyScale;
	}

	/**
	 * 企业规模 0:1-50 1:51-100 2:101-500 3:501-2000 4:2001-5000 5:5000以上
	 * @param companyScale 企业规模 0:1-50 1:51-100 2:101-500 3:501-2000 4:2001-5000 5:5000以上
	 */
	public void setCompanyScale(Integer companyScale) {
		this.companyScale = companyScale;
	}

	/**
	 * 姓名
	 * @return 姓名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 姓名
	 * @param userName 姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 电话
	 * @return 电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 电话
	 * @param phone 电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 职位
	 * @return 职位
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * 职位
	 * @param position 职位
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * 邮箱
	 * @return 邮箱
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * 邮箱
	 * @param mail 邮箱
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 留言
	 * @return 留言
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 留言
	 * @param message 留言
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 数据来源   0:web 1:h5  2:微信
	 * @return 数据来源   0:web 1:h5  2:微信
	 */
	public Integer getClientType() {
		return clientType;
	}

	/**
	 * 数据来源   0:web 1:h5  2:微信
	 * @param clientType 数据来源   0:web 1:h5  2:微信
	 */
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	/**
	 * 申请状态   0::未处理   1:已处理
	 * @return 申请状态   0::未处理   1:已处理
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 申请状态   0::未处理   1:已处理
	 * @param status 申请状态   0::未处理   1:已处理
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 处理人
	 * @return 处理人
	 */
	public Long getDealUser() {
		return dealUser;
	}

	/**
	 * 处理人
	 * @param dealUser 处理人
	 */
	public void setDealUser(Long dealUser) {
		this.dealUser = dealUser;
	}

	/**
	 * 处理时间
	 * @return 处理时间
	 */
	public Date getDealTime() {
		return dealTime;
	}

	/**
	 * 处理时间
	 * @param dealTime 处理时间
	 */
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
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

	public String getDealUserName() {
		return dealUserName;
	}

	public void setDealUserName(String dealUserName) {
		this.dealUserName = dealUserName;
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
	