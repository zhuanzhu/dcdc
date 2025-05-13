package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.DepartmentImportRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DepartmentImportRecordManage {

	public DepartmentImportRecordDTO findDepartmentImportRecordById(DepartmentImportRecordDTO dto);	

	public PageResult<DepartmentImportRecordDTO> findDepartmentImportRecordOfPage(DepartmentImportRecordDTO dto,Pagination page);

	public List<DepartmentImportRecordDTO> findDepartmentImportRecordAll(DepartmentImportRecordDTO dto);

	Long insertDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto);

	int updateDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto);

	int deleteDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto);
}
	