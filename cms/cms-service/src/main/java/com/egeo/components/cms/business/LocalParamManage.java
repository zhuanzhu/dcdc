package com.egeo.components.cms.business;

import java.util.Map;

import com.egeo.web.JsonResult;

public interface LocalParamManage {

	/**
	 * 查询本地参数列表
	 * @return
	 */
	JsonResult<Map<String, Object>> localParamList();

}
	