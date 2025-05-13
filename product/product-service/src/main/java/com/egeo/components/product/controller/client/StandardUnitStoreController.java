package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.StandardUnitStoreClient;
import com.egeo.components.product.dto.StandardUnitStoreDTO;
import com.egeo.components.product.service.read.StandardUnitStoreReadService;
import com.egeo.components.product.service.write.StandardUnitStoreWriteService;

@Controller
@RequestMapping("/client/product/standardUnitStore") 
public class StandardUnitStoreController implements StandardUnitStoreClient{ 

	@Autowired
	private StandardUnitStoreReadService standardUnitStoreReadService;
	@Autowired
	private StandardUnitStoreWriteService standardUnitStoreWriteService;


	@Override
	@RequestMapping(value = "/findStandardUnitStoreCount", method = { RequestMethod.POST })
	@ResponseBody
	public int findStandardUnitStoreCount(@RequestParam("suId") Long suId, @RequestParam("storeId") Long storeId) {
		return standardUnitStoreReadService.findStandardUnitStoreCount(suId, storeId);
	} 
 
	@Override
	@RequestMapping(value = "/standardUnitSumByStoreId", method = { RequestMethod.POST })
	@ResponseBody
	public Integer standardUnitSumByStoreId(@RequestParam("storeId") Long storeId,@RequestParam("platformId")  Long platformId) {
		return standardUnitStoreReadService.standardUnitSumByStoreId(storeId, platformId);
	} 
 
	@Override
	@RequestMapping(value = "/findByPuIdsByStoreId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findByPuIdsByStoreId(@RequestParam("storeId") Long storeId,@RequestParam("platformId")  Long platformId) {
		return com.egeo.utils.StringUtils.longsToStrings(standardUnitStoreReadService.findByPuIdsByStoreId(storeId, platformId));
	}

	@Override
	@RequestMapping(value = "/findStandardUnitStoreAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<StandardUnitStoreDTO> findStandardUnitStoreAll(@RequestBody StandardUnitStoreDTO dto) {
		// TODO Auto-generated method stub
		return standardUnitStoreReadService.findStandardUnitStoreAll(dto);
	} 
} 
