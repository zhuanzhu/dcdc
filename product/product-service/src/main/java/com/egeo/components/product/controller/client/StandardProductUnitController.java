package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.StandardProductUnitClient;
import com.egeo.components.product.dto.StandardProductUnitDTO;
import com.egeo.components.product.service.read.StandardProductUnitReadService;
import com.egeo.components.product.service.write.StandardProductUnitWriteService;

@Controller
@RequestMapping("/client/product/standardProductUnit") 
public class StandardProductUnitController implements StandardProductUnitClient{ 

	@Autowired
	private StandardProductUnitReadService standardProductUnitReadService;
	@Autowired
	private StandardProductUnitWriteService standardProductUnitWriteService;


	@Override
	@RequestMapping(value = "/findStandardProductUnitById", method = { RequestMethod.POST })
	@ResponseBody
	public StandardProductUnitDTO findStandardProductUnitById(@RequestBody StandardProductUnitDTO dto) {
		return standardProductUnitReadService.findStandardProductUnitById(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findStandardProductUnitAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<StandardProductUnitDTO> findStandardProductUnitAll(@RequestBody StandardProductUnitDTO dto) {
		return standardProductUnitReadService.findStandardProductUnitAll(dto);
	}

	@Override
	@RequestMapping(value = "/querySpuBySuId", method = { RequestMethod.POST })
	@ResponseBody
	public StandardProductUnitDTO querySpuBySuId(@RequestBody Long suId) {
		// TODO Auto-generated method stub
		return standardProductUnitReadService.querySpuBySuId(suId);
	} 
} 
