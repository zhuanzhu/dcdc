package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.DepartmentImportRecordDTO;


public interface DepartmentImportRecordWriteService {

	public Long insertDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto);

	public int updateDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto);

	public int deleteDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto);
}
	