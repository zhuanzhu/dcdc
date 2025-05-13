package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.CodeModuleConverter;
import com.egeo.components.user.dto.CodeModuleDTO;
import com.egeo.components.user.manage.write.CodeModuleWriteManage;
import com.egeo.components.user.po.CodeModulePO;
//com.egeo.components.user.service.write.CodeModuleWriteManage;
import com.egeo.components.user.service.write.CodeModuleWriteService;

@Service("codeModuleWriteService")
public class CodeModuleWriteServiceImpl implements CodeModuleWriteService {
	@Autowired
	private CodeModuleWriteManage codeModuleWriteManage;

	@Override
	public Long insertCodeModuleWithTx(CodeModuleDTO dto) {
		CodeModulePO po = CodeModuleConverter.toPO(dto);
		Long rt = codeModuleWriteManage.insertCodeModuleWithTx(po);		
		return rt;
	}

	@Override
	public int updateCodeModuleWithTx(CodeModuleDTO dto) {
		CodeModulePO po = CodeModuleConverter.toPO(dto);
		int rt = codeModuleWriteManage.updateCodeModuleWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCodeModuleWithTx(CodeModuleDTO dto) {
		CodeModulePO po = CodeModuleConverter.toPO(dto);
		int rt = codeModuleWriteManage.deleteCodeModuleWithTx(po);		
		return rt;
	}
}
	