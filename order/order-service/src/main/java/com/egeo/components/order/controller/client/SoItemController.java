package com.egeo.components.order.controller.client;

import java.util.List;

import com.egeo.components.order.po.SoItemPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.SoItemClient;
import com.egeo.components.order.dto.SoItemDTO;
import com.egeo.components.order.service.read.SoItemReadService;
import com.egeo.components.order.service.write.SoItemWriteService;

@Controller
@RequestMapping("/client/order/soItem")
public class SoItemController implements SoItemClient{

	@Autowired
	private SoItemReadService soItemReadService;
	@Autowired
	private SoItemWriteService soItemWriteService;


	@Override
	@RequestMapping(value = "/querySoItemListBySoId", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoItemDTO> querySoItemListBySoId(@RequestBody Long id) {
		return soItemReadService.querySoItemListBySoId(id);
	}

	@Override
	@RequestMapping(value = "/findPuIdBySoId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findPuIdBySoId(@RequestBody Long id) {
		return com.egeo.utils.StringUtils.longsToStrings(soItemReadService.findPuIdBySoId(id));
	}

	@Override
	@RequestMapping(value = "/querySoItemBySoId", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoItemDTO> querySoItemBySoId(@RequestBody Long soId) {
		return soItemReadService.querySoItemBySoId(soId);
	}

	@Override
	@RequestMapping(value = "/findAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoItemDTO> findAll(@RequestBody SoItemDTO soItemDTO) {
		return soItemReadService.findAll(soItemDTO);
	}

	@Override
	@RequestMapping(value = "/findSoItemByPuIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoItemDTO> findSoItemByPuIds(@RequestParam("puIds") List<String> puIds, @RequestParam("status") Integer status) {
		return soItemReadService.findSoItemByPuId(com.egeo.utils.StringUtils.stringsToLongs(puIds), status);
	}
	@Override
	@RequestMapping(value = "/findSoItemByPuId", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoItemDTO> findSoItemByPuId(@RequestParam("puId") Long puId, @RequestParam("status") Integer status) {
		return soItemReadService.findSoItemByPuId(puId, status);
	}

	@Override
	@RequestMapping(value = "/querySoItemListByMerchantIds", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoItemDTO> querySoItemListByMerchantIds(@RequestParam("merchantIdList") List<String> merchantIdList, @RequestParam("platformId") Long platformId) {
		// TODO Auto-generated method stub
		return soItemReadService.querySoItemListByMerchantIds(com.egeo.utils.StringUtils.stringsToLongs(merchantIdList), platformId);
	}

	@Override
	@RequestMapping(value = "/findProductIdsSoChild", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findProductIdsSoChild(@RequestBody Long soChildId){
		return soItemReadService.findProductIdsSoChild(soChildId);
	}

	@Override
	@RequestMapping(value = "/findSoItemsBySoChild", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoItemDTO> findSoItemsBySoChild(@RequestBody Long soChildId){
		return soItemReadService.findSoItemsSoChild(soChildId);
	}
}
