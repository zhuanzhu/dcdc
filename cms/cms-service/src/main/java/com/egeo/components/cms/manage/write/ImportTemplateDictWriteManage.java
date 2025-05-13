package com.egeo.components.cms.manage.write;

import com.egeo.components.cms.po.ImportTemplateDictPO;


public interface ImportTemplateDictWriteManage {

	Long insertImportTemplateDictWithTx(ImportTemplateDictPO po);

	int updateImportTemplateDictWithTx(ImportTemplateDictPO po);

	int deleteImportTemplateDictWithTx(ImportTemplateDictPO po);
}
	