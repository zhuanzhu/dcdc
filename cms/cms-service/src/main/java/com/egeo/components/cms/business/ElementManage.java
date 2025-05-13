package com.egeo.components.cms.business;

import java.util.Map;

import com.egeo.web.JsonResult;	

public interface ElementManage {

	/**
	 * 组件字典列表
	 * @param type
	 * @param platformId
     * @return
	 */
	public JsonResult<Map<String, Object>> elementDict(Integer type, Long platformId);

	/**
	 * 删除组件
	 * @param elementId
	 * @return
	 */
	public JsonResult<Map<String, Object>> deleteElement(Long elementId);
}
	