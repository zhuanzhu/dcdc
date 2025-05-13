package com.egeo.components.cms.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author wyy
 * @date 2018-08-02 10:33:40
 */
public class NavigationLabelWebVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	/**
	 * 导航栏标签名称
	 */
	private String navigationLabelName;
	
	/**
	 * 跳转类型 0:本地参数 1:h5内链 2:h5外链 3:商品组合 4:单个商品 5:无效果
	 */
	private Integer linkType;	

	/**
	 * 跳转目标id 如商品id,列表id,外链id等等
	 */
	private Long linkId;	

	/**
	 * 跳转参数json
	 */
	private String linkParam;	

	private List<Long> companyIds;
	
	private boolean suCompanyAvailable;
	
	/**
	 * 单个商品模板id
	 */
	private Long suTmplId;
	
	public List<Long> getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(List<Long> companyIds) {
		this.companyIds = companyIds;
	}

	public boolean isSuCompanyAvailable() {
		return suCompanyAvailable;
	}

	public void setSuCompanyAvailable(boolean suCompanyAvailable) {
		this.suCompanyAvailable = suCompanyAvailable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNavigationLabelName() {
		return navigationLabelName;
	}

	public void setNavigationLabelName(String navigationLabelName) {
		this.navigationLabelName = navigationLabelName;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public Long getLinkId() {
		return linkId;
	}

	public void setLinkId(Long linkId) {
		this.linkId = linkId;
	}

	public String getLinkParam() {
		return linkParam;
	}

	public void setLinkParam(String linkParam) {
		this.linkParam = linkParam;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public Long getSuTmplId() {
		return suTmplId;
	}

	public void setSuTmplId(Long suTmplId) {
		this.suTmplId = suTmplId;
	}

	/**
	 * 跳转连接
	 */
	private String linkUrl;	
	
}
	