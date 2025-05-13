package com.egeo.components.promotion.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.fo.FindECardOfPageFO;
import com.egeo.orm.PageResult;


@FeignClient(name = "service-promotion-fgj",contextId="ECardClient")
public interface ECardClient {

	@RequestMapping(value = { "/client/promotion/eCard/findECardAll" }, method = { RequestMethod.POST }) 
	public List<ECardDTO> findECardAll(ECardDTO dto); 
 
 
	@RequestMapping(value = { "/client/promotion/eCard/findECardOfPage" }, method = { RequestMethod.POST }) 
	public PageResult<ECardDTO> findECardOfPage(FindECardOfPageFO fo); 
 
 
	@RequestMapping(value = { "/client/promotion/eCard/updateCardTypeBySpuId" }, method = { RequestMethod.POST }) 
	public int updateCardTypeBySpuId(@RequestParam("spuId") Long spuId, @RequestParam("cardType") Integer cardType); 
 
 
	@RequestMapping(value = { "/client/promotion/eCard/updateECardWithTx" }, method = { RequestMethod.POST }) 
	public int updateECardWithTx(ECardDTO dto); 
 
 
	@RequestMapping(value = { "/client/promotion/eCard/insertECardWithTx" }, method = { RequestMethod.POST }) 
	public Long insertECardWithTx(ECardDTO dto); 
 
 
}