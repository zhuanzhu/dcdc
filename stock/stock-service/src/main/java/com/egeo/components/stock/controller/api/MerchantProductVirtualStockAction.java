package com.egeo.components.stock.controller.api;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.MerchantProductVirtualStockManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/stock/merchantProductVirtualStock")
public class MerchantProductVirtualStockAction extends BaseSpringController {
	
	@Resource(name="merchantProductVirtualStock")
	private MerchantProductVirtualStockManage merchantProductVirtualStockManage;
	
	
}
	