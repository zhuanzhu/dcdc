package com.egeo.components.cms.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.cms.business.SuListManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/cms/suList")
public class SuListAction extends BaseSpringController {
	
	@Resource(name="suList")
	private SuListManage suListManage;


}
		