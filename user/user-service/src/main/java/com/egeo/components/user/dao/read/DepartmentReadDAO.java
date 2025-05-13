package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.DepartmentPO;
import com.egeo.orm.BaseReadDAO;

public interface DepartmentReadDAO extends BaseReadDAO<DepartmentPO>{

	/**
	 * 查询用户部门信息
	 * @param userId
	 * @return
	 */
	DepartmentPO queryDepartmentByUserId(@Param("userId")Long userId);

	/**
	 * 根据公司id查询最下级部门列表
	 * @param companyId
	 * @return
	 */
	List<DepartmentPO> queryLeafDepByCompanyId(@Param("companyId")Long companyId);

	/**
	 * 查询公司一级部门列表
	 * @param companyId
	 * @return
	 */
	List<DepartmentPO> querySuperDepListByCompanyId(@Param("companyId")Long companyId);

	/**
	 * 查询下级部门列表
	 * @param id
	 * @return
	 */
	List<DepartmentPO> queryDepsByPid(@Param("id")Long id);

	/**
	 * 查询公司所有部门
	 * @param companyId
	 * @return
	 */
	List<DepartmentPO> queryDepartmentByCompanyId(@Param("companyId")Long companyId);
}
	