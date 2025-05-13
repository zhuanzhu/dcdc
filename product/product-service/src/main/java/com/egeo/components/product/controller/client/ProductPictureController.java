package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.ProductPictureClient;
import com.egeo.components.product.dto.ProductPictureDTO;
import com.egeo.components.product.service.read.ProductPictureReadService;
import com.egeo.components.product.service.write.ProductPictureWriteService;

@Controller
@RequestMapping("/client/product/productPicture") 
public class ProductPictureController implements ProductPictureClient{ 

	@Autowired
	private ProductPictureReadService productPictureReadService;
	@Autowired
	private ProductPictureWriteService productPictureWriteService;


	@Override
	@RequestMapping(value = "/findProductPictureAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<ProductPictureDTO> findProductPictureAll(@RequestBody ProductPictureDTO dto) {
		return productPictureReadService.findProductPictureAll(dto);
	} 
} 
