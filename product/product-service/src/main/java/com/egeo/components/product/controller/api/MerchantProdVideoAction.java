package com.egeo.components.product.controller.api;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProdVideoManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchantProdVideo")
public class MerchantProdVideoAction extends BaseSpringController {
	
	@Resource(name="merchantProdVideo")
	private MerchantProdVideoManage merchantProdVideoManage;
	
	
}
	