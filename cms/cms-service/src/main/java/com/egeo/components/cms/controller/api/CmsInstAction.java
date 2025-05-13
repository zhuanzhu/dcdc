package com.egeo.components.cms.controller.api;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.egeo.components.cms.business.CmsInstManage;
import com.egeo.components.cms.dto.CmsInstDTO;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/cms/cmsInst")
public class CmsInstAction extends BaseSpringController {
	
	private Logger logger = LoggerFactory.getLogger(CmsInstAction.class);
	
	@Resource(name="cmsInst")
	private CmsInstManage cmsInstManage;

	/**
	 * 保存通用实例
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/insertCommonCmsInst")
	@ResponseBody
	public JsonResult<Long> findCfgKeyByTemplateId(Long elementId, String instName, String compantIds, String configJson) {
		logger.info("保存通用实例，elementId:{},instName:{},compantIds:{},configJson:{}",
				new Object[] { elementId, instName, compantIds, configJson });
		List<Long> companyIdList = JSONArray.parseArray(compantIds, Long.class);
		CmsInstDTO dto = new CmsInstDTO();
		dto.setCmsElementId(elementId);
		dto.setCmsPageId(-1L);
		dto.setName(instName);
		Long id = cmsInstManage.insertCommonCmsInstWithTx(dto , companyIdList, configJson);
		return JsonResult.success(id);
	}
	
	/**
	 * 查询通用实例
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/findCommonCmsInst")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCommonInstConfig(Long instId) {
		logger.info("查询通用实例，instId:{}", new Object[] { instId });
		Map<String, Object> instMap = cmsInstManage.findCommonInstConfig(instId);
		return JsonResult.success(instMap);
	}
		
}
	