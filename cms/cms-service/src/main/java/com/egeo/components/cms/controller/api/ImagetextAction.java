package com.egeo.components.cms.controller.api;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.cms.business.ImagetextManage;
import com.egeo.web.BaseSpringController;



@Controller
@RequestMapping("/api/cms/imagetext")
public class ImagetextAction extends BaseSpringController {
	
	@Resource(name="imagetext")
	private ImagetextManage imagetextManage;


}
	