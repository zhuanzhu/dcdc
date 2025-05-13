package com.egeo.components.cms.controller.api;


import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.CmsCfgKeyManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;

@Controller
@RequestMapping("/api/cms/cmsCfgKey")
public class CmsCfgKeyAction extends BaseSpringController {
	
	private Logger logger = LoggerFactory.getLogger(CmsCfgKeyAction.class);
	
	@Resource(name="cmsCfgKey")
	private CmsCfgKeyManage cmsCfgKeyManage;

	/**
	 * 根据模板ID查询模板下所有组件的配置项
	 * @param templateId
	 * @return
	 */
	@RequestMapping(value = "/findCfgKeyByTemplateId")
	@ResponseBody
	public JsonResult<Map<String, Object>> findCfgKeyByTemplateId(Long templateId, Long pageId) {
		logger.info("根据模板ID查询模板下所有组件的配置项，templateId:{}", templateId);
		Map<String, Object> result = null;
		try {
			result = cmsCfgKeyManage.findCfgKeyByTemplateId(templateId, pageId);
		} catch (Exception e) {
			logger.error("根据模板ID查询模板下所有组件的配置项错误", e);
		}
		return JsonResult.success(result);
	}
		
}
	