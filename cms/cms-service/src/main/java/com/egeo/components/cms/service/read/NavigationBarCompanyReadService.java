package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.NavigationBarCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface NavigationBarCompanyReadService {

	public NavigationBarCompanyDTO findNavigationBarCompanyById(NavigationBarCompanyDTO dto);

	public PageResult<NavigationBarCompanyDTO> findNavigationBarCompanyOfPage(NavigationBarCompanyDTO dto,Pagination page);

	public List<NavigationBarCompanyDTO> findNavigationBarCompanyAll(NavigationBarCompanyDTO dto);

	/**
	 * 通过公司id集合查询首页导航栏
	 * @param companyIdList
	 * @return
	 */
	public List<NavigationBarCompanyDTO> findPageTabAllByCompanyId(List<Long> companyIdList);
}
	