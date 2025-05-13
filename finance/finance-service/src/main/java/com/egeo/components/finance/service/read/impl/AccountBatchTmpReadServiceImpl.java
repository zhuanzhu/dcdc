package com.egeo.components.finance.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.AccountBatchTmpConverter;
import com.egeo.components.finance.dto.AccountBatchTmpDTO;
import com.egeo.components.finance.manage.read.AccountBatchTmpReadManage;
import com.egeo.components.finance.po.AccountBatchTmpPO;
import com.egeo.components.finance.service.read.AccountBatchTmpReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("accountBatchTmpReadService")
public class AccountBatchTmpReadServiceImpl  implements AccountBatchTmpReadService {
	@Autowired
	private AccountBatchTmpReadManage accountBatchTmpReadManage;

	@Override
	public AccountBatchTmpDTO findAccountBatchTmpById(AccountBatchTmpDTO dto) {
		AccountBatchTmpPO po = AccountBatchTmpConverter.toPO(dto);
		AccountBatchTmpPO list = accountBatchTmpReadManage.findAccountBatchTmpById(po);		
		return AccountBatchTmpConverter.toDTO(list);
	}

	@Override
	public PageResult<AccountBatchTmpDTO> findAccountBatchTmpOfPage(AccountBatchTmpDTO dto, Pagination page) {
		AccountBatchTmpPO po = AccountBatchTmpConverter.toPO(dto);
        PageResult<AccountBatchTmpPO> pageResult = accountBatchTmpReadManage.findAccountBatchTmpOfPage(po, page);
        
        List<AccountBatchTmpDTO> list = AccountBatchTmpConverter.toDTO(pageResult.getList());
        PageResult<AccountBatchTmpDTO> result = new PageResult<AccountBatchTmpDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<AccountBatchTmpDTO> findAccountBatchTmpAll(AccountBatchTmpDTO dto) {
		AccountBatchTmpPO po = AccountBatchTmpConverter.toPO(dto);
		List<AccountBatchTmpPO> list = accountBatchTmpReadManage.findAccountBatchTmpAll(po);		
		return AccountBatchTmpConverter.toDTO(list);
	}

	@Override
	public AccountBatchTmpDTO queryAccountBatchTmpByFinBatch(String finBatch) {
		return AccountBatchTmpConverter.toDTO(accountBatchTmpReadManage.queryAccountBatchTmpByFinBatch(finBatch));
	}

	@Override
	public PageResult<AccountBatchTmpDTO> queryAccountBatchTmpPage(Pagination page, String keyWord, Long companyId,
			Integer status, Integer type,Long platformId) {
		PageResult<AccountBatchTmpPO> poPage=accountBatchTmpReadManage.queryAccountBatchTmpPage(page,keyWord,companyId,status,type,platformId);
		PageResult<AccountBatchTmpDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(AccountBatchTmpConverter.toDTO(poPage.getList()));
		return dtoPage;
	}
}
	