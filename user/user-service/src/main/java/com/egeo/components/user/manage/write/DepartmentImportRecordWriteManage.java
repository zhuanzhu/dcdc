package com.egeo.components.user.manage.write;

import com.egeo.components.user.po.DepartmentImportRecordPO;


public interface DepartmentImportRecordWriteManage {

	Long insertDepartmentImportRecordWithTx(DepartmentImportRecordPO po);

	int updateDepartmentImportRecordWithTx(DepartmentImportRecordPO po);

	int deleteDepartmentImportRecordWithTx(DepartmentImportRecordPO po);
}
	