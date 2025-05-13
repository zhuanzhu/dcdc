package com.egeo.components.user.manage.read;

import java.util.List;
	
import com.egeo.components.user.po.DepartmentPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface DepartmentReadManage {

	public DepartmentPO findDepartmentById(DepartmentPO po);

	public PageResult<DepartmentPO> findDepartmentOfPage(DepartmentPO po,Pagination page);

	public List<DepartmentPO> findDepartmentAll(DepartmentPO po);

	/**
	 * 根据用户id查询部门id
	 * @param userId
	 * @return
	 */
	public DepartmentPO queryDepartmentByUserId(Long userId);

	/**
	 * 根据公司id查询最下级部门列表
	 * @param companyId
	 * @return
	 */
	public List<DepartmentPO> queryLeafDepByCompanyId(Long companyId);

	/**
	 * 查询公司一级部门列表
	 * @param companyId
	 * @return
	 */
	public List<DepartmentPO> querySuperDepListByCompanyId(Long companyId);

	/**
	 * 查询下级部门列表
	 * @param id
	 * @return
	 */
	public List<DepartmentPO> queryDepsByPid(Long id);

	/**
	 * 查询公司所有部门
	 * @param companyId
	 * @return
	 */
	public List<DepartmentPO> queryDepsByCompanyId(Long companyId);
}
	