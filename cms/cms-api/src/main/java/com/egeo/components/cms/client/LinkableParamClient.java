package com.egeo.components.cms.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.cms.dto.LinkableParamDTO;


@FeignClient(name = "service-cms-fgj",contextId="LinkableParamClient")
public interface LinkableParamClient {

	@RequestMapping(value = { "/client/cms/linkableParam/findLinkableParamAll" }, method = { RequestMethod.POST }) 
	public List<LinkableParamDTO> findLinkableParamAll(LinkableParamDTO dto); 
 
 
	@RequestMapping(value = { "/client/cms/linkableParam/insertLinkableParamWithTx" }, method = { RequestMethod.POST }) 
	public Long insertLinkableParamWithTx(LinkableParamDTO dto); 
 
 
	@RequestMapping(value = { "/client/cms/linkableParam/deleteByPara" }, method = { RequestMethod.POST }) 
	public void deleteByPara(LinkableParamDTO linkableParam); 
 
 
}