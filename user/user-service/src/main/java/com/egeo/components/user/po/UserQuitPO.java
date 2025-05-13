package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author ghw
 * @date 2018-01-17 10:10:36
 */
public class UserQuitPO {


	private Long id;
	private Long userId;
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 员工编号
	 */
	private String memberCode;	

	/**
	 * 真实姓名
	 */
	private String name;	

	/**
	 * 员工邮箱
	 */
	private String mail;	

	/**
	 * 离职日期
	 */
	private Date quitTime;	

	/**
	 * 失效日期
	 */
	private Date invalidTime;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

	/**
	 * 平台ID
	 */
	private Long platformId;	

	public Long getId() {
		return id;
	}

	/**
	 * id
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 公司id
	 * @return 公司id
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * 公司id
	 * @param companyId 公司id
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
	 * 真实姓名
	 * @return 真实姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 真实姓名
	 * @param name 真实姓名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 员工邮箱
	 * @return 员工邮箱
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * 员工邮箱
	 * @param mail 员工邮箱
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * 离职日期
	 * @return 离职日期
	 */
	public Date getQuitTime() {
		return quitTime;
	}

	/**
	 * 离职日期
	 * @param quitTime 离职日期
	 */
	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}

	/**
	 * 失效日期
	 * @return 失效日期
	 */
	public Date getInvalidTime() {
		return invalidTime;
	}

	/**
	 * 失效日期
	 * @param invalidTime 失效日期
	 */
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
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
	 * 平台ID
	 * @return 平台ID
	 */
	public Long getPlatformId() {
		return platformId;
	}

	/**
	 * 平台ID
	 * @param platformId 平台ID
	 */
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
}
	