package com.egeo.components.user.manage.read;

import java.util.List;

import com.egeo.components.user.po.CompanyPO;
import com.egeo.components.user.po.DepartmentPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CompanyReadManage {

	public CompanyPO findCompanyById(CompanyPO po);

	public PageResult<CompanyPO> findCompanyOfPage(CompanyPO po,Pagination page,List<Long> companyIdList);

	public List<CompanyPO> findCompanyAll(CompanyPO po);
	/**
	 * 根据companyId查询部门列表
	 * @param companyId
	 * @return
	 */
	List<DepartmentPO> queryDepartmentListByCompanyId(Long companyId);

	public List<CompanyPO> queryCompanysByPlatformId(Long platformId);

	/**
	 * 查询用户公司
	 * @param userId
	 * @return
	 */
	public CompanyPO queryCompanyByUserId(long userId);
	/**
	 * 根据福利公司id集合查询福利公司信息
	 * @param companyIds
	 * @return
	 */
	public List<CompanyPO> findCompanyByCompanyIds(List<Long> companyIds);
	/**
	 * 根据公司id集合查询公司名称信息
	 * @param companyIds
	 * @return
	 */
	public List<String> findByCompanys(List<Long> companyIds);

	/**
	 * 批量查询公司
	 * @param ids
	 * @return
	 */
	public List<CompanyPO> queryCompaniesByIds(List<Long> ids);

	/**
	 * 根据名称查询公司
	 * @param name
	 * @return
	 */
	public CompanyPO queryCompanyByName(String name);

	public List<CompanyPO> findCompanyAllByFuzzyName(CompanyPO po);
	/**
	 * 查询所有福利公司
	 * @return
	 */
	public List<CompanyPO> findCompanyAll();


}
	