package com.egeo.components.cms.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.cms.dto.LinkableButtonPageDTO;


@FeignClient(name = "service-cms-fgj",contextId="LinkableButtonPageClient")
public interface LinkableButtonPageClient {

	@RequestMapping(value = { "/client/cms/linkableButtonPage/findLinkableButtonPageAll" }, method = { RequestMethod.POST }) 
	public List<LinkableButtonPageDTO> findLinkableButtonPageAll(LinkableButtonPageDTO dto); 
 
 
	@RequestMapping(value = { "/client/cms/linkableButtonPage/findCmsPageByLinkableId" }, method = { RequestMethod.POST }) 
	public List<LinkableButtonPageDTO> findCmsPageByLinkableId(Long linkableId); 
 
 
	@RequestMapping(value = { "/client/cms/linkableButtonPage/insertLinkableButtonPageWithTx" }, method = { RequestMethod.POST }) 
	public Long insertLinkableButtonPageWithTx(LinkableButtonPageDTO dto); 
 
 
	@RequestMapping(value = { "/client/cms/linkableButtonPage/deleteLinkableButtonPageByLinkableIdWithTx" }, method = { RequestMethod.POST }) 
	public int deleteLinkableButtonPageByLinkableIdWithTx(LinkableButtonPageDTO dto); 
 
 
	@RequestMapping(value = { "/client/cms/linkableButtonPage/insertBatchLinkableButtonPageWithTx" }, method = { RequestMethod.POST }) 
	public int insertBatchLinkableButtonPageWithTx(List<LinkableButtonPageDTO> dtos); 
 
 
}