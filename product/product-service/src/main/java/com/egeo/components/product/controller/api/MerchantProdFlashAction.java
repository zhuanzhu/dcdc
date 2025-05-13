package com.egeo.components.product.controller.api;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantProdFlashManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchantProdFlash")
public class MerchantProdFlashAction extends BaseSpringController {
	
	@Resource(name="merchantProdFlash")
	private MerchantProdFlashManage merchantProdFlashManage;
	
	
}
	