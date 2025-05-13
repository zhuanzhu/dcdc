package com.egeo.components.order.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.SoChildClient;
import com.egeo.components.order.dto.DeliverOrderWithTxDTO;
import com.egeo.components.order.dto.SoChildDTO;
import com.egeo.components.order.dto.SoChildPageDTO;
import com.egeo.components.order.service.read.SoChildReadService;
import com.egeo.components.order.service.write.SoChildWriteService;
import com.egeo.orm.PageResult;

@Controller
@RequestMapping("/client/order/soChild")
public class SoChildController implements SoChildClient{

	@Autowired
	private SoChildReadService soChildReadService;
	@Autowired
	private SoChildWriteService soChildWriteService;


	@Override
	@RequestMapping(value = "/deliverOrderWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public void deliverOrderWithTx(@RequestBody DeliverOrderWithTxDTO dto) {
		soChildWriteService.deliverOrderWithTx(dto.getSoChild(), dto.getSoPackage(),dto.getDeliverMap());
	}

	@Override
	@RequestMapping(value = "/querySoChildListBySoId", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoChildDTO> querySoChildListBySoId(@RequestBody Long id) {
		return soChildReadService.querySoChildListBySoId(id);
	}

	@Override
	@RequestMapping(value = "/findMerchantChildOrderOfPage", method = { RequestMethod.POST })
	@ResponseBody
	public PageResult<SoChildDTO> findMerchantChildOrderOfPage(@RequestBody SoChildPageDTO dto) {
		return soChildReadService.findMerchantChildOrderOfPage(dto.getDto(), dto.getPage());
	}

	@Override
	@RequestMapping(value = "/findSoChildAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<SoChildDTO> findSoChildAll(@RequestBody SoChildDTO dto) {
		return soChildReadService.findSoChildAll(dto);
	}
	@Override
	@RequestMapping(value = { "/findSoChildByChildCode" }, method = { RequestMethod.POST })
	@ResponseBody
	public SoChildDTO findSoChildByChildCode(@RequestBody String childCode){
		return soChildReadService.querySoChildByChildCode(childCode);
	}

	@Override
	@RequestMapping(value = { "/findSoChildById" }, method = { RequestMethod.POST })
	@ResponseBody
	public SoChildDTO findSoChildById(@RequestBody Long id){
		return soChildReadService.findSoChildById(id);
	}
}
