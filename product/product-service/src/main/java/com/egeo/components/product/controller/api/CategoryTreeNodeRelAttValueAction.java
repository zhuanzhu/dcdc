package com.egeo.components.product.controller.api;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.CategoryTreeNodeRelAttValueManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/categoryTreeNodeRelAttValue")
public class CategoryTreeNodeRelAttValueAction extends BaseSpringController {
	
	@Resource(name="categoryTreeNodeRelAttValue")
	private CategoryTreeNodeRelAttValueManage categoryTreeNodeRelAttValueManage;
	
	
}
	