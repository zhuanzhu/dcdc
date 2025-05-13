package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.ImportTemplateDictWriteService;
import com.egeo.components.cms.manage.write.ImportTemplateDictWriteManage;
import com.egeo.components.cms.converter.ImportTemplateDictConverter;
import com.egeo.components.cms.dto.ImportTemplateDictDTO;
import com.egeo.components.cms.po.ImportTemplateDictPO;

@Service("importTemplateDictWriteService")
public class ImportTemplateDictWriteServiceImpl  implements ImportTemplateDictWriteService {
	@Autowired
	private ImportTemplateDictWriteManage importTemplateDictWriteManage;

	@Override
	public Long insertImportTemplateDictWithTx(ImportTemplateDictDTO dto) {
		ImportTemplateDictPO po = ImportTemplateDictConverter.toPO(dto);
		Long rt = importTemplateDictWriteManage.insertImportTemplateDictWithTx(po);		
		return rt;
	}

	@Override
	public int updateImportTemplateDictWithTx(ImportTemplateDictDTO dto) {
		ImportTemplateDictPO po = ImportTemplateDictConverter.toPO(dto);
		int rt = importTemplateDictWriteManage.updateImportTemplateDictWithTx(po);		
		return rt;
	}

	@Override
	public int deleteImportTemplateDictWithTx(ImportTemplateDictDTO dto) {
		ImportTemplateDictPO po = ImportTemplateDictConverter.toPO(dto);
		int rt = importTemplateDictWriteManage.deleteImportTemplateDictWithTx(po);		
		return rt;
	}
}
	