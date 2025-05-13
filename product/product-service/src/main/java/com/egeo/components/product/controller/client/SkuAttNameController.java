package com.egeo.components.product.controller.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.egeo.components.product.client.SkuAttNameClient;
import com.egeo.components.product.service.read.SkuAttNameReadService;
import com.egeo.components.product.service.write.SkuAttNameWriteService;

@Controller
@RequestMapping("/client/product/skuAttName") 
public class SkuAttNameController implements SkuAttNameClient{ 

	@Autowired
	private SkuAttNameReadService skuAttNameReadService;
	@Autowired
	private SkuAttNameWriteService skuAttNameWriteService;
}

