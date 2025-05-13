package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.ImportTemplateDictDTO;


public interface ImportTemplateDictWriteService {

	public Long insertImportTemplateDictWithTx(ImportTemplateDictDTO dto);

	public int updateImportTemplateDictWithTx(ImportTemplateDictDTO dto);

	public int deleteImportTemplateDictWithTx(ImportTemplateDictDTO dto);
}
	