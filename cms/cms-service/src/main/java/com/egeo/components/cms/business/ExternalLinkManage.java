package com.egeo.components.cms.business;

import java.util.List;
import java.util.Map;

import com.egeo.web.JsonResult;	

public interface ExternalLinkManage {

	/**
	 * 查询所有外部链接
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> findExternalLinkAll();

	/**
	 * 查询外部链接详情
	 * @param id
	 * @return
	 */
	public JsonResult<Map<String, Object>> linkDetail(Long id);

}
	