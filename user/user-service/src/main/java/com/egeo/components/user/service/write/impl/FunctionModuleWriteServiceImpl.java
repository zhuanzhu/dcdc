package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.FunctionModuleConverter;
import com.egeo.components.user.dto.FunctionModuleDTO;
import com.egeo.components.user.manage.write.FunctionModuleWriteManage;
import com.egeo.components.user.po.FunctionModulePO;
import com.egeo.components.user.service.write.FunctionModuleWriteService;

@Service("functionModuleWriteService")
public class FunctionModuleWriteServiceImpl implements FunctionModuleWriteService {
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
	