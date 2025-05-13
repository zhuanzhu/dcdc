package com.egeo.components.user.facade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.user.dto.DepartmentTempDTO;
import com.egeo.components.user.dto.DepartmentTreeDTO;
import com.egeo.components.user.service.read.DepartmentTempReadService;
import com.egeo.components.user.service.write.DepartmentTempWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class DepartmentTempFacade {
	
	@Resource
	private DepartmentTempReadService departmentTempReadService;
	
	@Resource
	private DepartmentTempWriteService departmentTempWriteService;
	
	@Autowired
	private ImportRecordsClient importRecordsReadService;
	
	@Autowired
	private ImportRecordsClient importRecordsWriteService;
	
	
	
	public DepartmentTempDTO findDepartmentTempById(DepartmentTempDTO dto){
		
		return departmentTempReadService.findDepartmentTempById(dto);
	}

	public PageResult<DepartmentTempDTO> findDepartmentTempOfPage(DepartmentTempDTO dto,Pagination page){
		
		return departmentTempReadService.findDepartmentTempOfPage(dto, page);
		
	}

	public List<DepartmentTempDTO> findDepartmentTempAll(DepartmentTempDTO dto){
		
		return departmentTempReadService.findDepartmentTempAll(dto);
		
	}

	public Long insertDepartmentTempWithTx(DepartmentTempDTO dto){
		
		return departmentTempWriteService.insertDepartmentTempWithTx(dto);
	}

	public int updateDepartmentTempWithTx(DepartmentTempDTO dto){
		
		return departmentTempWriteService.updateDepartmentTempWithTx(dto);
	}

	public int deleteDepartmentTempWithTx(DepartmentTempDTO dto){
		
		return departmentTempWriteService.deleteDepartmentTempWithTx(dto);
		
	}

	/**
	 * 根据临时部门的id数组，查询部门
	 * @param dto
	 * @param departmentTempIdsArr
	 * @return
	 */
	public List<DepartmentTempDTO> findDepartmentTempAllByIdsArr(DepartmentTempDTO dto, Long[] departmentTempIdsArr) {
		return departmentTempReadService.findDepartmentTempAllByIdsArr(dto,departmentTempIdsArr);
	}

	/**
	 * 将树插入到临时数据库，并临时记录表头
	 * @param sortList
	 * @param platformId 
	 * @param companyId 
	 * @return
	 */
	public Map<String, Object> insertInfoInDeptAndRecord(List<DepartmentTreeDTO> sortList, Long companyId,
			Long platformId, ImportRecordsDTO importRecordsDTO) {
		
		Map<String, Object> data =new HashMap<>();
		String departmentTempListIdStr = departmentTempWriteService.insertDepartmentTempPlusWithTx(sortList,companyId,platformId);
		data.put("departmentTempListIdStr", departmentTempListIdStr);
		
		Long importRecordId = importRecordsWriteService.insertImportRecordsWithTx(importRecordsDTO);
		//将要填在导入记录表里面的数据返回
		data.put("importRecordId", importRecordId);
		
		return data;
	}

}
	