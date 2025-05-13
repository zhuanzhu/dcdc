package com.egeo.components.product.controller.api;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantBrandManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchantBrand")
public class MerchantBrandAction extends BaseSpringController {
	
	@Resource(name="merchantBrand")
	private MerchantBrandManage merchantBrandManage;
	
	
}
	