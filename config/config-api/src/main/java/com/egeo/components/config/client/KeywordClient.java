package com.egeo.components.config.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.config.dto.KeywordDTO;
import com.egeo.components.config.dto.KeywordPageDTO;
import com.egeo.orm.PageResult;


@FeignClient(name = "service-config-fgj",contextId="KeywordClient")
public interface KeywordClient {

	@RequestMapping(value = { "/client/config/keyword/findKeywordById" }, method = { RequestMethod.POST }) 
	public KeywordDTO findKeywordById(KeywordDTO dto); 
 
 
	@RequestMapping(value = { "/client/config/keyword/findKeywordOfPage" }, method = { RequestMethod.POST }) 
	public PageResult<KeywordDTO> findKeywordOfPage(KeywordPageDTO dto); 
 
 
	@RequestMapping(value = { "/client/config/keyword/findKeywordAll" }, method = { RequestMethod.POST }) 
	public List<KeywordDTO> findKeywordAll(KeywordDTO dto); 
 
 
	@RequestMapping(value = { "/client/config/keyword/insertKeywordWithTx" }, method = { RequestMethod.POST }) 
	public Long insertKeywordWithTx(KeywordDTO dto); 
 
 
	@RequestMapping(value = { "/client/config/keyword/updateKeywordWithTx" }, method = { RequestMethod.POST }) 
	public int updateKeywordWithTx(KeywordDTO dto); 
 
 
	@RequestMapping(value = { "/client/config/keyword/deleteKeywordWithTx" }, method = { RequestMethod.POST }) 
	public int deleteKeywordWithTx(KeywordDTO dto); 
 
 
}