package com.egeo.components.product.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.product.client.StandardUnitCompanyClient;
import com.egeo.components.product.dto.FindSuCompanyByCompanyIdAndTypeAndSuIdsDTO;
import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.service.read.StandardUnitCompanyReadService;
import com.egeo.components.product.service.write.StandardUnitCompanyWriteService;

@Controller
@RequestMapping("/client/product/standardUnitCompany") 
public class StandardUnitCompanyController implements StandardUnitCompanyClient{ 

	@Autowired
	private StandardUnitCompanyReadService standardUnitCompanyReadService;
	@Autowired
	private StandardUnitCompanyWriteService standardUnitCompanyWriteService;


	@Override
	@RequestMapping(value = "/findStandardUnitCompanyAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<StandardUnitCompanyDTO> findStandardUnitCompanyAll(@RequestBody StandardUnitCompanyDTO dto) {
		return standardUnitCompanyReadService.findStandardUnitCompanyAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findSuCompanyByCompanyIdAndTypeAndSuIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<StandardUnitCompanyDTO> findSuCompanyByCompanyIdAndTypeAndSuIds(@RequestBody FindSuCompanyByCompanyIdAndTypeAndSuIdsDTO fo) {
		return standardUnitCompanyReadService.findSuCompanyByCompanyIdAndTypeAndSuIds(fo.getDto(),fo.getSuIds());
	}

	@Override
	@RequestMapping(value = "/querySuCompanyAvailable", method = { RequestMethod.POST })
	@ResponseBody
	public boolean querySuCompanyAvailable(@RequestParam("suId") Long suId, @RequestParam("companyId") Long companyId,@RequestParam("clientId") Long clientId, @RequestParam("companyType") Integer companyType) {
		// TODO Auto-generated method stub
		return standardUnitCompanyReadService.querySuCompanyAvailable(suId, companyId, clientId, companyType);
	} 
}
