package com.egeo.components.promotion.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.ExchangeBatchClient;
import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.components.promotion.service.read.ExchangeBatchReadService;
import com.egeo.components.promotion.service.write.ExchangeBatchWriteService;

@Controller
@RequestMapping("/client/promotion/exchangeBatch") 
public class ExchangeBatchController implements ExchangeBatchClient{ 

	@Autowired
	private ExchangeBatchReadService exchangeBatchReadService;
	@Autowired
	private ExchangeBatchWriteService exchangeBatchWriteService;


	@Override
	@RequestMapping(value = "/findExchangeIdsByBatch", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findExchangeIdsByBatch(@RequestBody ExchangeBatchDTO dto) {
		// TODO Auto-generated method stub
		return com.egeo.utils.StringUtils.longsToStrings(exchangeBatchReadService.findExchangeIdsByBatch(dto));
	}


	@Override
	@RequestMapping(value = "/findExchangeActivityByOldCouponUnitId", method = { RequestMethod.POST })
	@ResponseBody
	public List<String> findExchangeActivityByOldCouponUnitId(@RequestParam("batchId") Long batchId,@RequestParam("couponUnitStatus")  Integer couponUnitStatus) {
		// TODO Auto-generated method stub
		return com.egeo.utils.StringUtils.longsToStrings(exchangeBatchReadService.findExchangeActivityByOldCouponUnitId(batchId, couponUnitStatus));
	} 
} 
