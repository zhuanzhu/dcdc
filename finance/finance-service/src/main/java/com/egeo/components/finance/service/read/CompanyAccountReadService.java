package com.egeo.components.finance.service.read;


import java.util.List;

import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CompanyAccountReadService {

	public CompanyAccountDTO findCompanyAccountById(CompanyAccountDTO dto);

	public PageResult<CompanyAccountDTO> findCompanyAccountOfPage(CompanyAccountDTO dto,Pagination page);

	public List<CompanyAccountDTO> findCompanyAccountAll(CompanyAccountDTO dto);

	/**
	 * 查询公司账户分页列表
	 * @param page
	 * @param accountName
	 * @param companyId
	 * @param disabled
	 * @return
	 */
	public PageResult<CompanyAccountDTO> queryCompanyAccountPage(Pagination page, String accountName, List<Long> companyId,
			Integer disabled,Long platformId);

	public List<CompanyAccountDTO> queryNormalAccounts(Long platformId,List<Long> companyId);

	/**
	 * 根据类型查询特殊账户
	 *
     * @param platformId
     * @param type
     * @return
	 */
	public CompanyAccountDTO querySpecialCompanyAccountByType(Long platformId, int type);

	/**
	 * 批量查询公司账户
	 * @param accountIds
	 * @return
	 */
	public List<CompanyAccountDTO> queryCompanyAccountsByIds(List<Long> accountIds);

	/**
	 * 根据公司id查询公司账户
	 * @param companyId
	 * @return
	 */
	public CompanyAccountDTO queryNormalCompanyAccountByCompnayId(Long companyId);
}
	