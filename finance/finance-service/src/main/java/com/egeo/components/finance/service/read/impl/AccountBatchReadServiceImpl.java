package com.egeo.components.finance.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.AccountBatchConverter;
import com.egeo.components.finance.dto.AccountBatchDTO;
import com.egeo.components.finance.manage.read.AccountBatchReadManage;
import com.egeo.components.finance.po.AccountBatchPO;
import com.egeo.components.finance.service.read.AccountBatchReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("accountBatchReadService")
public class AccountBatchReadServiceImpl implements AccountBatchReadService {
	@Autowired
	private AccountBatchReadManage accountBatchReadManage;

	@Override
	public AccountBatchDTO findAccountBatchById(Long id) {
		AccountBatchPO po = new AccountBatchPO();
		po.setId(id);
		AccountBatchPO res = accountBatchReadManage.findAccountBatchById(po);		
		return AccountBatchConverter.toDTO(res);
	}

	@Override
	public PageResult<AccountBatchDTO> findAccountBatchOfPage(AccountBatchDTO dto, Pagination page) {
		AccountBatchPO po = AccountBatchConverter.toPO(dto);
        PageResult<AccountBatchPO> pageResult = accountBatchReadManage.findAccountBatchOfPage(po, page);
        
        List<AccountBatchDTO> list = AccountBatchConverter.toDTO(pageResult.getList());
        PageResult<AccountBatchDTO> result = new PageResult<AccountBatchDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<AccountBatchDTO> findAccountBatchAll(AccountBatchDTO dto) {
		AccountBatchPO po = AccountBatchConverter.toPO(dto);
		List<AccountBatchPO> list = accountBatchReadManage.findAccountBatchAll(po);		
		return AccountBatchConverter.toDTO(list);
	}

	@Override
	public AccountBatchDTO queryAccountBatchByFinBatch(String finBatch) {
		return AccountBatchConverter.toDTO(accountBatchReadManage.queryAccountBatchByFinBatch(finBatch));
	}

	@Override
	public Integer queryBatchDayCount() {
		return accountBatchReadManage.queryBatchDayCount();
	}

	@Override
	public PageResult<AccountBatchDTO> queryAccountBatchPage(Long accountId,String batchNo,Pagination page, String keyWord, Long companyId,
			Integer type,Integer status,Long platformId) {
		PageResult<AccountBatchPO> poPage=accountBatchReadManage.queryAccountBatchPage(accountId,batchNo,page, keyWord, companyId, type,status,platformId);
		PageResult<AccountBatchDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(AccountBatchConverter.toDTO(poPage.getList()));
		return dtoPage;
	}

	@Override
	public List<AccountBatchDTO> querySameSumBatchInWeek(double sum) {
		
		return AccountBatchConverter.toDTO(accountBatchReadManage.querySameSumBatchInWeek(sum));
	}

	@Override
	public PageResult<AccountBatchDTO> queryRechargeAccountBatchPage(Pagination page, Long companyId, String batchNo) {
		PageResult<AccountBatchPO> poPage=accountBatchReadManage.queryRechargeAccountBatchPage(page,companyId,batchNo);
		PageResult<AccountBatchDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(AccountBatchConverter.toDTO(poPage.getList()));
		return dtoPage;
	}
}
	