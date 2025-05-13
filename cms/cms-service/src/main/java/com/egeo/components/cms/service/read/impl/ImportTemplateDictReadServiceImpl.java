package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.ImportTemplateDictReadService;
import com.egeo.components.cms.manage.read.ImportTemplateDictReadManage;
import com.egeo.components.cms.converter.ImportTemplateDictConverter;
import com.egeo.components.cms.dto.ImportTemplateDictDTO;
import com.egeo.components.cms.po.ImportTemplateDictPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("importTemplateDictReadService")
public class ImportTemplateDictReadServiceImpl  implements ImportTemplateDictReadService {
	@Autowired
	private ImportTemplateDictReadManage importTemplateDictReadManage;

	@Override
	public ImportTemplateDictDTO findImportTemplateDictById(ImportTemplateDictDTO dto) {
		ImportTemplateDictPO po = ImportTemplateDictConverter.toPO(dto);
		ImportTemplateDictPO list = importTemplateDictReadManage.findImportTemplateDictById(po);		
		return ImportTemplateDictConverter.toDTO(list);
	}

	@Override
	public PageResult<ImportTemplateDictDTO> findImportTemplateDictOfPage(ImportTemplateDictDTO dto, Pagination page) {
		ImportTemplateDictPO po = ImportTemplateDictConverter.toPO(dto);
        PageResult<ImportTemplateDictPO> pageResult = importTemplateDictReadManage.findImportTemplateDictOfPage(po, page);
        
        List<ImportTemplateDictDTO> list = ImportTemplateDictConverter.toDTO(pageResult.getList());
        PageResult<ImportTemplateDictDTO> result = new PageResult<ImportTemplateDictDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ImportTemplateDictDTO> findImportTemplateDictAll(ImportTemplateDictDTO dto) {
		ImportTemplateDictPO po = ImportTemplateDictConverter.toPO(dto);
		List<ImportTemplateDictPO> list = importTemplateDictReadManage.findImportTemplateDictAll(po);		
		return ImportTemplateDictConverter.toDTO(list);
	}
	/**
	 * 根据类型和平台id查询导入模版url
	 * @param type
	 * @return
	 */
	@Override
	public String findImportTemplateDictUrlByType(Integer type, Long platformId) {
		// TODO Auto-generated method stub
		return importTemplateDictReadManage.findImportTemplateDictUrlByType(type, platformId);
	}
}
	