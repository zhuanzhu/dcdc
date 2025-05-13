package com.egeo.components.cms.controller.api;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.ImportTemplateDictManage;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/importTemplateDict")
public class ImportTemplateDictAction extends BaseSpringController {
	
	@Resource(name="importTemplateDict")
	private ImportTemplateDictManage importTemplateDictManage;


	/**
	 * 根据类型和平台id查询导入模版url
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/findImportTemplateDictUrlByType")
	@ResponseBody
	public JsonResult<String> findImportTemplateDictUrlByType(Integer type,HttpServletRequest req ) {
		String str = req.getHeader("platformId");
		Long platformId = null;
		if(EmptyUtil.isNotEmpty(str)){
			platformId = Long.valueOf(str);
		}else{
			return JsonResult.fail("平台Id不能为空");
		}
		logger.info("根据类型和平台id查询导入模版url,类型 = {},平台id = {}",type,platformId);
		if(type == null){
			return JsonResult.fail("类型不能为空");
		}
		String rt = importTemplateDictManage.findImportTemplateDictUrlByType(type,platformId);
		return JsonResult.success(rt);
					 
	}
}
	