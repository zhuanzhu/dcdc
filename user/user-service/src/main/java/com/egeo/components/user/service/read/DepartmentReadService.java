package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.DepartmentDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface DepartmentReadService {

	public DepartmentDTO findDepartmentById(DepartmentDTO dto);

	public PageResult<DepartmentDTO> findDepartmentOfPage(DepartmentDTO dto,Pagination page);

	public List<DepartmentDTO> findDepartmentAll(DepartmentDTO dto);
	/**
	 * 根据公司id递归查询所有部门信息
	 * @param dto
	 * @return
	 */
	public List<DepartmentDTO> recursionDepartmentAll(DepartmentDTO dto);

	/**
	 * 查询用户所在部门
	 * @param mpUserId
	 * @return
	 */
	public DepartmentDTO queryDepartmentByUserId(Long mpUserId);

	/**
	 * 查询公司最下级部门列表
	 * @param companyId
	 * @return
	 */
	public List<DepartmentDTO> queryLeafDepByCompanyId(Long companyId);

	/**
	 * 查询公司一级部门列表
	 * @param companyId
	 * @return
	 */
	public List<DepartmentDTO> querySuperDepListByCompanyId(Long companyId);

	/**
	 * 查询下级部门列表
	 * @param id
	 * @return
	 */
	public List<DepartmentDTO> queryDepsByPid(Long id);

	/**
	 * 查询公司所有部门
	 * @param companyId
	 * @return
	 */
	public List<DepartmentDTO> queryDepsByCompanyId(Long companyId);
	
	
}
	