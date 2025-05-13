package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.StoreProductUnitClient;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.service.read.StoreProductUnitReadService;
import com.egeo.components.product.service.write.StoreProductUnitWriteService;

@Controller
@RequestMapping("/client/product/storeProductUnit") 
public class StoreProductUnitController implements StoreProductUnitClient{ 

	@Autowired
	private StoreProductUnitReadService storeProductUnitReadService;
	@Autowired
	private StoreProductUnitWriteService storeProductUnitWriteService;


	@Override
	@RequestMapping(value = "/findStoreProductUnitAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<StoreProductUnitDTO> findStoreProductUnitAll(@RequestBody StoreProductUnitDTO dto) {
		return storeProductUnitReadService.findStoreProductUnitAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findStorePuIdsByStoreId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findStorePuIdsByStoreId(@RequestParam("storeId") Long storeId,@RequestParam("platformId")  Long platformId) {
		return com.egeo.utils.StringUtils.longsToStrings(storeProductUnitReadService.findStorePuIdsByStoreId(storeId, platformId));
	}

	@Override
	@RequestMapping(value = "/findStoreProductUnitById", method = { RequestMethod.POST })
	@ResponseBody
	public StoreProductUnitDTO findStoreProductUnitById(@RequestBody StoreProductUnitDTO dto) {
		// TODO Auto-generated method stub
		return storeProductUnitReadService.findStoreProductUnitById(dto);
	} 
} 
