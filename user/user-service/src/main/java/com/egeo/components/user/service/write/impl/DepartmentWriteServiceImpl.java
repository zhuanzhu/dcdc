package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.DepartmentConverter;
import com.egeo.components.user.dto.DepartmentDTO;
import com.egeo.components.user.manage.write.DepartmentWriteManage;
import com.egeo.components.user.po.DepartmentPO;
import com.egeo.components.user.service.write.DepartmentWriteService;

@Service("departmentWriteService")
public class DepartmentWriteServiceImpl implements DepartmentWriteService {
	@Autowired
	private DepartmentWriteManage departmentWriteManage;

	@Override
	public Long insertDepartmentWithTx(DepartmentDTO dto) {
		DepartmentPO po = DepartmentConverter.toPO(dto);
		Long rt = departmentWriteManage.insertDepartmentWithTx(po);		
		return rt;
	}

	@Override
	public int updateDepartmentWithTx(DepartmentDTO dto) {
		DepartmentPO po = DepartmentConverter.toPO(dto);
		int rt = departmentWriteManage.updateDepartmentWithTx(po);		
		return rt;
	}

	@Override
	public int deleteDepartmentWithTx(DepartmentDTO dto) {
		DepartmentPO po = DepartmentConverter.toPO(dto);
		int rt = departmentWriteManage.deleteDepartmentWithTx(po);		
		return rt;
	}

	@Override
	public Long deleteByCompanyId(DepartmentDTO dtoRe) {
		DepartmentPO po = DepartmentConverter.toPO(dtoRe);
		return departmentWriteManage.deleteByCompanyIdWithTx(po);
	}
}
	