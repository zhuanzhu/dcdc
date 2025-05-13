package com.egeo.components.config.service.read;


import java.util.List;

import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ImportRecordsReadService {

	public ImportRecordsDTO findImportRecordsById(ImportRecordsDTO dto);

	public PageResult<ImportRecordsDTO> findImportRecordsOfPage(ImportRecordsDTO dto,Pagination page);

	public List<ImportRecordsDTO> findImportRecordsAll(ImportRecordsDTO dto);

	/**
	 * 查询头信息草稿
	 * @param sn
	 * @return
	 */
	public ImportRecordsDTO queryRecordTempBySn(String sn);

}
	