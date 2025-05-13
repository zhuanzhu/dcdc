package com.egeo.components.cms.condition;

import com.egeo.components.cms.po.LinkableButtonPagePO;

/**
 * 
 * @author mingqiang.luo
 * @date 2018-12-14 14:35:33
 */
public class LinkableButtonPageCondition extends LinkableButtonPagePO {
	private static final long serialVersionUID = 1L;
	
	private String pageName;

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

}
	