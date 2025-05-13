package com.egeo.components.user.po;


import java.util.Date;

/**
 * 
 * @author ghw
 * @date 2018-01-16 10:21:04
 */
public class UserTempPO {


	private Long id;
	/**
	 * 导入类型
	 */
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 部门id（部门编号）
	 */
	private Long departmentId;	

	/**
	 * 员工姓名
	 */
	private String name;	

	/**
	 * 员工编号
	 */
	private String memberCode;	

	/**
	 * 邮箱
	 */
	private String mail;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 平台id
	 */
	private Long platformId;	
	/**
	 * 出生年月日
	 */
	private Date birthday;	
	/**
	 * 性别0女1男
	 */
	private Integer sex;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 创建人ID
	 */
	private Long createUserid;	

	/**
	 * 创建人姓名
	 */
	private String createUsername;	

	/**
	 * 创建人IP
	 */
	private String createUserip;	

	/**
	 * 创建人MAC地址
	 */
	private String createUsermac;
	/**
	 * 导入批次id
	 */
	private Long importRecordsId;
	private Long enterpriseId;

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Long getImportRecordsId() {
		return importRecordsId;
	}

	public void setImportRecordsId(Long importRecordsId) {
		this.importRecordsId = importRecordsId;
	}

	public Long getCreateUserid() {
		return createUserid;
	}

	public void setCreateUserid(Long createUserid) {
		this.createUserid = createUserid;
	}

	public String getCreateUsername() {
		return createUsername;
	}

	public void setCreateUsername(String createUsername) {
		this.createUsername = createUsername;
	}

	public String getCreateUserip() {
		return createUserip;
	}

	public void setCreateUserip(String createUserip) {
		this.createUserip = createUserip;
	}

	public String getCreateUsermac() {
		return createUsermac;
	}

	public void setCreateUsermac(String createUsermac) {
		this.createUsermac = createUsermac;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

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
	 * 部门id（部门编号）
	 * @return 部门id（部门编号）
	 */
	public Long getDepartmentId() {
		return departmentId;
	}

	/**
	 * 部门id（部门编号）
	 * @param departmentId 部门id（部门编号）
	 */
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * 员工姓名
	 * @return 员工姓名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 员工姓名
	 * @param name 员工姓名
	 */
	public void setName(String name) {
		this.name = name;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
	