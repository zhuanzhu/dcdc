package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.FunctionTreeNodeDTO;


public interface FunctionTreeNodeWriteService {

	public Long insertFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto);

	public int updateFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto);

	public int deleteFunctionTreeNodeWithTx(FunctionTreeNodeDTO dto);
}
	