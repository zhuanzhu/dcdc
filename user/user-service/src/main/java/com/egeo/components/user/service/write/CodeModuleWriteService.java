package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.CodeModuleDTO;


public interface CodeModuleWriteService {

	public Long insertCodeModuleWithTx(CodeModuleDTO dto);

	public int updateCodeModuleWithTx(CodeModuleDTO dto);

	public int deleteCodeModuleWithTx(CodeModuleDTO dto);
}
	