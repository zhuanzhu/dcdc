package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.FunctionTreeNodeUrlDTO;

import java.util.List;


public interface FunctionTreeNodeUrlWriteService {

	public Long insertFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto);

	public int updateFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto);

	public int deleteFunctionTreeNodeUrlWithTx(FunctionTreeNodeUrlDTO dto);

}
	