package com.egeo.components.promotion.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.promotion.dto.ExchangeOrderRecordDTO;
import com.egeo.components.promotion.dto.UpdateExchangeAndCouponWithTxDTO;


@FeignClient(name = "service-promotion-fgj",contextId="ExchangeOrderRecordClient")
public interface ExchangeOrderRecordClient {

	@RequestMapping(value = { "/client/promotion/exchangeOrderRecord/findExchangeOrderRecordAllByOrderCode" }, method = { RequestMethod.POST }) 
	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordAllByOrderCode(String orderCode); 
 
 
	@RequestMapping(value = { "/client/promotion/exchangeOrderRecord/findExchangeOrderRecordAll" }, method = { RequestMethod.POST }) 
	public List<ExchangeOrderRecordDTO> findExchangeOrderRecordAll(ExchangeOrderRecordDTO dto); 
 
 
	@RequestMapping(value = { "/client/promotion/exchangeOrderRecord/updateExchangeOrderRecordByOrderCodeWithTx" }, method = { RequestMethod.POST }) 
	public int updateExchangeOrderRecordByOrderCodeWithTx(ExchangeOrderRecordDTO recordDTO); 
 
 
	@RequestMapping(value = { "/client/promotion/exchangeOrderRecord/insertExchangeOrderRecordWithTx" }, method = { RequestMethod.POST }) 
	public Long insertExchangeOrderRecordWithTx(ExchangeOrderRecordDTO dto); 
 
	@RequestMapping(value = { "/client/promotion/exchangeOrderRecord/updateExchangeAndCouponWithTx" }, method = { RequestMethod.POST }) 
	public boolean updateExchangeAndCouponWithTx(UpdateExchangeAndCouponWithTxDTO dto);
}