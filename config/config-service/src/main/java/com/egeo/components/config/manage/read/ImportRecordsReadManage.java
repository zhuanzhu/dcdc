package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.po.ImportRecordsPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ImportRecordsReadManage {

	public ImportRecordsPO findImportRecordsById(ImportRecordsPO po);

	public PageResult<ImportRecordsPO> findImportRecordsOfPage(ImportRecordsPO po,Pagination page);

	public List<ImportRecordsPO> findImportRecordsAll(ImportRecordsPO po);

	/**
	 * 查询头信息草稿
	 * @param sn
	 * @return
	 */
	public ImportRecordsPO queryRecordTempBySn(String sn);


}
	