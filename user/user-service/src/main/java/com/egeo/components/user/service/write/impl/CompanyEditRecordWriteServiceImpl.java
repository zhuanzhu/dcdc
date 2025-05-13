package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.CompanyEditRecordConverter;
import com.egeo.components.user.dto.CompanyEditRecordDTO;
import com.egeo.components.user.manage.write.CompanyEditRecordWriteManage;
import com.egeo.components.user.po.CompanyEditRecordPO;
import com.egeo.components.user.service.write.CompanyEditRecordWriteService;

@Service("companyEditRecordWriteService")
public class CompanyEditRecordWriteServiceImpl implements CompanyEditRecordWriteService {
	@Autowired
	private CompanyEditRecordWriteManage companyEditRecordWriteManage;

	@Override
	public Long insertCompanyEditRecordWithTx(CompanyEditRecordDTO dto) {
		CompanyEditRecordPO po = CompanyEditRecordConverter.toPO(dto);
		Long rt = companyEditRecordWriteManage.insertCompanyEditRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateCompanyEditRecordWithTx(CompanyEditRecordDTO dto) {
		CompanyEditRecordPO po = CompanyEditRecordConverter.toPO(dto);
		int rt = companyEditRecordWriteManage.updateCompanyEditRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCompanyEditRecordWithTx(CompanyEditRecordDTO dto) {
		CompanyEditRecordPO po = CompanyEditRecordConverter.toPO(dto);
		int rt = companyEditRecordWriteManage.deleteCompanyEditRecordWithTx(po);		
		return rt;
	}
}
	