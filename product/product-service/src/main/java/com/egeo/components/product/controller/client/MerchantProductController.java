package com.egeo.components.product.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.MerchantProductClient;
import com.egeo.components.product.dto.FindMerchantProductOfPageDTO;
import com.egeo.components.product.dto.MerchantProductDTO;
import com.egeo.components.product.service.read.MerchantProductReadService;
import com.egeo.components.product.service.write.MerchantProductWriteService;
import com.egeo.orm.PageResult;

@Controller
@RequestMapping("/client/product/merchantProduct") 
public class MerchantProductController implements MerchantProductClient{ 

	@Autowired
	private MerchantProductReadService merchantProductReadService;
	@Autowired
	private MerchantProductWriteService merchantProductWriteService;


	@Override
	@RequestMapping(value = "/findMerchantProductById", method = { RequestMethod.POST })
	@ResponseBody
	public MerchantProductDTO findMerchantProductById(@RequestBody MerchantProductDTO dto) {
		return merchantProductReadService.findMerchantProductById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findMerchantProductOfPage", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<MerchantProductDTO> findMerchantProductOfPage(@RequestBody FindMerchantProductOfPageDTO dto) {
		return merchantProductReadService.findMerchantProductOfPage(dto.getPriceStart(), dto.getPriceEnd(), dto.getStartProfit(), dto.getEndProfit(), dto.getStoreIds(), dto.getStarTime(), dto.getEndTime(), dto.getDto(), dto.getPage(), dto.getNameList());
	} 
}
