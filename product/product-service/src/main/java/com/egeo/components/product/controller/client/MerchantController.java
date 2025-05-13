package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.MerchantClient;
import com.egeo.components.product.dto.MerchantDTO;
import com.egeo.components.product.service.read.MerchantReadService;
import com.egeo.components.product.service.write.MerchantWriteService;

@Controller
@RequestMapping("/client/product/merchant") 
public class MerchantController implements MerchantClient{ 

	@Autowired
	private MerchantReadService merchantReadService;
	@Autowired
	private MerchantWriteService merchantWriteService;


	@Override
	@RequestMapping(value = "/findMerchantById", method = { RequestMethod.POST })
	@ResponseBody
	public MerchantDTO findMerchantById(@RequestBody MerchantDTO dto) {
		return merchantReadService.findMerchantById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findMerchantAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<MerchantDTO> findMerchantAll(@RequestBody MerchantDTO dto) {
		return merchantReadService.findMerchantAll(dto);
	} 
} 
