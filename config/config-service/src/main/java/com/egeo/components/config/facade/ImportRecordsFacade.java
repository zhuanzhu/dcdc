package com.egeo.components.config.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.config.service.read.ImportRecordsReadService;
import com.egeo.components.config.service.write.ImportRecordsWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ImportRecordsFacade {
	
	@Autowired
	private ImportRecordsReadService importRecordsReadService;
	
	@Autowired
	private ImportRecordsWriteService importRecordsWriteService;
	
	
	public ImportRecordsDTO findImportRecordsById(ImportRecordsDTO dto){
		
		return importRecordsReadService.findImportRecordsById(dto);
	}

	public PageResult<ImportRecordsDTO> findImportRecordsOfPage(ImportRecordsDTO dto,Pagination page){
		
		return importRecordsReadService.findImportRecordsOfPage(dto, page);
		
	}

	public List<ImportRecordsDTO> findImportRecordsAll(ImportRecordsDTO dto){
		
		return importRecordsReadService.findImportRecordsAll(dto);
		
	}

	public Long insertImportRecordsWithTx(ImportRecordsDTO dto){
		
		return importRecordsWriteService.insertImportRecordsWithTx(dto);
	}

	public int updateImportRecordsWithTx(ImportRecordsDTO dto){
		
		return importRecordsWriteService.updateImportRecordsWithTx(dto);
	}

	public int deleteImportRecordsWithTx(ImportRecordsDTO dto){
		
		return importRecordsWriteService.deleteImportRecordsWithTx(dto);
		
	}

}
	