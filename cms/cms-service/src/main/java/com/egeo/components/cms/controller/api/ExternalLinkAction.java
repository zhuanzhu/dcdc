package com.egeo.components.cms.controller.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.ExternalLinkManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/cms/externalLink")
public class ExternalLinkAction extends BaseSpringController {
	
	@Resource(name="externalLink")
	private ExternalLinkManage externalLinkManage;


	/**
	 * 查询所有外部链接
	 * @param vo
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/findExternalLinkAll")
	@ResponseBody
	public JsonResult<List<Map<String, Object>>> findExternalLinkAll() {
		List<Map<String, Object>> rt = externalLinkManage.findExternalLinkAll();	
		return JsonResult.success(rt);
	}	
	
	/**
	 * 查询外部链接详情
	 * @return
	 */
	@RequestMapping(value = "/linkDetail")
	@ResponseBody
	public JsonResult<Map<String,Object>> linkDetail(Long id){
		return externalLinkManage.linkDetail(id);
	}

		
}
	