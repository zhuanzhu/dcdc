package com.egeo.components.finance.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.finance.po.CompanyAccountPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CompanyAccountReadDAO extends BaseReadDAO<CompanyAccountPO> {

	/**
	 * 公司账户分页列表
	 * 
	 * @param page
	 * @param accountName
	 * @param companyId
	 * @param disabled
	 * @return
	 */
	List<CompanyAccountPO> queryCompanyAccountPage(@Param("page") Pagination page,
			@Param("accountName") String accountName, @Param("companyId") List<Long> companyId,
			@Param("disabled") Integer disabled,@Param("platformId")Long platformId);

	/**
	 * 公司账户分页列表总记录
	 * 
	 * @param accountName
	 * @param companyId
	 * @param disabled
	 * @return
	 */
	Integer queryCompanyAccountPageTotalCount(@Param("accountName") String accountName,
			@Param("companyId") List<Long> companyId, @Param("disabled") Integer disabled,@Param("platformId")Long platformId);

	/**
	 * 查询所有调整原因
	 * 
	 * @param platformId
	 * @return
	 */
	List<CompanyAccountPO> queryNormalAccounts(@Param("platformId") Long platformId,@Param("companyId") List<Long> companyId);

	/**
	 * 根据类型查询特殊账户
	 * 
	 * @param type
	 * @return
	 */
	CompanyAccountPO querySpecialCompanyAccountByType(@Param("type") int type,@Param("platformId") Long platformId);

	/**
	 * 批量查询公司账户
	 * 
	 * @param accountIds
	 * @return
	 */
	List<CompanyAccountPO> queryCompanyAccountsByIds(@Param("ids") List<Long> accountIds);

	/**
	 * 根据公司id查询公司普通账户
	 * @param companyId
	 * @return
	 */
	CompanyAccountPO queryNormalCompanyAccountByCompnayId(@Param("companyId")Long companyId);

	/**
	 * 根据账户id查询公司账户
	 * @param accountId
	 * @return
	 */
	CompanyAccountPO queryCompanyAccountById(@Param("id")Long accountId);
}
