package com.egeo.components.promotion.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.ECardClient;
import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.service.read.ECardReadService;
import com.egeo.components.promotion.service.write.ECardWriteService;
import com.egeo.orm.PageResult;

@Controller
@RequestMapping("/client/promotion/eCard") 
public class ECardController implements ECardClient{ 

	@Autowired
	private ECardReadService eCardReadService;
	@Autowired
	private ECardWriteService eCardWriteService;


	@Override
	@RequestMapping(value = "/findECardAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<ECardDTO> findECardAll(@RequestBody ECardDTO dto) {
		return eCardReadService.findECardAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/findECardOfPage", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<ECardDTO> findECardOfPage(@RequestBody com.egeo.components.promotion.fo.FindECardOfPageFO fo) {
		return eCardReadService.findECardOfPage(fo.getDto(),fo.getPage());
	} 
 
	@Override
	@RequestMapping(value = "/updateCardTypeBySpuId", method = { RequestMethod.POST })
	@ResponseBody
	public int updateCardTypeBySpuId(@RequestParam("spuId") Long spuId, @RequestParam("cardType") Integer cardType) {
		return eCardWriteService.updateCardTypeBySpuId(spuId, cardType);
	} 
 
	@Override
	@RequestMapping(value = "/updateECardWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateECardWithTx(@RequestBody ECardDTO dto) {
		return eCardWriteService.updateECardWithTx(dto);
	} 
 
	@Override
	@RequestMapping(value = "/insertECardWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertECardWithTx(@RequestBody ECardDTO dto) {
		return eCardWriteService.insertECardWithTx(dto);
	} 
 
}