package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.DepartmentTempDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface DepartmentTempReadService {

	public DepartmentTempDTO findDepartmentTempById(DepartmentTempDTO dto);

	public PageResult<DepartmentTempDTO> findDepartmentTempOfPage(DepartmentTempDTO dto,Pagination page);

	public List<DepartmentTempDTO> findDepartmentTempAll(DepartmentTempDTO dto);

	public List<DepartmentTempDTO> findDepartmentTempAllByIdsArr(DepartmentTempDTO dto, Long[] departmentTempIdsArr);
}
	