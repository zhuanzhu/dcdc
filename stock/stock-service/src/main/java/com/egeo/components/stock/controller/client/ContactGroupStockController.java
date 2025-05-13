package com.egeo.components.stock.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.stock.client.ContactGroupStockClient;
import com.egeo.components.stock.dto.ContactGroupStockDTO;
import com.egeo.components.stock.service.read.ContactGroupStockReadService;
import com.egeo.components.stock.service.write.ContactGroupStockWriteService;

@Controller
@RequestMapping("/client/stock/contactGroupStock") 
public class ContactGroupStockController implements ContactGroupStockClient{ 

	@Autowired
	private ContactGroupStockReadService contactGroupStockReadService;
	@Autowired
	private ContactGroupStockWriteService contactGroupStockWriteService;


	@Override
	@RequestMapping(value = "/findContactGroupStockAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<ContactGroupStockDTO> findContactGroupStockAll(@RequestBody ContactGroupStockDTO dto) {
		return contactGroupStockReadService.findContactGroupStockAll(dto);
	} 
}
