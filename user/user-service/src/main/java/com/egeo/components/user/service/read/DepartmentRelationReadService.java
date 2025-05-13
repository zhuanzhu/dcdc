package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.DepartmentRelationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface DepartmentRelationReadService {

	public DepartmentRelationDTO findDepartmentRelationById(DepartmentRelationDTO dto);

	public PageResult<DepartmentRelationDTO> findDepartmentRelationOfPage(DepartmentRelationDTO dto,Pagination page);

	public List<DepartmentRelationDTO> findDepartmentRelationAll(DepartmentRelationDTO dto);
}
	