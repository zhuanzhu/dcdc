package com.egeo.components.cms.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.client.FunctionModuleCategoryClient;
import com.egeo.components.cms.dto.FunctionModuleCategoryDTO;
import com.egeo.components.cms.service.read.FunctionModuleCategoryReadService;
import com.egeo.components.cms.service.write.FunctionModuleCategoryWriteService;

@Controller
@RequestMapping("/client/cms/functionModuleCategory") 
public class FunctionModuleCategoryController implements FunctionModuleCategoryClient{ 

	@Autowired
	private FunctionModuleCategoryReadService functionModuleCategoryReadService;
	@Autowired
	private FunctionModuleCategoryWriteService functionModuleCategoryWriteService;


	@Override
	@RequestMapping(value = "/findFunctionModuleCategoryAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<FunctionModuleCategoryDTO> findFunctionModuleCategoryAll(@RequestBody FunctionModuleCategoryDTO dto) {
		return functionModuleCategoryReadService.findFunctionModuleCategoryAll(dto);
	} 
}
