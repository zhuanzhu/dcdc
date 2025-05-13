package com.egeo.components.cms.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tan
 * @date 2018-12-13 17:01:37
 */
public class CmsMainCfgVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long company_id;
	//int(2) NULL1 夏日 2 普通 3 新年
	private int temp_type;
	private String banner_header ;
	private String banner_middle ;
	private String self_product ;
	private String recommendation;
	private Date createTime;
	private Date updateTime;
	private Long update_user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompanyId() {
		return company_id;
	}
	public void setCompanyId(Long company_id) {
		this.company_id = company_id;
	}
	public int getTempType() {
		return temp_type;
	}
	public void setTempType(int temp_type) {
		this.temp_type = temp_type;
	}
	public String getBannerHeader() {
		return banner_header;
	}
	public void setBannerHeader(String banner_header) {
		this.banner_header = banner_header;
	}
	public String getBannerMiddle() {
		return banner_middle;
	}
	public void setBannerMiddle(String banner_middle) {
		this.banner_middle = banner_middle;
	}
	public String getSelfProduct() {
		return self_product;
	}
	public void setSelfProduct(String self_product) {
		this.self_product = self_product;
	}
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getUpdateUser() {
		return update_user;
	}
	public void setUpdateUser(Long update_user) {
		this.update_user = update_user;
	}
	

}
	