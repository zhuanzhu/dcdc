package com.egeo.components.promotion.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.business.HelloManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;


@Controller
@RequestMapping("/api/promotion/hello")
public class HelloAction extends BaseSpringController {

	@Resource(name = "hello")
	private HelloManage helloManage;


	@RequestMapping(value = "/hello")
	@ResponseBody
	public JsonResult<String> helloWorld() {
		return helloManage.helloWord();
	}


}

	