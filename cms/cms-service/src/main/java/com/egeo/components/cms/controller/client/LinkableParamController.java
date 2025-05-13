package com.egeo.components.cms.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.client.LinkableParamClient;
import com.egeo.components.cms.dto.LinkableParamDTO;
import com.egeo.components.cms.service.read.LinkableParamReadService;
import com.egeo.components.cms.service.write.LinkableParamWriteService;

@Controller
@RequestMapping("/client/cms/linkableParam") 
public class LinkableParamController implements LinkableParamClient{ 

	@Autowired
	private LinkableParamReadService linkableParamReadService;
	@Autowired
	private LinkableParamWriteService linkableParamWriteService;


	@Override
	@RequestMapping(value = "/findLinkableParamAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<LinkableParamDTO> findLinkableParamAll(@RequestBody LinkableParamDTO dto) {
		return linkableParamReadService.findLinkableParamAll( dto);
	} 
 
	@Override
	@RequestMapping(value = "/insertLinkableParamWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertLinkableParamWithTx(@RequestBody LinkableParamDTO dto) {
		return linkableParamWriteService.insertLinkableParamWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/deleteByPara", method = { RequestMethod.POST })
	@ResponseBody
	public void deleteByPara(@RequestBody LinkableParamDTO linkableParam) {
		linkableParamWriteService.deleteByPara(linkableParam);
	} 
 
}