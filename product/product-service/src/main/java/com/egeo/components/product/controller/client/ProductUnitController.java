package com.egeo.components.product.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.ProductUnitClient;
import com.egeo.components.product.dto.ProductUnitDTO;
import com.egeo.components.product.service.read.ProductUnitReadService;
import com.egeo.components.product.service.write.ProductUnitWriteService;

@Controller
@RequestMapping("/client/product/productUnit") 
public class ProductUnitController implements ProductUnitClient{ 

	@Autowired
	private ProductUnitReadService productUnitReadService;
	@Autowired
	private ProductUnitWriteService productUnitWriteService;


	@Override
	@RequestMapping(value = "/findProductUnitById", method = { RequestMethod.POST })
	@ResponseBody
	public ProductUnitDTO findProductUnitById(@RequestBody ProductUnitDTO dto) {
		return productUnitReadService.findProductUnitById(dto);
	} 
} 
