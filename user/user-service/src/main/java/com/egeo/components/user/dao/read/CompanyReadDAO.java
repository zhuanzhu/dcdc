package com.egeo.components.user.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.user.po.CompanyPO;
import com.egeo.orm.BaseReadDAO;
import com.egeo.orm.Pagination;

public interface CompanyReadDAO extends BaseReadDAO<CompanyPO>{

	/**
	 * 根据平台id查询公司
	 * @param platformId 
	 * @return
	 */
	List<CompanyPO> queryCompanysByPlatformId(@Param("platformId")Long platformId);

	/**
	 * 查询用户公司
	 * @param userId
	 * @return
	 */
	CompanyPO queryCompanyByUserId(@Param("userId")Long userId);
	/**
	 * 根据福利公司id集合查询福利公司信息
	 * @param companyIds
	 * @return
	 */
	List<CompanyPO> findCompanyByCompanyIds(@Param("ids")List<Long> companyIds);
	/**
	 * 根据公司id集合查询公司名称信息
	 * @param companyIds
	 * @return
	 */
	List<String> findByCompanys(@Param("ids")List<Long> companyIds);

	/**
	 * 批量查询公司
	 * @param ids
	 * @return
	 */
	List<CompanyPO> queryCompaniesByIds(@Param("ids")List<Long> ids);

	/**
	 * 根据名称查询公司
	 * @param name
	 * @return
	 */
	CompanyPO queryCompanyByName(@Param("name")String name);

	/**
	 * 根据公司名称模糊查询公司
	 * @param po
	 * @return
	 */
	List<CompanyPO> findAllByFuzzyName(CompanyPO po);
	/**
	 * 查询所有福利公司
	 * @return
	 */
	List<CompanyPO> findCompanyAll();

	int countCompanyOfPage(@Param("po")CompanyPO po, @Param("ids")List<Long> companyIdList);

	List<CompanyPO> findCompanyOfPage(@Param("po")CompanyPO po, @Param("page")Pagination page, @Param("ids")List<Long> companyIdList);

}
	