package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.ImportRecordsPO;


public interface ImportRecordsWriteManage {

	Long insertImportRecordsWithTx(ImportRecordsPO po);

	int updateImportRecordsWithTx(ImportRecordsPO po);

	int deleteImportRecordsWithTx(ImportRecordsPO po);

	/**
	 * 根据sn删除头文件草稿
	 * @param sn
	 * @return
	 */
	int deleteBySnWithTx(String sn);

}
	