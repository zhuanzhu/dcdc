package com.egeo.components.cms.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.LinkableButtonPageDTO;


@FeignClient(name = "service-cms-fgj",contextId="LinkableButtonClient")
public interface LinkableButtonClient {

	@RequestMapping(value = { "/client/cms/linkableButton/findLinkableButtonById" }, method = { RequestMethod.POST }) 
	public LinkableButtonDTO findLinkableButtonById(Long id); 
 
 
	@RequestMapping(value = { "/client/cms/linkableButton/insertLinkableButtonWithTx" }, method = { RequestMethod.POST }) 
	public Long insertLinkableButtonWithTx(LinkableButtonDTO dto); 
 
 
	@RequestMapping(value = { "/client/cms/linkableButton/deleteLinkableButtonWithTx" }, method = { RequestMethod.POST }) 
	public int deleteLinkableButtonWithTx(LinkableButtonDTO dto); 
	
	
	@RequestMapping(value = { "/client/cms/linkableButton/insertBatchLinkableButtonPageWithTx" }, method = { RequestMethod.POST }) 
	public int insertBatchLinkableButtonPageWithTx(List<LinkableButtonPageDTO> dtos);
	
	@RequestMapping(value = { "/client/cms/linkableButton/deleteLinkableButtonPageByLinkableIdWithTx" }, method = { RequestMethod.POST })
	public int deleteLinkableButtonPageByLinkableIdWithTx(LinkableButtonPageDTO dto);
	
	@RequestMapping(value = { "/client/cms/linkableButton/findLinkableButtonPageAll" }, method = { RequestMethod.POST })
	public List<LinkableButtonPageDTO> findLinkableButtonPageAll(LinkableButtonPageDTO dto);
	
}