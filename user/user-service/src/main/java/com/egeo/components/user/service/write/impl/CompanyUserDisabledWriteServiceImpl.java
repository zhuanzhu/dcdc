package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.CompanyUserDisabledConverter;
import com.egeo.components.user.dto.CompanyUserDisabledDTO;
import com.egeo.components.user.manage.write.CompanyUserDisabledWriteManage;
import com.egeo.components.user.po.CompanyUserDisabledPO;
import com.egeo.components.user.service.write.CompanyUserDisabledWriteService;

@Service("companyUserDisabledWriteService")
public class CompanyUserDisabledWriteServiceImpl implements CompanyUserDisabledWriteService {
	@Autowired
	private CompanyUserDisabledWriteManage companyUserDisabledWriteManage;

	@Override
	public Long insertCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto) {
		CompanyUserDisabledPO po = CompanyUserDisabledConverter.toPO(dto);
		Long rt = companyUserDisabledWriteManage.insertCompanyUserDisabledWithTx(po);		
		return rt;
	}

	@Override
	public int updateCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto) {
		CompanyUserDisabledPO po = CompanyUserDisabledConverter.toPO(dto);
		int rt = companyUserDisabledWriteManage.updateCompanyUserDisabledWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCompanyUserDisabledWithTx(CompanyUserDisabledDTO dto) {
		CompanyUserDisabledPO po = CompanyUserDisabledConverter.toPO(dto);
		int rt = companyUserDisabledWriteManage.deleteCompanyUserDisabledWithTx(po);		
		return rt;
	}

	@Override
	public int updateRevalidationByCompanyId(Integer revalidation,Long companyId) {
		int rt = companyUserDisabledWriteManage.updateRevalidationByCompanyId(revalidation,companyId);
		return rt;
	}
}
	