package com.egeo.components.finance.service.read.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.AccountFlowConverter;
import com.egeo.components.finance.dto.AccountFlowDTO;
import com.egeo.components.finance.manage.read.AccountFlowReadManage;
import com.egeo.components.finance.po.AccountFlowPO;
import com.egeo.components.finance.service.read.AccountFlowReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("accountFlowReadService")
public class AccountFlowReadServiceImpl  implements AccountFlowReadService {
	@Autowired
	private AccountFlowReadManage accountFlowReadManage;

	@Override
	public AccountFlowDTO findAccountFlowById(AccountFlowDTO dto) {
		AccountFlowPO po = AccountFlowConverter.toPO(dto);
		AccountFlowPO list = accountFlowReadManage.findAccountFlowById(po);		
		return AccountFlowConverter.toDTO(list);
	}

	@Override
	public PageResult<AccountFlowDTO> findAccountFlowOfPage(AccountFlowDTO dto, Pagination page) {
		AccountFlowPO po = AccountFlowConverter.toPO(dto);
        PageResult<AccountFlowPO> pageResult = accountFlowReadManage.findAccountFlowOfPage(po, page);
        
        List<AccountFlowDTO> list = AccountFlowConverter.toDTO(pageResult.getList());
        PageResult<AccountFlowDTO> result = new PageResult<AccountFlowDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<AccountFlowDTO> findAccountFlowAll(AccountFlowDTO dto) {
		AccountFlowPO po = AccountFlowConverter.toPO(dto);
		List<AccountFlowPO> list = accountFlowReadManage.findAccountFlowAll(po);		
		return AccountFlowConverter.toDTO(list);
	}

	@Override
	public PageResult<AccountFlowDTO> queryAccountFlowPage(Long batchId, String outflowAccount,
			String inflowAccount, Long startTime, Long endTime, Long platformId, Pagination page) {
		PageResult<AccountFlowPO> poPage=accountFlowReadManage.queryAccountFlowPage(batchId,outflowAccount,inflowAccount,startTime,endTime, platformId, page);
		PageResult<AccountFlowDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(AccountFlowConverter.toDTO(poPage.getList()));
		return dtoPage;
	}

	@Override
	public PageResult<AccountFlowDTO> queryAccountFlowPageByAccountId(Pagination page, Long accountId, Integer mode) {
		PageResult<AccountFlowPO> poPage=accountFlowReadManage.queryAccountFlowPageByAccountId(page,accountId,mode);
		PageResult<AccountFlowDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(AccountFlowConverter.toDTO(poPage.getList()));
		return dtoPage;
	}

	@Override
	public List<AccountFlowDTO> queryOrderRefundFlow(Long orderId) {
		return AccountFlowConverter.toDTO(accountFlowReadManage.queryOrderRefundFlow(orderId));
	}

	@Override
	public PageResult<AccountFlowDTO> userFinFlowPage(List<Long> accountIdList, Pagination page, Date startTime, Date endTime) {
		PageResult<AccountFlowPO> poPage=accountFlowReadManage.userFinFlowPage(accountIdList,page,startTime,endTime);
		PageResult<AccountFlowDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(AccountFlowConverter.toDTO(poPage.getList()));
		return dtoPage;
	}

	@Override
	public List<AccountFlowDTO> findAccountFlowOfEnterprise(Long enterpriseId, Date start, Date end) {
		// TODO Auto-generated method stub
		List<AccountFlowPO> poPage=accountFlowReadManage.userFinFlowEnterpise(enterpriseId,start,end);
		return AccountFlowConverter.toDTO(poPage);
	}
	@Override
	public List<AccountFlowDTO> findAccountFlowOfCompany(Long companyId, Date start, Date end) {
		// TODO Auto-generated method stub
		List<AccountFlowPO> poPage=accountFlowReadManage.userFinFlowCompany(companyId,start,end);
		return AccountFlowConverter.toDTO(poPage);
	}
}
	