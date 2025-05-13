package com.egeo.components.cms.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.cms.service.read.ImportTemplateDictReadService;
import com.egeo.components.cms.service.write.ImportTemplateDictWriteService;
import com.egeo.components.cms.dto.ImportTemplateDictDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ImportTemplateDictFacade {
	
	@Resource
	private ImportTemplateDictReadService importTemplateDictReadService;
	
	@Resource
	private ImportTemplateDictWriteService importTemplateDictWriteService;
	
	
	public ImportTemplateDictDTO findImportTemplateDictById(ImportTemplateDictDTO dto){
		
		return importTemplateDictReadService.findImportTemplateDictById(dto);
	}

	public PageResult<ImportTemplateDictDTO> findImportTemplateDictOfPage(ImportTemplateDictDTO dto,Pagination page){
		
		return importTemplateDictReadService.findImportTemplateDictOfPage(dto, page);
		
	}

	public List<ImportTemplateDictDTO> findImportTemplateDictAll(ImportTemplateDictDTO dto){
		
		return importTemplateDictReadService.findImportTemplateDictAll(dto);
		
	}

	public Long insertImportTemplateDictWithTx(ImportTemplateDictDTO dto){
		
		return importTemplateDictWriteService.insertImportTemplateDictWithTx(dto);
	}

	public int updateImportTemplateDictWithTx(ImportTemplateDictDTO dto){
		
		return importTemplateDictWriteService.updateImportTemplateDictWithTx(dto);
	}

	public int deleteImportTemplateDictWithTx(ImportTemplateDictDTO dto){
		
		return importTemplateDictWriteService.deleteImportTemplateDictWithTx(dto);
		
	}
	/**
	 * 根据类型和平台id查询导入模版url
	 * @param type
	 * @return
	 */
	public String findImportTemplateDictUrlByType(Integer type, Long platformId) {
		// TODO Auto-generated method stub
		return importTemplateDictReadService.findImportTemplateDictUrlByType(type, platformId);
	}

}
	