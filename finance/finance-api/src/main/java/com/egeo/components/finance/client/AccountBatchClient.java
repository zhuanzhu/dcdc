package com.egeo.components.finance.client;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.egeo.components.finance.dto.AccountBatchDTO;
import com.egeo.components.finance.dto.CashFlowAccountDTO;
import com.egeo.components.finance.dto.CashFlowResultDTO;
import com.egeo.components.finance.dto.UnifiedCashFlowDTO;


@FeignClient(name = "service-finance-fgj",contextId="AccountBatchClient")
public interface AccountBatchClient {

	@RequestMapping(value = { "/client/finance/accountBatch/unifiedCashFlow" }, method = { RequestMethod.POST }) 
	public CashFlowResultDTO unifiedCashFlow(UnifiedCashFlowDTO dto); 
	
	
	@RequestMapping(value = { "/client/finance/accountBatch/findAccountBatchById" }, method = { RequestMethod.POST }) 
	public AccountBatchDTO findAccountBatchById(Long id);
}
