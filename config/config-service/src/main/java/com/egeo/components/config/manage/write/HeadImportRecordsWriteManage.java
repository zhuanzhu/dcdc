package com.egeo.components.config.manage.write;

import com.egeo.components.config.po.HeadImportRecordsPO;


public interface HeadImportRecordsWriteManage {

	Long insertHeadImportRecordsWithTx(HeadImportRecordsPO po);

	int updateHeadImportRecordsWithTx(HeadImportRecordsPO po);

	int deleteHeadImportRecordsWithTx(HeadImportRecordsPO po);
}
	