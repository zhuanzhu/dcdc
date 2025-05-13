package com.egeo.components.cms.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author jzh
 * @date 2018-04-04 13:24:44
 */
public class BannerCompanyDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private BannerDTO banner;
	/**
	 * 轮播图id
	 */
	private Long bannerId;	

	/**
	 * 公司id
	 */
	private Long companyId;	

	/**
	 * 创建时间
	 */
	private Date createTime;	

	/**
	 * 更新时间
	 */
	private Date updateTime;	
	/**
	 * 轮播图id
	 */
	private Integer up;	

	public BannerDTO getBanner() {
		return banner;
	}

	public void setBanner(BannerDTO banner) {
		this.banner = banner;
	}

	public Integer getUp() {
		return up;
	}

	public void setUp(Integer up) {
		this.up = up;
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
	 * 轮播图id
	 * @return 轮播图id
	 */
	public Long getBannerId() {
		return bannerId;
	}

	/**
	 * 轮播图id
	 * @param bannerId 轮播图id
	 */
	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
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
}
	