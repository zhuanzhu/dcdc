package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.StoreClient;
import com.egeo.components.product.dto.StoreDTO;
import com.egeo.components.product.service.read.StoreReadService;
import com.egeo.components.product.service.write.StoreWriteService;

@Controller
@RequestMapping("/client/product/store") 
public class StoreController implements StoreClient{ 

	@Autowired
	private StoreReadService storeReadService;
	@Autowired
	private StoreWriteService storeWriteService;


	@Override
	@RequestMapping(value = "/findStoreById", method = { RequestMethod.POST })
	@ResponseBody
	public StoreDTO findStoreById(@RequestBody StoreDTO dto) {
		return storeReadService.findStoreById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findStoreAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<StoreDTO> findStoreAll(@RequestBody StoreDTO dto) {
		return storeReadService.findStoreAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findHeadStoreByStoreId", method = { RequestMethod.POST })
	@ResponseBody
	public StoreDTO findHeadStoreByStoreId(@RequestBody Long storeId) {
		return storeReadService.findHeadStoreByStoreId(storeId);
	} 
} 
