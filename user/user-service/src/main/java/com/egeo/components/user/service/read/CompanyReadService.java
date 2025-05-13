package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.CompanyDTO;
import com.egeo.components.user.dto.DepartmentDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface CompanyReadService {

	public CompanyDTO findCompanyById(Long id);

	public PageResult<CompanyDTO> findCompanyOfPage(CompanyDTO dto,Pagination page,List<Long> companyIdList);

	public List<CompanyDTO> findCompanyAll(CompanyDTO dto);
	/**
	 * 根据companyId查询部门列表
	 * @param companyId
	 * @return
	 */
	public List<DepartmentDTO> queryDepartmentListByCompanyId(Long companyId);

	/**
	 * 根据平台id查询公司列表
	 * @param platformId
	 * @return
	 */
	public List<CompanyDTO> queryCompanysByPlatformId(Long platformId);

	/**
	 * 查询用户公司
	 * @param userId
	 * @return
	 */
	public CompanyDTO queryCompanyByUserId(long userId);
	/**
	 * 根据福利公司id集合查询福利公司信息
	 * @param companyIds
	 * @return
	 */
	public List<CompanyDTO> findCompanyByCompanyIds(List<Long> companyIds);
	/**
	 * 根据公司id集合查询公司名称信息
	 * @param companyIds
	 * @return
	 */
	public List<String> findByCompanys(List<Long> companyIds);

	/**
	 * 批量查询公司
	 * @param companyIds
	 * @return
	 */
	public List<CompanyDTO> queryCompaniesByIds(List<Long> companyIds);

	/**
	 * 根据名称查询公司
	 * @param name
	 * @return
	 */
	public CompanyDTO queryCompanyByName(String name);

	public List<CompanyDTO> findCompanyAllByFuzzyName(CompanyDTO companyDTO);
	/**
	 * 查询所有福利公司
	 * @return
	 */
	public List<CompanyDTO> findCompanyAll();

    List<CompanyDTO> findCompanyByWorkWechat(CompanyDTO dto);
}
	