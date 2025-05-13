package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.DepartmentRelationConverter;
import com.egeo.components.user.dto.DepartmentRelationDTO;
import com.egeo.components.user.manage.write.DepartmentRelationWriteManage;
import com.egeo.components.user.po.DepartmentRelationPO;
import com.egeo.components.user.service.write.DepartmentRelationWriteService;

@Service("departmentRelationWriteService")
public class DepartmentRelationWriteServiceImpl implements DepartmentRelationWriteService {
	@Autowired
	private DepartmentRelationWriteManage departmentRelationWriteManage;

	@Override
	public Long insertDepartmentRelationWithTx(DepartmentRelationDTO dto) {
		DepartmentRelationPO po = DepartmentRelationConverter.toPO(dto);
		Long rt = departmentRelationWriteManage.insertDepartmentRelationWithTx(po);		
		return rt;
	}

	@Override
	public int updateDepartmentRelationWithTx(DepartmentRelationDTO dto) {
		DepartmentRelationPO po = DepartmentRelationConverter.toPO(dto);
		int rt = departmentRelationWriteManage.updateDepartmentRelationWithTx(po);		
		return rt;
	}

	@Override
	public int deleteDepartmentRelationWithTx(DepartmentRelationDTO dto) {
		DepartmentRelationPO po = DepartmentRelationConverter.toPO(dto);
		int rt = departmentRelationWriteManage.deleteDepartmentRelationWithTx(po);		
		return rt;
	}
	/**
	 * 根据部门id删除部门关系表
	 * @param departmentRelationDTO
	 * @return
	 */
	@Override
	public int deleteDepartmentRelationByDepartmentIdWithTx(DepartmentRelationDTO departmentRelationDTO) {
		
		return departmentRelationWriteManage.deleteDepartmentRelationByDepartmentIdWithTx(DepartmentRelationConverter.toPO(departmentRelationDTO));
	}
}
	