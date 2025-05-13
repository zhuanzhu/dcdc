package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.FunctionTreeNodeConverter;
import com.egeo.components.user.dto.FunctionTreeNodeDTO;
import com.egeo.components.user.manage.write.FunctionTreeNodeWriteManage;
import com.egeo.components.user.po.FunctionTreeNodePO;
import com.egeo.components.user.service.write.FunctionTreeNodeWriteService;

@Service("functionTreeNodeWriteService")
public class FunctionTreeNodeWriteServiceImpl implements FunctionTreeNodeWriteService {
	@Autowired
	private FunctionTreeNodeWriteManage functionTreeNodeWriteManage;

	@Override
	public Long insertFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto) {
		FunctionTreeNodePO po = FunctionTreeNodeConverter.toPO(dto);
		Long rt = functionTreeNodeWriteManage.insertFunctionTreeNodeWithTx(po);		
		return rt;
	}

	@Override
	public int updateFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto) {
		FunctionTreeNodePO po = FunctionTreeNodeConverter.toPO(dto);
		int rt = functionTreeNodeWriteManage.updateFunctionTreeNodeWithTx(po);		
		return rt;
	}

	@Override
	public int deleteFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto) {
		FunctionTreeNodePO po = FunctionTreeNodeConverter.toPO(dto);
		int rt = functionTreeNodeWriteManage.deleteFunctionTreeNodeWithTx(po);		
		return rt;
	}
}
	