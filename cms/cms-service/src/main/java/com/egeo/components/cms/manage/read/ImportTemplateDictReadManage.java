package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.ImportTemplateDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ImportTemplateDictReadManage {

	public ImportTemplateDictPO findImportTemplateDictById(ImportTemplateDictPO po);

	public PageResult<ImportTemplateDictPO> findImportTemplateDictOfPage(ImportTemplateDictPO po,Pagination page);

	public List<ImportTemplateDictPO> findImportTemplateDictAll(ImportTemplateDictPO po);
	/**
	 * 根据类型和平台id查询导入模版url
	 * @param type
	 * @return
	 */
	public String findImportTemplateDictUrlByType(Integer type, Long platformId);
}
	