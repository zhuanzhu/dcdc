package com.egeo.components.cms.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.client.LinkableButtonClient;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.service.read.LinkableButtonPageReadService;
import com.egeo.components.cms.service.read.LinkableButtonReadService;
import com.egeo.components.cms.service.write.LinkableButtonPageWriteService;
import com.egeo.components.cms.service.write.LinkableButtonWriteService;

@Controller
@RequestMapping("/client/cms/linkableButton") 
public class LinkableButtonController implements LinkableButtonClient{ 

	@Autowired
	private LinkableButtonReadService linkableButtonReadService;
	@Autowired
	private LinkableButtonWriteService linkableButtonWriteService;
	@Autowired
	private LinkableButtonPageReadService linkableButtonPageReadService;
	@Autowired
	private LinkableButtonPageWriteService linkableButtonPageWriteService;


	
	@RequestMapping(value = "/findLinkableButtonById", method = { RequestMethod.POST })
	@ResponseBody
	public LinkableButtonDTO findLinkableButtonById(@RequestBody Long id) {
		return linkableButtonReadService.findLinkableButtonById(id);
	} 
 
	
	@RequestMapping(value = "/insertLinkableButtonWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertLinkableButtonWithTx(@RequestBody LinkableButtonDTO dto) {
		return linkableButtonWriteService.insertLinkableButtonWithTx(dto);
	} 
 
	
	@RequestMapping(value = "/deleteLinkableButtonWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteLinkableButtonWithTx(@RequestBody LinkableButtonDTO dto) {
		return linkableButtonWriteService.deleteLinkableButtonWithTx(dto);
	}


	@Override
	@RequestMapping(value = "/insertBatchLinkableButtonPageWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int insertBatchLinkableButtonPageWithTx(@RequestBody List<LinkableButtonPageDTO> dtos) {
		// TODO Auto-generated method stub
		return linkableButtonPageWriteService.insertBatchLinkableButtonPageWithTx(dtos);
	}


	@Override
	@RequestMapping(value = "/deleteLinkableButtonPageByLinkableIdWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteLinkableButtonPageByLinkableIdWithTx(@RequestBody LinkableButtonPageDTO dto) {
		// TODO Auto-generated method stub
		return linkableButtonPageWriteService.deleteLinkableButtonPageByLinkableIdWithTx(dto);
	}


	@Override
	@RequestMapping(value = "/findLinkableButtonPageAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<LinkableButtonPageDTO> findLinkableButtonPageAll(@RequestBody LinkableButtonPageDTO dto) {
		// TODO Auto-generated method stub
		return linkableButtonPageReadService.findLinkableButtonPageAll(dto);
	} 
 
}