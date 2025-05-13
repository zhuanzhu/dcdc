package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.FunctionModuleWriteService;
import com.egeo.components.cms.manage.write.FunctionModuleWriteManage;
import com.egeo.components.cms.converter.FunctionModuleConverter;
import com.egeo.components.cms.dto.FunctionModuleDTO;
import com.egeo.components.cms.po.FunctionModulePO;

@Service("functionModuleWriteService")
public class FunctionModuleWriteServiceImpl  implements FunctionModuleWriteService {
	@Autowired
	private FunctionModuleWriteManage functionModuleWriteManage;

	@Override
	public Long insertFunctionModuleWithTx(FunctionModuleDTO dto) {
		FunctionModulePO po = FunctionModuleConverter.toPO(dto);
		Long rt = functionModuleWriteManage.insertFunctionModuleWithTx(po);		
		return rt;
	}

	@Override
	public int updateFunctionModuleWithTx(FunctionModuleDTO dto) {
		FunctionModulePO po = FunctionModuleConverter.toPO(dto);
		int rt = functionModuleWriteManage.updateFunctionModuleWithTx(po);		
		return rt;
	}

	@Override
	public int deleteFunctionModuleWithTx(FunctionModuleDTO dto) {
		FunctionModulePO po = FunctionModuleConverter.toPO(dto);
		int rt = functionModuleWriteManage.deleteFunctionModuleWithTx(po);		
		return rt;
	}
}
	