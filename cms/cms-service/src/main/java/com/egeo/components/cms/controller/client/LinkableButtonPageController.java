package com.egeo.components.cms.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.cms.client.LinkableButtonPageClient;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;
import com.egeo.components.cms.service.read.LinkableButtonPageReadService;
import com.egeo.components.cms.service.write.LinkableButtonPageWriteService;

@Controller
@RequestMapping("/client/cms/linkableButtonPage") 
public class LinkableButtonPageController  implements  LinkableButtonPageClient{ 

	@Autowired
	private LinkableButtonPageReadService linkableButtonPageReadService;
	@Autowired
	private LinkableButtonPageWriteService linkableButtonPageWriteService;


	
	@RequestMapping(value = "/findLinkableButtonPageAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<LinkableButtonPageDTO> findLinkableButtonPageAll(@RequestBody LinkableButtonPageDTO dto) {
		return linkableButtonPageReadService.findLinkableButtonPageAll(dto);
	} 
 
	
	@RequestMapping(value = "/findCmsPageByLinkableId", method = { RequestMethod.POST })
	@ResponseBody
	public List<LinkableButtonPageDTO> findCmsPageByLinkableId(Long linkableId) {
		return linkableButtonPageReadService.findCmsPageByLinkableId(linkableId);
	} 
 
	
	@RequestMapping(value = "/insertLinkableButtonPageWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertLinkableButtonPageWithTx(@RequestBody LinkableButtonPageDTO dto) {
		return linkableButtonPageWriteService.insertLinkableButtonPageWithTx(dto);
	} 
 
	
	@RequestMapping(value = "/deleteLinkableButtonPageByLinkableIdWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int deleteLinkableButtonPageByLinkableIdWithTx(@RequestBody LinkableButtonPageDTO dto) {
		return linkableButtonPageWriteService.deleteLinkableButtonPageByLinkableIdWithTx(dto);
	} 
 
	
	@RequestMapping(value = "/insertBatchLinkableButtonPageWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int insertBatchLinkableButtonPageWithTx(@RequestBody List<LinkableButtonPageDTO> dtos) {
		return linkableButtonPageWriteService.insertBatchLinkableButtonPageWithTx(dtos);
	} 
 
}