package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.vo.DepartmentVO;
import com.egeo.components.user.dto.DepartmentDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.web.JsonResult;	

public interface DepartmentManage {

	public Map<String, Object> findDepartmentById(DepartmentDTO dto);	

	public PageResult<DepartmentDTO> findDepartmentOfPage(DepartmentDTO dto,Pagination page);

	public List<DepartmentDTO> findDepartmentAll(DepartmentDTO dto);

	Long insertDepartmentWithTx(DepartmentDTO dto);

	int updateDepartmentWithTx(DepartmentDTO dto);

	int deleteDepartmentWithTx(DepartmentDTO dto);
	/**
	 * 根据公司id递归查询所有部门信息
	 * @param dto
	 * @return
	 */
	public List<DepartmentVO> recursionDepartmentAll(DepartmentDTO dto);
	/**
	 * 根据公司id机构id查询子部门信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<Map<String, Object>> departmentBydepartmentId(Long companyId, Long departmentId,Long platformId);

	/**
	 * 公司一级部门列表
	 * @param companyId
	 * @return
	 */
	public JsonResult<Map<String, Object>> superDepList(Long companyId);

	/**
	 * 查询部门的下级部门和本部门直属员工列表
	 * @param id
	 * @param pageSize 
	 * @param pageNo 
	 * @param userId 
	 * @return
	 */
	public JsonResult<Map<String, Object>> depMembersAndSubDeps(Long id, Integer pageNo, Integer pageSize, Long userId);

	/**
	 * 该部门是否有子部门
	 * @param id
	 * @return
	 */
	public JsonResult<Map<String, Object>> haveSubDep(Long id);

	/**
	 * 公司部门树
	 * @param companyId
	 * @return
	 */
	public JsonResult<Map<String, Object>> depTree(Long companyId);
	
	/**
	 * 公司部门树2
	 * @param companyId
	 * @return
	 */
	public JsonResult<Map<String, Object>> depTree2(Long companyId);
	
	/**
	 * 通过公司id查询公司的所有部门
	 * @param companyId
	 * @return
	 */
	public JsonResult<Map<String, Object>> depTree3(Long companyId);

	/**
	 * 部门员工分页列表
	 * @param depId
	 * @return
	 */
	public JsonResult<Map<String, Object>> depMemPage(Integer pageNo,Integer pageSize,Long depId,Long userId);

	public JsonResult<Map<String, Object>> insertDepartmentWithTx(Long companyId, Long platformId,
			List<Map<String, Object>> valueList);

	public Long deleteByCompanyId(DepartmentDTO dtoRe);

	public Long insertDepartmentFromTmpWithTx(DepartmentDTO departmentDTO2);

	/**
	 * 确认导入
	 * @param companyId
	 * @param parseLong
	 * @param departmentTempListIdStr
	 * @param importRecordId
	 * @return
	 */
	public JsonResult<String> assureImportDepartmentAll(Long companyId, Long parseLong, String departmentTempListIdStr,
			String importRecordId);

	/**
	 * 导出组织架构
	 * @param departmentDTO
	 * @return
	 */
	public JsonResult<String> outImportDepartment(DepartmentDTO departmentDTO);

}
	