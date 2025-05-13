package com.egeo.components.stock.controller.api;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.MerchantProductWarehouseManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/stock/merchantProductWarehouse")
public class MerchantProductWarehouseAction extends BaseSpringController {
	
	@Resource(name="merchantProductWarehouse")
	private MerchantProductWarehouseManage merchantProductWarehouseManage;
	
	
}
	