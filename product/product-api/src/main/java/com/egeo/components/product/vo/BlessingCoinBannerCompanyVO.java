package com.egeo.components.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author min
 * @date 2018-01-25 14:11:58
 */
public class BlessingCoinBannerCompanyVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	/**
	 * 轮播图id
	 */
	private Long blessingCoinBannerId;
	/**
	 * 公司id
	 */
	private Long companyId;
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
	 * 轮播图id
	 * @return 轮播图id
	 */
	public Long getBlessingCoinBannerId() {
		return blessingCoinBannerId;
	}

	/**
	 * 轮播图id
	 * @param blessingCoinBannerId 轮播图id
	 */
	public void setBlessingCoinBannerId(Long blessingCoinBannerId) {
		this.blessingCoinBannerId = blessingCoinBannerId;
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
	