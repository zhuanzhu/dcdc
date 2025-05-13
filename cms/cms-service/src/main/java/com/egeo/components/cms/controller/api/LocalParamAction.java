package com.egeo.components.cms.controller.api;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.LocalParamManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/localParam")
public class LocalParamAction extends BaseSpringController {
	
	@Resource(name="localParam")
	private LocalParamManage localParamManage;

	/**
	 * 本地参数列表
	 * @return
	 */
	@RequestMapping(value = "/localParamList")
	@ResponseBody
	public JsonResult<Map<String, Object>> localParamList(){
		return localParamManage.localParamList();
	}
		
}
	