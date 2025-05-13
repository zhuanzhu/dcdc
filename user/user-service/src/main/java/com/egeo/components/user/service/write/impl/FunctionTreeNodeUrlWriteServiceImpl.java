package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.FunctionTreeNodeUrlConverter;
import com.egeo.components.user.dto.FunctionTreeNodeUrlDTO;
import com.egeo.components.user.manage.write.FunctionTreeNodeUrlWriteManage;
import com.egeo.components.user.po.FunctionTreeNodeUrlPO;
import com.egeo.components.user.service.write.FunctionTreeNodeUrlWriteService;

@Service("functionTreeNodeUrlWriteService")
public class FunctionTreeNodeUrlWriteServiceImpl implements FunctionTreeNodeUrlWriteService {
	@Autowired
	private FunctionTreeNodeUrlWriteManage functionTreeNodeUrlWriteManage;

	@Override
	public Long insertFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto) {
		FunctionTreeNodeUrlPO po = FunctionTreeNodeUrlConverter.toPO(dto);
		Long rt = functionTreeNodeUrlWriteManage.insertFunctionTreeNodeUrlWithTx(po);		
		return rt;
	}

	@Override
	public int updateFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto) {
		FunctionTreeNodeUrlPO po = FunctionTreeNodeUrlConverter.toPO(dto);
		int rt = functionTreeNodeUrlWriteManage.updateFunctionTreeNodeUrlWithTx(po);		
		return rt;
	}

	@Override
	public int deleteFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto) {
		FunctionTreeNodeUrlPO po = FunctionTreeNodeUrlConverter.toPO(dto);
		int rt = functionTreeNodeUrlWriteManage.deleteFunctionTreeNodeUrlWithTx(po);		
		return rt;
	}

}
	