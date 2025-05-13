package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.FunctionModuleDTO;


public interface FunctionModuleWriteService {

	public Long insertFunctionModuleWithTx(FunctionModuleDTO dto);

	public int updateFunctionModuleWithTx(FunctionModuleDTO dto);

	public int deleteFunctionModuleWithTx(FunctionModuleDTO dto);
}
	