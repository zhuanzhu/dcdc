package com.egeo.components.user.service.read;

public interface CompanyCoreReadService {
	/**
	 * 根据公司id查询公司类型
	 * @param companyId
	 * @return
	 */
	public Integer findCompanyTypeById(Long companyId);
	
	/**
	 * 根据用户id查询公司类型
	 * @param companyId
	 * @return
	 */
	public Integer findCompanyTypeByUserId(Long userId);
	
	/**
	 * 根据公司id查询公司类型对应的所有公司id
	 * @param companyId
	 * @return
	 */
	public Long findCompanyAllIdByCompanyId(Long companyId);

	public Long findEnterpriseCompanyAllId(Long companyId);

	public Long findCompanyAllIdByCompanyType(Integer companyType);

}

	