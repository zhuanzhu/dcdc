package com.egeo.components.cms.condition;

import com.egeo.components.cms.po.CmsCfgKeyPO;

/**
 * 
 * @author tan
 * @date 2018-12-13 17:01:38
 */
public class CmsCfgKeyCondition extends CmsCfgKeyPO {
	private static final long serialVersionUID = 1L;

	private Integer elementSort;
	
	/**
	 * 模板ID
	 */
	private Long templateId;

	public Integer getElementSort() {
		return elementSort;
	}

	public void setElementSort(Integer elementSort) {
		this.elementSort = elementSort;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	
}
	