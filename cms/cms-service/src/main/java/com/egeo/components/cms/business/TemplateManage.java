package com.egeo.components.cms.business;

import java.util.Map;

import com.egeo.components.cms.vo.TemplateInstContentVO;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;

public interface TemplateManage {

	/**
	 * 查询模板结构
	 * @param companyId 
	 * @param companyId
	 * @return
	 */
	public JsonResult<Map<String, Object>> templateConstruct(Integer clientType,Integer type, Long companyId, Long pageTabId,Long platformId);

	/**
	 * 后台模板分页列表
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @param type 
	 * @param clientType 
	 * @param status 
	 * @return
	 */
	public JsonResult<Map<String, Object>> templatePage(
			Integer pageNo, Integer pageSize, String name, Integer type, 
			Integer status, Integer clientType,Integer companyType,Long platformId);

	/**
	 * 后台启用模板
	 *
     * @param platformId
     * @param templateId
     * @return
	 */
	public JsonResult<Map<String, Object>> useTemplate(Long platformId, Long templateId);

	/**
	 * 新增/编辑模板
	 * @return
	 */
	public JsonResult<Map<String, Object>> saveTemplate(Long templateId,String name,String remark,
			Integer clientType,Integer showFgj,String elementIds,Long platformId);

	/**
	 * 模板详情
	 *
     * @param id
     * @param templateId
     * @param platformId
	 * @return
	 */
	public JsonResult<Map<String, Object>> templateDetail(Long templateId, Long platformId);

	/**
	 * 新建空白模板
	 * @param type 
	 * @return
	 */
	public JsonResult<Map<String, Object>> createEmptyTmpl(Integer type, Integer companyType,Long platformId);

    JsonResult<TemplateInstContentVO> templateInstContent(Long clientId, Long storeId, Integer clientType, Integer type, Long companyId, Long platformId, Long pageTabId, Pagination page);
}
	