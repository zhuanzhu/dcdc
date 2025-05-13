package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.DepartmentImportRecordDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface DepartmentImportRecordReadService {

	public DepartmentImportRecordDTO findDepartmentImportRecordById(DepartmentImportRecordDTO dto);

	public PageResult<DepartmentImportRecordDTO> findDepartmentImportRecordOfPage(DepartmentImportRecordDTO dto,Pagination page);

	public List<DepartmentImportRecordDTO> findDepartmentImportRecordAll(DepartmentImportRecordDTO dto);
}
	