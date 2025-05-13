package com.egeo.components.config.service.write;

import com.egeo.components.config.dto.ImportRecordsDTO;


public interface ImportRecordsWriteService {

	public Long insertImportRecordsWithTx(ImportRecordsDTO dto);

	public int updateImportRecordsWithTx(ImportRecordsDTO dto);

	public int deleteImportRecordsWithTx(ImportRecordsDTO dto);

	/**
	 * 根据sn删除草稿头文件
	 * @param sn
	 * @return
	 */
	public int deleteBySn(String sn);

}
	