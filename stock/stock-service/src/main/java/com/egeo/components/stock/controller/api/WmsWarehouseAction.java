package com.egeo.components.stock.controller.api;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.WmsWarehouseManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/stock/wmsWarehouse")
public class WmsWarehouseAction extends BaseSpringController {
	
	@Resource(name="wmsWarehouse")
	private WmsWarehouseManage wmsWarehouseManage;
	
	
}
	