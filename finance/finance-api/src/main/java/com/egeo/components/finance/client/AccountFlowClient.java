package com.egeo.components.finance.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.finance.dto.AccountFlowDTO;


@FeignClient(name = "service-finance-fgj",contextId="AccountFlowClient")
public interface AccountFlowClient {

	@RequestMapping(value = { "/client/finance/accountFlow/findAccountFlowAll" }, method = { RequestMethod.POST }) 
	public List<AccountFlowDTO> findAccountFlowAll(AccountFlowDTO dto); 

	/**
	 * 查询订单退款流水
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = { "/client/finance/accountFlow/queryOrderRefundFlow" }, method = { RequestMethod.POST }) 
	public List<AccountFlowDTO> queryOrderRefundFlow(Long orderId);
	

	@RequestMapping(value = { "/client/finance/accountFlow/orderRefund" }, method = { RequestMethod.POST }) 
	public void orderRefund(String orderCode);
	@RequestMapping(value = { "/client/finance/accountFlow/orderConfirm" }, method = { RequestMethod.POST }) 
	public void orderConfirm(String orderCode);
}
 
 
