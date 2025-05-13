package com.egeo.components.finance.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.egeo.components.finance.client.AccountBatchClient;
import com.egeo.components.finance.dto.AccountBatchDTO;
import com.egeo.components.finance.dto.CashFlowResultDTO;
import com.egeo.components.finance.dto.UnifiedCashFlowDTO;
import com.egeo.components.finance.service.read.AccountBatchReadService;
import com.egeo.components.finance.service.write.AccountBatchWriteService;

@Controller
@RequestMapping("/client/finance/accountBatch") 
public class AccountBatchController implements AccountBatchClient{ 

	@Autowired
	private AccountBatchReadService accountBatchReadService;
	@Autowired
	private AccountBatchWriteService accountBatchWriteService;


	@Override
	@RequestMapping(value = "/unifiedCashFlow", method = { RequestMethod.POST })
	@ResponseBody
	public CashFlowResultDTO unifiedCashFlow(@RequestBody UnifiedCashFlowDTO dto) {
		return accountBatchWriteService.unifiedCashFlow(dto.getOutFlowAccs(), dto.getOutFlowAccType(),
				dto.getInFlowAccs(), dto.getInFlowAccType(), 
				dto.isNonNegLimit(),dto.getPlatformId(),dto.getType(),
				dto.getOrderId(),dto.getOrderCode(),dto.getOperatorId(),
				dto.getFinBatchType(),dto.getReasonId(),dto.getRemark(),dto.isRecharge()
				,dto.getOperateType());
	}


	@Override
	@RequestMapping(value = "/findAccountBatchById", method = { RequestMethod.POST })
	@ResponseBody
	public AccountBatchDTO findAccountBatchById(@RequestParam("id") Long id) {
		// TODO Auto-generated method stub
		return accountBatchReadService.findAccountBatchById(id);
	} 
}
