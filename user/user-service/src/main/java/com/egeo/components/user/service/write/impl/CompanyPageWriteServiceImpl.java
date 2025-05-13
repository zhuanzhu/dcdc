package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.CompanyPageConverter;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.manage.write.CompanyPageWriteManage;
import com.egeo.components.user.po.CompanyPagePO;
import com.egeo.components.user.service.write.CompanyPageWriteService;

@Service("companyPageWriteService")
public class CompanyPageWriteServiceImpl implements CompanyPageWriteService {
	@Autowired
	private CompanyPageWriteManage companyPageWriteManage;

	@Override
	public Long insertCompanyPageWithTx(CompanyPageDTO dto) {
		CompanyPagePO po = CompanyPageConverter.toPO(dto);
		Long rt = companyPageWriteManage.insertCompanyPageWithTx(po);		
		return rt;
	}

	@Override
	public int updateCompanyPageWithTx(CompanyPageDTO dto) {
		CompanyPagePO po = CompanyPageConverter.toPO(dto);
		int rt = companyPageWriteManage.updateCompanyPageWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCompanyPageWithTx(CompanyPageDTO dto) {
		CompanyPagePO po = CompanyPageConverter.toPO(dto);
		int rt = companyPageWriteManage.deleteCompanyPageWithTx(po);		
		return rt;
	}
}
	