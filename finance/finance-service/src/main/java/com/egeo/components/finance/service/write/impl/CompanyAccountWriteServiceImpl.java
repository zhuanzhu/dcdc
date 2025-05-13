package com.egeo.components.finance.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.CompanyAccountConverter;
import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.manage.write.CompanyAccountWriteManage;
import com.egeo.components.finance.po.CompanyAccountPO;
import com.egeo.components.finance.service.write.CompanyAccountWriteService;

@Service("companyAccountWriteService")
public class CompanyAccountWriteServiceImpl  implements CompanyAccountWriteService {
	@Autowired
	private CompanyAccountWriteManage companyAccountWriteManage;

	@Override
	public Long insertCompanyAccountWithTx(CompanyAccountDTO dto) {
		CompanyAccountPO po = CompanyAccountConverter.toPO(dto);
		Long rt = companyAccountWriteManage.insertCompanyAccountWithTx(po);		
		return rt;
	}

	@Override
	public int updateCompanyAccountWithTx(CompanyAccountDTO dto) {
		CompanyAccountPO po = CompanyAccountConverter.toPO(dto);
		int rt = companyAccountWriteManage.updateCompanyAccountWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCompanyAccountWithTx(CompanyAccountDTO dto) {
		CompanyAccountPO po = CompanyAccountConverter.toPO(dto);
		int rt = companyAccountWriteManage.deleteCompanyAccountWithTx(po);		
		return rt;
	}

	@Override
	public int updateAccountDisable(Long id, Integer disabled) {
		// TODO Auto-generated method stub
		return companyAccountWriteManage.updateAccountDisable(id,disabled);
	}
}
	