package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.CompanyCallCenterConverter;
import com.egeo.components.user.dto.CompanyCallCenterDTO;
import com.egeo.components.user.manage.write.CompanyCallCenterWriteManage;
import com.egeo.components.user.po.CompanyCallCenterPO;
import com.egeo.components.user.service.write.CompanyCallCenterWriteService;

@Service("companyCallCenterWriteService")
public class CompanyCallCenterWriteServiceImpl implements CompanyCallCenterWriteService {
	@Autowired
	private CompanyCallCenterWriteManage companyCallCenterWriteManage;

	@Override
	public Long insertCompanyCallCenterWithTx(CompanyCallCenterDTO dto) {
		CompanyCallCenterPO po = CompanyCallCenterConverter.toPO(dto);
		Long rt = companyCallCenterWriteManage.insertCompanyCallCenterWithTx(po);		
		return rt;
	}

	@Override
	public int updateCompanyCallCenterWithTx(CompanyCallCenterDTO dto) {
		CompanyCallCenterPO po = CompanyCallCenterConverter.toPO(dto);
		int rt = companyCallCenterWriteManage.updateCompanyCallCenterWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCompanyCallCenterWithTx(CompanyCallCenterDTO dto) {
		CompanyCallCenterPO po = CompanyCallCenterConverter.toPO(dto);
		int rt = companyCallCenterWriteManage.deleteCompanyCallCenterWithTx(po);		
		return rt;
	}
}
	