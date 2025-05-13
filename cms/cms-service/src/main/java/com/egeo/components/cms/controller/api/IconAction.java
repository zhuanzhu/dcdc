package com.egeo.components.cms.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.cms.business.IconManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/cms/icon")
public class IconAction extends BaseSpringController {
	
	@Resource(name="icon")
	private IconManage iconManage;


}
	