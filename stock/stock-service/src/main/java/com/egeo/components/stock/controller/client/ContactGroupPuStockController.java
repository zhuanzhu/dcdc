package com.egeo.components.stock.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.client.ContactGroupPuStockClient;
import com.egeo.components.stock.dto.ContactGroupPuStockDTO;
import com.egeo.components.stock.service.read.ContactGroupPuStockReadService;
import com.egeo.components.stock.service.write.ContactGroupPuStockWriteService;

@Controller
@RequestMapping("/client/stock/contactGroupPuStock") 
public class ContactGroupPuStockController implements ContactGroupPuStockClient{ 

	@Autowired
	private ContactGroupPuStockReadService contactGroupPuStockReadService;
	@Autowired
	private ContactGroupPuStockWriteService contactGroupPuStockWriteService;


	@Override
	@RequestMapping(value = "/insertContactGroupPuStockWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertContactGroupPuStockWithTx(@RequestBody ContactGroupPuStockDTO dto) {
		return contactGroupPuStockWriteService.insertContactGroupPuStockWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findPuIdListByPuId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findPuIdListByPuId(@RequestParam("puid") Long puid) {
		return com.egeo.utils.StringUtils.longsToStrings(contactGroupPuStockReadService.findPuIdListByPuId(puid));
	} 
 
	@Override
	@RequestMapping(value = "/findContactGroupPuStockAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<ContactGroupPuStockDTO> findContactGroupPuStockAll(@RequestBody ContactGroupPuStockDTO dto) {
		return contactGroupPuStockReadService.findContactGroupPuStockAll(dto);
	} 
 
}