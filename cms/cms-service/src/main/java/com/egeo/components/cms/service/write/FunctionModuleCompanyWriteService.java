package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.FunctionModuleCompanyDTO;


public interface FunctionModuleCompanyWriteService {

	public Long insertFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto);

	public int updateFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto);

	public int deleteFunctionModuleCompanyWithTx(FunctionModuleCompanyDTO dto);
}
	