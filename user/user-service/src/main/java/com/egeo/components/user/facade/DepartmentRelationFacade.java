package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.DepartmentRelationDTO;
import com.egeo.components.user.service.read.DepartmentRelationReadService;
import com.egeo.components.user.service.write.DepartmentRelationWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class DepartmentRelationFacade {
	
	@Resource
	private DepartmentRelationReadService departmentRelationReadService;
	
	@Resource
	private DepartmentRelationWriteService departmentRelationWriteService;
	
	
	public DepartmentRelationDTO findDepartmentRelationById(DepartmentRelationDTO dto){
		
		return departmentRelationReadService.findDepartmentRelationById(dto);
	}

	public PageResult<DepartmentRelationDTO> findDepartmentRelationOfPage(DepartmentRelationDTO dto,Pagination page){
		
		return departmentRelationReadService.findDepartmentRelationOfPage(dto, page);
		
	}

	public List<DepartmentRelationDTO> findDepartmentRelationAll(DepartmentRelationDTO dto){
		
		return departmentRelationReadService.findDepartmentRelationAll(dto);
		
	}

	public Long insertDepartmentRelationWithTx(DepartmentRelationDTO dto){
		
		return departmentRelationWriteService.insertDepartmentRelationWithTx(dto);
	}

	public int updateDepartmentRelationWithTx(DepartmentRelationDTO dto){
		
		return departmentRelationWriteService.updateDepartmentRelationWithTx(dto);
	}

	public int deleteDepartmentRelationWithTx(DepartmentRelationDTO dto){
		
		return departmentRelationWriteService.deleteDepartmentRelationWithTx(dto);
		
	}
	/**
	 * 根据部门id删除部门关系表
	 * @param departmentRelationDTO
	 * @return
	 */
	public int deleteDepartmentRelationByDepartmentIdWithTx(DepartmentRelationDTO departmentRelationDTO) {
		
		return departmentRelationWriteService.deleteDepartmentRelationByDepartmentIdWithTx(departmentRelationDTO);
	}

}
	