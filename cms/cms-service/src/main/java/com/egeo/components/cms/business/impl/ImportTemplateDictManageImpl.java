package com.egeo.components.cms.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.business.ImportTemplateDictManage;
import com.egeo.components.cms.dto.ImportTemplateDictDTO;
import com.egeo.components.cms.facade.ImportTemplateDictFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("importTemplateDict")
public class ImportTemplateDictManageImpl implements ImportTemplateDictManage{
	
	@Value("${qiniu.picture}")
	private String qiniuPicture;
	
	@Resource(name="importTemplateDictFacade")
	private ImportTemplateDictFacade importTemplateDictFacade;

	@Override
	public ImportTemplateDictDTO findImportTemplateDictById(ImportTemplateDictDTO dto) {
		return importTemplateDictFacade.findImportTemplateDictById(dto);
	}

	@Override
	public PageResult<ImportTemplateDictDTO> findImportTemplateDictOfPage(ImportTemplateDictDTO dto, Pagination page) {
		return importTemplateDictFacade.findImportTemplateDictOfPage(dto, page);
	}

	@Override
	public List<ImportTemplateDictDTO> findImportTemplateDictAll(ImportTemplateDictDTO dto) {
		return importTemplateDictFacade.findImportTemplateDictAll(dto);
	}

	@Override
	public Long insertImportTemplateDictWithTx(ImportTemplateDictDTO dto) {
		return importTemplateDictFacade.insertImportTemplateDictWithTx(dto);
	}

	@Override
	public int updateImportTemplateDictWithTx(ImportTemplateDictDTO dto) {
		return importTemplateDictFacade.updateImportTemplateDictWithTx(dto);
	}

	@Override
	public int deleteImportTemplateDictWithTx(ImportTemplateDictDTO dto) {
		return importTemplateDictFacade.deleteImportTemplateDictWithTx(dto);
	}
	/**
	 * 根据类型和平台id查询导入模版url
	 * @param type
	 * @return
	 */
	@Override
	public String findImportTemplateDictUrlByType(Integer type, Long platformId) {
		String url = importTemplateDictFacade.findImportTemplateDictUrlByType(type, platformId);
		return qiniuPicture + "/" + url;
	}


}
	