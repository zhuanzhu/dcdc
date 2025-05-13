package com.egeo.components.config.business;

import java.util.List;

import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ImportRecordsManage {

	public ImportRecordsDTO findImportRecordsById(ImportRecordsDTO dto);	

	public PageResult<ImportRecordsDTO> findImportRecordsOfPage(ImportRecordsDTO dto,Pagination page);

	public List<ImportRecordsDTO> findImportRecordsAll(ImportRecordsDTO dto);

	Long insertImportRecordsWithTx(ImportRecordsDTO dto);

	int updateImportRecordsWithTx(ImportRecordsDTO dto);

	int deleteImportRecordsWithTx(ImportRecordsDTO dto);
}
	