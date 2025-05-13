package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.DepartmentImportRecordConverter;
import com.egeo.components.user.dto.DepartmentImportRecordDTO;
import com.egeo.components.user.manage.write.DepartmentImportRecordWriteManage;
import com.egeo.components.user.po.DepartmentImportRecordPO;
import com.egeo.components.user.service.write.DepartmentImportRecordWriteService;

@Service("departmentImportRecordWriteService")
public class DepartmentImportRecordWriteServiceImpl implements DepartmentImportRecordWriteService {
	@Autowired
	private DepartmentImportRecordWriteManage departmentImportRecordWriteManage;

	@Override
	public Long insertDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto) {
		DepartmentImportRecordPO po = DepartmentImportRecordConverter.toPO(dto);
		Long rt = departmentImportRecordWriteManage.insertDepartmentImportRecordWithTx(po);		
		return rt;
	}

	@Override
	public int updateDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto) {
		DepartmentImportRecordPO po = DepartmentImportRecordConverter.toPO(dto);
		int rt = departmentImportRecordWriteManage.updateDepartmentImportRecordWithTx(po);		
		return rt;
	}

	@Override
	public int deleteDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto) {
		DepartmentImportRecordPO po = DepartmentImportRecordConverter.toPO(dto);
		int rt = departmentImportRecordWriteManage.deleteDepartmentImportRecordWithTx(po);		
		return rt;
	}
}
	