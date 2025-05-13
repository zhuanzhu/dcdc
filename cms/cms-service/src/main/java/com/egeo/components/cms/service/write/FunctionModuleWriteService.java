package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.FunctionModuleDTO;


public interface FunctionModuleWriteService {

	public Long insertFunctionModuleWithTx(FunctionModuleDTO dto);

	public int updateFunctionModuleWithTx(FunctionModuleDTO dto);

	public int deleteFunctionModuleWithTx(FunctionModuleDTO dto);
}
	