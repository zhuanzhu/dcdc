package com.egeo.components.user.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author ghw
 * @date 2018-01-10 01:11:50
 */
public class DepartmentImportRecordDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 公司名称
	 */
	private String companyName;	

	/**
	 * 排序
	 */
	private Integer sortValue;	

	/**
	 * 创建时间:(导入的文件创建时间)
	 */
	private Date createTime;	

	/**
	 * 文件序号
	 */
	private String fileOrder;	

	/**
	 * 平台id
	 */
	private Long platformId;	

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
	 * 排序
	 * @return 排序
	 */
	public Integer getSortValue() {
		return sortValue;
	}

	/**
	 * 排序
	 * @param sortValue 排序
	 */
	public void setSortValue(Integer sortValue) {
		this.sortValue = sortValue;
	}
	/**
	 * 创建时间:(导入的文件创建时间)
	 * @return 创建时间:(导入的文件创建时间)
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间:(导入的文件创建时间)
	 * @param createTime 创建时间:(导入的文件创建时间)
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 文件序号
	 * @return 文件序号
	 */
	public String getFileOrder() {
		return fileOrder;
	}

	/**
	 * 文件序号
	 * @param fileOrder 文件序号
	 */
	public void setFileOrder(String fileOrder) {
		this.fileOrder = fileOrder;
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
	