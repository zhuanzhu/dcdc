package com.egeo.components.cms.condition;

import com.egeo.components.cms.po.CmsPageCfgPO;

/**
 * 
 * @author tan
 * @date 2018-12-18 12:27:56
 */
public class CmsPageCfgCondition extends CmsPageCfgPO {
	private static final long serialVersionUID = 1L;

	private String ckgKeyCode;
	
	private String ckgKeyDescription;
	
	private Integer ckgKeyType;

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

	public String getCkgKeyDescription() {
		return ckgKeyDescription;
	}

	public void setCkgKeyDescription(String ckgKeyDescription) {
		this.ckgKeyDescription = ckgKeyDescription;
	}
	
}
	