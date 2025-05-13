package com.egeo.components.finance.manage.read;

import java.util.List;

import com.egeo.components.finance.po.CompanyAccountPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CompanyAccountReadManage {

	public CompanyAccountPO findCompanyAccountById(CompanyAccountPO po);

	public PageResult<CompanyAccountPO> findCompanyAccountOfPage(CompanyAccountPO po,Pagination page);

	public List<CompanyAccountPO> findCompanyAccountAll(CompanyAccountPO po);

	/**
	 * 查询公司账户分页列表
	 * @param page
	 * @param accountName
	 * @param companyId
	 * @param disabled
	 * @return
	 */
	public PageResult<CompanyAccountPO> queryCompanyAccountPage(Pagination page, String accountName, List<Long> companyId,
			Integer disabled,Long platformId);

	/**
	 * 查询所有调整原因
	 * @param platformId
	 * @return
	 */
	public List<CompanyAccountPO> queryNormalAccounts(Long platformId,List<Long> companyId);

	/**
	 * 根据类型查询特殊账户
	 *
     * @param platformId
     * @param type
     * @return
	 */
	public CompanyAccountPO querySpecialCompanyAccountByType(Long platformId, int type);

	/**
	 * 批量查询公司账户
	 * @param accountIds
	 * @return
	 */
	public List<CompanyAccountPO> queryCompanyAccountsByIds(List<Long> accountIds);

	/**
	 * 根据公司id查询公司账户
	 * @param companyId
	 * @return
	 */
	public CompanyAccountPO queryNormalCompanyAccountByCompnayId(Long companyId);

	/**
	 * 根据账户id查询公司账户
	 * @param accountId
	 * @return
	 */
	public CompanyAccountPO queryCompanyAccountById(Long accountId);
}
	