package com.egeo.components.cms.controller.api;


import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.business.ElementManage;
import com.egeo.utils.EmptyUtil;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/cms/element")
public class ElementAction extends BaseSpringController {
	
	@Resource(name="element")
	private ElementManage elementManage;

	/**
	 * 组件字典列表
	 * @param type 类型 0:商城类 1:应用类
	 * @return
	 */
	@RequestMapping("elementDict")
	@ResponseBody
	public JsonResult<Map<String, Object>> elementDict(Integer type, HttpServletRequest req){
		//校验platformId
		String str = req.getHeader("platformId");

		if(EmptyUtil.isEmpty(str)){
			return JsonResult.fail("platformId不能为null");

		}
		Long platformId=Long.valueOf(str);
		return elementManage.elementDict(type,platformId);
	}
	
	/**
	 * 删除组件
	 * @param elementId
	 * @return
	 */
	@RequestMapping("deleteElement")
	@ResponseBody
	public JsonResult<Map<String, Object>> deleteElement(Long elementId){
		return elementManage.deleteElement(elementId);
	}
}
	