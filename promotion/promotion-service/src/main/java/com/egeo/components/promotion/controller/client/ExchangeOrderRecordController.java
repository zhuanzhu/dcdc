package com.egeo.components.promotion.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.promotion.client.ExchangeOrderRecordClient;
import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.dto.UpdateExchangeAndCouponWithTxDTO;
import com.egeo.components.promotion.service.read.ExchangeOrderRecordReadService;
import com.egeo.components.promotion.service.write.ExchangeOrderRecordWriteService;

@Controller
@RequestMapping("/client/promotion/exchangeOrderRecord") 
public class ExchangeOrderRecordController implements ExchangeOrderRecordClient{ 

	@Autowired
	private ExchangeOrderRecordReadService exchangeOrderRecordReadService;
	@Autowired
	private ExchangeOrderRecordWriteService exchangeOrderRecordWriteService;


	@Override
	@RequestMapping(value = "/findExchangeOrderRecordAllByOrderCode", method = { RequestMethod.POST })
	@ResponseBody
	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordAllByOrderCode(@RequestBody String orderCode) {
		return exchangeOrderRecordReadService.findExchangeOrderRecordAllByOrderCode(orderCode);
	} 
 
	@Override
	@RequestMapping(value = "/findExchangeOrderRecordAll", method = { RequestMethod.POST })
	@ResponseBody
	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordAll(@RequestBody ExchangeOrderRecordDTO dto) {
		return exchangeOrderRecordReadService.findExchangeOrderRecordAll(dto);
	} 
 
	@Override
	@RequestMapping(value = "/updateExchangeOrderRecordByOrderCodeWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public int updateExchangeOrderRecordByOrderCodeWithTx(@RequestBody ExchangeOrderRecordDTO recordDTO) {
		return exchangeOrderRecordWriteService.updateExchangeOrderRecordByOrderCodeWithTx(recordDTO);
	} 
 
	@Override
	@RequestMapping(value = "/insertExchangeOrderRecordWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public Long insertExchangeOrderRecordWithTx(@RequestBody ExchangeOrderRecordDTO dto) {
		return exchangeOrderRecordWriteService.insertExchangeOrderRecordWithTx(dto);
	}

	@Override
	@RequestMapping(value = "/updateExchangeAndCouponWithTx", method = { RequestMethod.POST })
	@ResponseBody
	public boolean updateExchangeAndCouponWithTx(@RequestBody UpdateExchangeAndCouponWithTxDTO dto) {
		// TODO Auto-generated method stub
		return exchangeOrderRecordWriteService.updateExchangeAndCouponWithTx(dto.getCouponUnitCode(), dto.getCouponUnitStatus(), dto.getCouponUnitId(), dto.getCouponBatchDTO(), dto.getRecordId(), dto.getUserId());
	} 
 
}