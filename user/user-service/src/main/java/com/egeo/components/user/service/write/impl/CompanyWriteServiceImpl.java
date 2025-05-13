package com.egeo.components.user.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.CompanyConverter;
import com.egeo.components.user.converter.CompanyPageConverter;
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.CompanyPageDTO;
import com.egeo.components.user.manage.write.CompanyWriteManage;
import com.egeo.components.user.po.CompanyPO;
import com.egeo.components.user.service.write.CompanyWriteService;


@Service("companyWriteService")
public class CompanyWriteServiceImpl implements CompanyWriteService {
	@Autowired
	private CompanyWriteManage companyWriteManage;
	

	@Override
	public Long insertCompanyWithTx(CompanyDTO dto,List<CompanyPageDTO> companyPageList) {
		CompanyPO po = CompanyConverter.toPO(dto);
		//插入公司记录
		Long rt = companyWriteManage.insertCompanyWithTx(po,CompanyPageConverter.toPO(companyPageList));
		

		
		return rt;
	}

	@Override
	public int updateCompanyWithTx(CompanyDTO dto,List<CompanyPageDTO> companyPageList) {
		CompanyPO po = CompanyConverter.toPO(dto);
		int rt = companyWriteManage.updateCompanyWithTx(po,CompanyPageConverter.toPO(companyPageList));		
		return rt;
	}

	@Override
	public int deleteCompanyWithTx(CompanyDTO dto) {
		CompanyPO po = CompanyConverter.toPO(dto);
		int rt = companyWriteManage.deleteCompanyWithTx(po);		
		return rt;
	}
	@Override
	public void updateCompanyParamWithTX(CompanyDTO dto) {
		companyWriteManage.updateCompanyParamWithTx(CompanyConverter.toPO(dto));
	}
}
	