package com.egeo.components.stock.controller.api;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.MerchantProductActivityStockManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/stock/merchantProductActivityStock")
public class MerchantProductActivityStockAction extends BaseSpringController {
	
	@Resource(name="merchantProductActivityStock")
	private MerchantProductActivityStockManage merchantProductActivityStockManage;
	
	
}
	