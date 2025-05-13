package com.egeo.components.order.controller.client;

import java.util.List;

import com.egeo.components.order.dto.SoRefundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.order.client.SoRefundClient;
import com.egeo.components.order.service.read.SoRefundReadService;
import com.egeo.components.order.service.write.SoRefundWriteService;

@Controller
@RequestMapping("/client/order/soRefund")
public class SoRefundController implements SoRefundClient{

	@Autowired
	private SoRefundReadService soRefundReadService;
	@Autowired
	private SoRefundWriteService soRefundWriteService;


	@Override
	@RequestMapping(value = "/genSoRefundNO", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> genSoRefundNO() {
		return soRefundReadService.genSoRefundNO();
	}

	@Override
	@RequestMapping(value = "/genSoRefundById", method = { RequestMethod.POST })
	@ResponseBody
	public SoRefundDTO getSoRefundById(@RequestBody Long id) {
		SoRefundDTO dto = new SoRefundDTO();
		dto.setId(id);
		return soRefundReadService.findSoRefundById(dto);
	}

	@Override
	@RequestMapping(value = { "/changeRefundState" }, method = { RequestMethod.POST })
	@ResponseBody
	public Integer changeRefundState(@RequestBody SoRefundDTO dto){
		return soRefundWriteService.updateSoRefundWithTx(dto);
	}
}
