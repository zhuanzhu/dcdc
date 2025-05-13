package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.DepartmentImportRecordDTO;
import com.egeo.components.user.service.read.DepartmentImportRecordReadService;
import com.egeo.components.user.service.write.DepartmentImportRecordWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class DepartmentImportRecordFacade {
	
	@Resource
	private DepartmentImportRecordReadService departmentImportRecordReadService;
	
	@Resource
	private DepartmentImportRecordWriteService departmentImportRecordWriteService;
	
	
	public DepartmentImportRecordDTO findDepartmentImportRecordById(DepartmentImportRecordDTO dto){
		
		return departmentImportRecordReadService.findDepartmentImportRecordById(dto);
	}

	public PageResult<DepartmentImportRecordDTO> findDepartmentImportRecordOfPage(DepartmentImportRecordDTO dto,Pagination page){
		
		return departmentImportRecordReadService.findDepartmentImportRecordOfPage(dto, page);
		
	}

	public List<DepartmentImportRecordDTO> findDepartmentImportRecordAll(DepartmentImportRecordDTO dto){
		
		return departmentImportRecordReadService.findDepartmentImportRecordAll(dto);
		
	}

	public Long insertDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto){
		
		return departmentImportRecordWriteService.insertDepartmentImportRecordWithTx(dto);
	}

	public int updateDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto){
		
		return departmentImportRecordWriteService.updateDepartmentImportRecordWithTx(dto);
	}

	public int deleteDepartmentImportRecordWithTx(DepartmentImportRecordDTO dto){
		
		return departmentImportRecordWriteService.deleteDepartmentImportRecordWithTx(dto);
		
	}

}
	