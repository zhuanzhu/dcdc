package com.egeo.components.user.facade;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.config.client.HeadImportRecordsClient;
import com.egeo.components.config.client.ImportRecordsClient;
import com.egeo.components.config.dto.HeadImportRecordsDTO;
import com.egeo.components.config.dto.ImportRecordsDTO;
import com.egeo.components.user.dto.DepartmentDTO;
import com.egeo.components.user.service.read.DepartmentReadService;
import com.egeo.components.user.service.write.DepartmentWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;


@Component
public class DepartmentFacade {
	
	@Resource
	private DepartmentReadService departmentReadService;
	
	@Resource
	private DepartmentWriteService departmentWriteService;
	
	@Autowired
	private ImportRecordsClient importRecordsReadService;
	
	@Autowired
	private HeadImportRecordsClient headImportRecordsReadService;
	
	@Autowired
	private HeadImportRecordsClient headImportRecordsWriteService;

	
	
	public DepartmentDTO findDepartmentById(DepartmentDTO dto){
		
		return departmentReadService.findDepartmentById(dto);
	}

	public PageResult<DepartmentDTO> findDepartmentOfPage(DepartmentDTO dto,Pagination page){
		
		return departmentReadService.findDepartmentOfPage(dto, page);
		
	}

	public List<DepartmentDTO> findDepartmentAll(DepartmentDTO dto){
		
		return departmentReadService.findDepartmentAll(dto);
		
	}

	public Long insertDepartmentWithTx(DepartmentDTO dto){
		
		return departmentWriteService.insertDepartmentWithTx(dto);
	}

	public int updateDepartmentWithTx(DepartmentDTO dto){
		
		return departmentWriteService.updateDepartmentWithTx(dto);
	}

	public int deleteDepartmentWithTx(DepartmentDTO dto){
		
		return departmentWriteService.deleteDepartmentWithTx(dto);
		
	}

	public List<DepartmentDTO> recursionDepartmentAll(DepartmentDTO dto) {
		return departmentReadService.recursionDepartmentAll(dto);
	}

	/**
	 * 根据公司id查询一级部门列表
	 * @param companyId
	 * @return
	 */
	public List<DepartmentDTO> querySuperDepListByCompanyId(Long companyId) {
		
		return departmentReadService.querySuperDepListByCompanyId(companyId);
	}

	/**
	 * 查询下级部门列表
	 * @param id
	 * @return
	 */
	public List<DepartmentDTO> queryDepsByPid(Long id) {
		return departmentReadService.queryDepsByPid(id);
	}

	/**
	 * 查询公司所有部门
	 * @param companyId
	 * @return
	 */
	public List<DepartmentDTO> queryDepsByCompanyId(Long companyId) {
		return departmentReadService.queryDepsByCompanyId(companyId);
	}


	public Long deleteByCompanyId(DepartmentDTO dtoRe) {
		return departmentWriteService.deleteByCompanyId(dtoRe);
	}


	public Long insertHeadImportRecord(String companyName, Date generatedDate, String fileSequenceNumber) {
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();
		headImportRecordsDTO.setCompanyName(companyName);
		headImportRecordsDTO.setTemplateType("组织架构导入");
		headImportRecordsDTO.setGeneratedTime(generatedDate);
		headImportRecordsDTO.setFileSequenceNumber(fileSequenceNumber);
		return headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
	}

	/**
	 * 查询导入正式记录表的信息
	 * @param headImportRecordsDTO
	 * @return
	 */
	public List<HeadImportRecordsDTO> findHeadImportRecordsAll(HeadImportRecordsDTO headImportRecordsDTO) {
		return headImportRecordsReadService.findHeadImportRecordsAll(headImportRecordsDTO);
	}

	public Long insertHeadImportRecordByImportRecordById(Long parseLong) {
		ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
		importRecordsDTO.setId(parseLong);
		ImportRecordsDTO dto = importRecordsReadService.findImportRecordsById(importRecordsDTO);
		
		//生成头信息记录对象
		HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();
		headImportRecordsDTO.setId(dto.getId());
		headImportRecordsDTO.setTemplateType(dto.getTemplateType());
		headImportRecordsDTO.setGeneratedTime(dto.getGeneratedTime());
		headImportRecordsDTO.setFileSequenceNumber(dto.getFileSequenceNumber());
		headImportRecordsDTO.setCompanyName(dto.getCompanyName());
		
		
		return headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
		
	}

	/**
	 * 确认导入组织架构表
	 * @param companyId
	 * @param departmentDTOList
	 * @param parseLong
	 * @return
	 */
	public int assureImportDepartmentAll(Long companyId, List<DepartmentDTO> departmentDTOList,
			Long parseLong) {
		
			//1需要清除本公司原来的组织架构信息（在正式表）
			DepartmentDTO dtoRe=new DepartmentDTO();
			dtoRe.setCompanyId(companyId);
			List<DepartmentDTO> findDepartmentAll = departmentReadService.findDepartmentAll(dtoRe);
			if(EmptyUtil.isNotEmpty(findDepartmentAll)){
				departmentWriteService.deleteByCompanyId(dtoRe);
			}
			
			//2将临时表的数据同步到正式表
			for (DepartmentDTO departmentDTO : departmentDTOList) {
				departmentWriteService.insertDepartmentWithTx(departmentDTO);
			}
			
			
			//3.在记录表里面插入数据
			
			//根据插入在临时表中的id查询记录表信息
			ImportRecordsDTO importRecordsDTO = new ImportRecordsDTO();
			importRecordsDTO.setId(parseLong);
			ImportRecordsDTO dto = importRecordsReadService.findImportRecordsById(importRecordsDTO);
			
			//生成头信息记录对象
			HeadImportRecordsDTO headImportRecordsDTO = new HeadImportRecordsDTO();
			headImportRecordsDTO.setId(dto.getId());
			headImportRecordsDTO.setTemplateType(dto.getTemplateType());
			headImportRecordsDTO.setGeneratedTime(dto.getGeneratedTime());
			headImportRecordsDTO.setFileSequenceNumber(dto.getFileSequenceNumber());
			headImportRecordsDTO.setCompanyName(dto.getCompanyName());
			headImportRecordsWriteService.insertHeadImportRecordsWithTx(headImportRecordsDTO);
		
		return 0;
	}

}
	