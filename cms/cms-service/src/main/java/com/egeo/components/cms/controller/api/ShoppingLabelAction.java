package com.egeo.components.cms.controller.api;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egeo.components.cms.business.ShoppingLabelManage;
import com.egeo.web.BaseSpringController;

@Controller
@RequestMapping("/api/cms/shoppingLabel")
public class ShoppingLabelAction extends BaseSpringController {

	@Resource(name = "shoppingLabel")
	private ShoppingLabelManage shoppingLabelManage;

}
