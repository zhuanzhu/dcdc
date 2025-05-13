package com.egeo.components.cms.condition;

import com.egeo.components.cms.po.CmsInstCfgPO;

/**
 * 
 * @author tan
 * @date 2018-12-14 17:57:13
 */
public class CmsInstCfgCondition extends CmsInstCfgPO {
	
	private static final long serialVersionUID = 1L;
	
	private Long cmsElementId;
	
	private String ckgKeyCode;

	private String ckgKeyDescription;
	
	private Integer ckgKeyType;
	
	private Integer sort;
	
	private Long instId;

	private Integer instStatus;
	
	public Long getInstId() {
		return instId;
	}

	public void setInstId(Long instId) {
		this.instId = instId;
	}

	public String getCkgKeyDescription() {
		return ckgKeyDescription;
	}

	public void setCkgKeyDescription(String ckgKeyDescription) {
		this.ckgKeyDescription = ckgKeyDescription;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getCmsElementId() {
		return cmsElementId;
	}

	public void setCmsElementId(Long cmsElementId) {
		this.cmsElementId = cmsElementId;
	}

	public String getCkgKeyCode() {
		return ckgKeyCode;
	}

	public void setCkgKeyCode(String ckgKeyCode) {
		this.ckgKeyCode = ckgKeyCode;
	}

	public Integer getCkgKeyType() {
		return ckgKeyType;
	}

	public void setCkgKeyType(Integer ckgKeyType) {
		this.ckgKeyType = ckgKeyType;
	}

	public Integer getInstStatus() {
		return instStatus;
	}

	public void setInstStatus(Integer instStatus) {
		this.instStatus = instStatus;
	}

}
	