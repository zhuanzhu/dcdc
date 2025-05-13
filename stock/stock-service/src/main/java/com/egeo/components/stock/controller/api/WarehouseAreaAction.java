package com.egeo.components.stock.controller.api;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.business.WarehouseAreaManage;
import com.egeo.web.BaseSpringController;
import com.egeo.web.JsonResult;



@Controller
@RequestMapping("/api/stock/warehouseArea")
public class WarehouseAreaAction extends BaseSpringController {
	
	@Resource(name="warehouseArea")
	private WarehouseAreaManage warehouseAreaManage;
	
	
}
	