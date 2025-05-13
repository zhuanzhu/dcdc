package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.FunctionModuleCompanyWriteService;
import com.egeo.components.cms.manage.write.FunctionModuleCompanyWriteManage;
import com.egeo.components.cms.converter.FunctionModuleCompanyConverter;
import com.egeo.components.cms.dto.FunctionModuleCompanyDTO;
import com.egeo.components.cms.po.FunctionModuleCompanyPO;

@Service("functionModuleCompanyWriteService")
public class FunctionModuleCompanyWriteServiceImpl  implements FunctionModuleCompanyWriteService {
	@Autowired
	private FunctionModuleCompanyWriteManage functionModuleCompanyWriteManage;

	@Override
	public Long insertFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto) {
		FunctionModuleCompanyPO po = FunctionModuleCompanyConverter.toPO(dto);
		Long rt = functionModuleCompanyWriteManage.insertFunctionModuleCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto) {
		FunctionModuleCompanyPO po = FunctionModuleCompanyConverter.toPO(dto);
		int rt = functionModuleCompanyWriteManage.updateFunctionModuleCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto) {
		FunctionModuleCompanyPO po = FunctionModuleCompanyConverter.toPO(dto);
		int rt = functionModuleCompanyWriteManage.deleteFunctionModuleCompanyWithTx(po);		
		return rt;
	}
}
	