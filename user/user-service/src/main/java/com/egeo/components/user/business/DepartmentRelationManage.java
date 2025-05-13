package com.egeo.components.user.business;

import java.util.List;
	
import com.egeo.components.user.dto.DepartmentRelationDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DepartmentRelationManage {

	public DepartmentRelationDTO findDepartmentRelationById(DepartmentRelationDTO dto);	

	public PageResult<DepartmentRelationDTO> findDepartmentRelationOfPage(DepartmentRelationDTO dto,Pagination page);

	public List<DepartmentRelationDTO> findDepartmentRelationAll(DepartmentRelationDTO dto);

	Long insertDepartmentRelationWithTx(DepartmentRelationDTO dto);

	int updateDepartmentRelationWithTx(DepartmentRelationDTO dto);

	int deleteDepartmentRelationWithTx(DepartmentRelationDTO dto);
}
	