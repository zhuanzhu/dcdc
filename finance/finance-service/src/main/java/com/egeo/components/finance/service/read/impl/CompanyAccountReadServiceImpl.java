package com.egeo.components.finance.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.CompanyAccountConverter;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.manage.read.CompanyAccountReadManage;
import com.egeo.components.finance.po.CompanyAccountPO;
import com.egeo.components.finance.service.read.CompanyAccountReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("companyAccountReadService")
public class CompanyAccountReadServiceImpl implements CompanyAccountReadService {
	@Autowired
	private CompanyAccountReadManage companyAccountReadManage;

	@Override
	public CompanyAccountDTO findCompanyAccountById(CompanyAccountDTO dto) {
		CompanyAccountPO po = CompanyAccountConverter.toPO(dto);
		CompanyAccountPO list = companyAccountReadManage.findCompanyAccountById(po);		
		return CompanyAccountConverter.toDTO(list);
	}

	@Override
	public PageResult<CompanyAccountDTO> findCompanyAccountOfPage(CompanyAccountDTO dto, Pagination page) {
		CompanyAccountPO po = CompanyAccountConverter.toPO(dto);
        PageResult<CompanyAccountPO> pageResult = companyAccountReadManage.findCompanyAccountOfPage(po, page);
        
        List<CompanyAccountDTO> list = CompanyAccountConverter.toDTO(pageResult.getList());
        PageResult<CompanyAccountDTO> result = new PageResult<CompanyAccountDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CompanyAccountDTO> findCompanyAccountAll(CompanyAccountDTO dto) {
		CompanyAccountPO po = CompanyAccountConverter.toPO(dto);
		List<CompanyAccountPO> list = companyAccountReadManage.findCompanyAccountAll(po);		
		return CompanyAccountConverter.toDTO(list);
	}

	@Override
	public PageResult<CompanyAccountDTO> queryCompanyAccountPage(Pagination page, String accountName, List<Long> companyId,
			Integer disabled,Long platformId) {
		PageResult<CompanyAccountPO> poPage=companyAccountReadManage.queryCompanyAccountPage(page,accountName,companyId,disabled,platformId);
		PageResult<CompanyAccountDTO> dtoPage=new PageResult<>();
		dtoPage.copy(poPage);
		dtoPage.setList(CompanyAccountConverter.toDTO(poPage.getList()));
		return dtoPage;
	}

	@Override
	public List<CompanyAccountDTO> queryNormalAccounts(Long platformId,List<Long> companyId) {
		return CompanyAccountConverter.toDTO(companyAccountReadManage.queryNormalAccounts(platformId,companyId));
	}

	@Override
	public CompanyAccountDTO querySpecialCompanyAccountByType(Long platformId, int type) {
		return CompanyAccountConverter.toDTO(companyAccountReadManage.querySpecialCompanyAccountByType(platformId,type));
	}

	@Override
	public List<CompanyAccountDTO> queryCompanyAccountsByIds(List<Long> accountIds) {
		return CompanyAccountConverter.toDTO(companyAccountReadManage.queryCompanyAccountsByIds(accountIds));
	}

	@Override
	public CompanyAccountDTO queryNormalCompanyAccountByCompnayId(Long companyId) {
		return CompanyAccountConverter.toDTO(companyAccountReadManage.queryNormalCompanyAccountByCompnayId(companyId));
	}
}
	