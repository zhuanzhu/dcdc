package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.ImportTemplateDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ImportTemplateDictReadService {

	public ImportTemplateDictDTO findImportTemplateDictById(ImportTemplateDictDTO dto);

	public PageResult<ImportTemplateDictDTO> findImportTemplateDictOfPage(ImportTemplateDictDTO dto,Pagination page);

	public List<ImportTemplateDictDTO> findImportTemplateDictAll(ImportTemplateDictDTO dto);
	/**
	 * 根据类型和平台id查询导入模版url
	 * @param type
	 * @return
	 */
	public String findImportTemplateDictUrlByType(Integer type, Long platformId);
}
	