package com.egeo.components.product.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author tan
 * @date 2019-03-29 10:17:51
 */
public class JdPriceConfigDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 正式公司价格比例
	 */
	private Integer standardCompanyRate;	

	/**
	 * 正式公司价格比例
	 */
	private Integer democompanysCompanyRate;	

	/**
	 * 正式公司价格比例
	 */
	private Integer competingCompanyRate;	

	/**
	 * 创建时间:创建记录时数据库会自动set值
	 */
	private Date createTime;	

	/**
	 * 修改时间:更新时数据库会自动set值
	 */
	private Date updateTime;	

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
	 * 正式公司价格比例
	 * @return 正式公司价格比例
	 */
	public Integer getStandardCompanyRate() {
		return standardCompanyRate;
	}

	/**
	 * 正式公司价格比例
	 * @param standardCompanyRate 正式公司价格比例
	 */
	public void setStandardCompanyRate(Integer standardCompanyRate) {
		this.standardCompanyRate = standardCompanyRate;
	}
	/**
	 * 正式公司价格比例
	 * @return 正式公司价格比例
	 */
	public Integer getDemocompanysCompanyRate() {
		return democompanysCompanyRate;
	}

	/**
	 * 正式公司价格比例
	 * @param democompanysCompanyRate 正式公司价格比例
	 */
	public void setDemocompanysCompanyRate(Integer democompanysCompanyRate) {
		this.democompanysCompanyRate = democompanysCompanyRate;
	}
	/**
	 * 正式公司价格比例
	 * @return 正式公司价格比例
	 */
	public Integer getCompetingCompanyRate() {
		return competingCompanyRate;
	}

	/**
	 * 正式公司价格比例
	 * @param competingCompanyRate 正式公司价格比例
	 */
	public void setCompetingCompanyRate(Integer competingCompanyRate) {
		this.competingCompanyRate = competingCompanyRate;
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
}
	