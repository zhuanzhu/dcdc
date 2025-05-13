package com.egeo.components.cms.business;

import java.util.List;
	
import com.egeo.components.cms.dto.ImportTemplateDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ImportTemplateDictManage {

	public ImportTemplateDictDTO findImportTemplateDictById(ImportTemplateDictDTO dto);	

	public PageResult<ImportTemplateDictDTO> findImportTemplateDictOfPage(ImportTemplateDictDTO dto,Pagination page);

	public List<ImportTemplateDictDTO> findImportTemplateDictAll(ImportTemplateDictDTO dto);

	Long insertImportTemplateDictWithTx(ImportTemplateDictDTO dto);

	int updateImportTemplateDictWithTx(ImportTemplateDictDTO dto);

	int deleteImportTemplateDictWithTx(ImportTemplateDictDTO dto);
	/**
	 * 根据类型和平台id查询导入模版url
	 * @param type
	 * @param platformId
	 * @return
	 */
	public String findImportTemplateDictUrlByType(Integer type, Long platformId);
}
	