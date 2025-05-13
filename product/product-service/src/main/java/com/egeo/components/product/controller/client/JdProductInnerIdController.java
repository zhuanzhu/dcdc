package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.JdProductInnerIdClient;
import com.egeo.components.product.dto.JdProductInnerIdDTO;
import com.egeo.components.product.service.read.JdProductInnerIdReadService;

@Controller
@RequestMapping("/client/product/jdProductInnerId") 
public class JdProductInnerIdController implements JdProductInnerIdClient{ 

	@Autowired
	private JdProductInnerIdReadService jdProductInnerIdReadService;


	@Override
	@RequestMapping(value = "/findJdProductInnerIdAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<JdProductInnerIdDTO> findJdProductInnerIdAll(@RequestBody JdProductInnerIdDTO dto){
		return jdProductInnerIdReadService.findJdProductInnerIdAll(dto);
	} 

} 
