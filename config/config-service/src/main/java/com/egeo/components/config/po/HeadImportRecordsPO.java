package com.egeo.components.config.po;


import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-12 05:09:12
 */
public class HeadImportRecordsPO {


	private Long id;

	/**
	 * 公司名称
	 */
	private String companyName;	

	/**
	 * 模板类型
	 */
	private String templateType;	

	/**
	 * 生成时间
	 */
	private Date generatedTime;	

	/**
	 * 文件序号
	 */
	private String fileSequenceNumber;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

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
	 * 模板类型
	 * @return 模板类型
	 */
	public String getTemplateType() {
		return templateType;
	}

	/**
	 * 模板类型
	 * @param templateType 模板类型
	 */
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	/**
	 * 生成时间
	 * @return 生成时间
	 */
	public Date getGeneratedTime() {
		return generatedTime;
	}

	/**
	 * 生成时间
	 * @param generatedTime 生成时间
	 */
	public void setGeneratedTime(Date generatedTime) {
		this.generatedTime = generatedTime;
	}

	/**
	 * 文件序号
	 * @return 文件序号
	 */
	public String getFileSequenceNumber() {
		return fileSequenceNumber;
	}

	/**
	 * 文件序号
	 * @param fileSequenceNumber 文件序号
	 */
	public void setFileSequenceNumber(String fileSequenceNumber) {
		this.fileSequenceNumber = fileSequenceNumber;
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
}
	