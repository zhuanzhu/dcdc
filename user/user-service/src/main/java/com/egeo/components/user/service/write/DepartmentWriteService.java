package com.egeo.components.user.service.write;

import com.egeo.components.user.dto.DepartmentDTO;


public interface DepartmentWriteService {

	public Long insertDepartmentWithTx(DepartmentDTO dto);

	public int updateDepartmentWithTx(DepartmentDTO dto);

	public int deleteDepartmentWithTx(DepartmentDTO dto);

	public Long deleteByCompanyId(DepartmentDTO dtoRe);
}
	