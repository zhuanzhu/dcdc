package com.egeo.components.product.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.PictureClient;
import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.service.read.PictureReadService;
import com.egeo.components.product.service.write.PictureWriteService;

@Controller
@RequestMapping("/client/product/picture") 
public class PictureController implements PictureClient{ 

	@Autowired
	private PictureReadService pictureReadService;
	@Autowired
	private PictureWriteService pictureWriteService;


	@Override
	@RequestMapping(value = "/findById", method = { RequestMethod.POST })
	@ResponseBody
    public PictureDTO findById(@RequestBody PictureDTO dto) {
		return pictureReadService.findById(dto);
	} 
} 
