package com.egeo.components.product.controller.api;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.business.MerchantSeriesProductAttManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/product/merchantSeriesProductAtt")
public class MerchantSeriesProductAttAction extends BaseSpringController {
	
	@Resource(name="merchantSeriesProductAtt")
	private MerchantSeriesProductAttManage merchantSeriesProductAttManage;
	
	
}
	