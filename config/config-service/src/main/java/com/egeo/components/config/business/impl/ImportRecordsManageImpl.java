package com.egeo.components.config.business.impl;
	

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.config.business.ImportRecordsManage;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.config.facade.ImportRecordsFacade;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("importRecords")
public class ImportRecordsManageImpl implements ImportRecordsManage{

	
	@Resource(name="importRecordsFacade")
	private ImportRecordsFacade importRecordsFacade;

	@Override
	public ImportRecordsDTO findImportRecordsById(ImportRecordsDTO dto) {
		return importRecordsFacade.findImportRecordsById(dto);
	}

	@Override
	public PageResult<ImportRecordsDTO> findImportRecordsOfPage(ImportRecordsDTO dto, Pagination page) {
		return importRecordsFacade.findImportRecordsOfPage(dto, page);
	}

	@Override
	public List<ImportRecordsDTO> findImportRecordsAll(ImportRecordsDTO dto) {
		return importRecordsFacade.findImportRecordsAll(dto);
	}

	@Override
	public Long insertImportRecordsWithTx(ImportRecordsDTO dto) {
		return importRecordsFacade.insertImportRecordsWithTx(dto);
	}

	@Override
	public int updateImportRecordsWithTx(ImportRecordsDTO dto) {
		return importRecordsFacade.updateImportRecordsWithTx(dto);
	}

	@Override
	public int deleteImportRecordsWithTx(ImportRecordsDTO dto) {
		return importRecordsFacade.deleteImportRecordsWithTx(dto);
	}


}
	