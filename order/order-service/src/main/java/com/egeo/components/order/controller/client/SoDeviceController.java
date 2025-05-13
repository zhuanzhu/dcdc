package com.egeo.components.order.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.SoDeviceClient;
import com.egeo.components.order.dto.SoDeviceDTO;
import com.egeo.components.order.dto.SoDevicePageDTO;
import com.egeo.components.order.service.read.SoDeviceReadService;
import com.egeo.components.order.service.write.SoDeviceWriteService;
import com.egeo.orm.PageResult;

@Controller
@RequestMapping("/client/order/soDevice") 
public class SoDeviceController implements SoDeviceClient{ 

	@Autowired
	private SoDeviceReadService soDeviceReadService;
	@Autowired
	private SoDeviceWriteService soDeviceWriteService;



	@Override
	@RequestMapping(value = "/findSoDeviceById", method = { RequestMethod.POST })
	@ResponseBody
	public SoDeviceDTO findSoDeviceById(@RequestBody SoDeviceDTO dto) {
		// TODO Auto-generated method stub
		return soDeviceReadService.findSoDeviceById(dto);
	}


	@Override
	@RequestMapping(value = "/findSoDeviceOfPage", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<SoDeviceDTO> findSoDeviceOfPage(@RequestBody SoDevicePageDTO dto) {
		// TODO Auto-generated method stub
		return soDeviceReadService.findSoDeviceOfPage(dto.getDto(), dto.getPage());
	}


	@Override
	@RequestMapping(value = "/findSoDeviceAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoDeviceDTO> findSoDeviceAll(@RequestBody SoDeviceDTO dto) {
		// TODO Auto-generated method stub
		return soDeviceReadService.findSoDeviceAll(dto);
	} 
 
}