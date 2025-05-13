package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.StoreTreeNodeClient;
import com.egeo.components.product.dto.StoreTreeNodeDTO;
import com.egeo.components.product.service.read.StoreTreeNodeReadService;
import com.egeo.components.product.service.write.StoreTreeNodeWriteService;

@Controller
@RequestMapping("/client/product/storeTreeNode") 
public class StoreTreeNodeController implements StoreTreeNodeClient{ 

	@Autowired
	private StoreTreeNodeReadService storeTreeNodeReadService;
	@Autowired
	private StoreTreeNodeWriteService storeTreeNodeWriteService;


	@Override
	@RequestMapping(value = "/findStoreTreeNodeAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<StoreTreeNodeDTO> findStoreTreeNodeAll(@RequestBody StoreTreeNodeDTO dto) {
		return storeTreeNodeReadService.findStoreTreeNodeAll(dto);
	} 
} 
