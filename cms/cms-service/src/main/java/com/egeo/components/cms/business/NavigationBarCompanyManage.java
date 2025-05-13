package com.egeo.components.cms.business;

import java.util.List;
	
import com.egeo.components.cms.dto.NavigationBarCompanyDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface NavigationBarCompanyManage {

	public NavigationBarCompanyDTO findNavigationBarCompanyById(NavigationBarCompanyDTO dto);	

	public PageResult<NavigationBarCompanyDTO> findNavigationBarCompanyOfPage(NavigationBarCompanyDTO dto,Pagination page);

	public List<NavigationBarCompanyDTO> findNavigationBarCompanyAll(NavigationBarCompanyDTO dto);

	Long insertNavigationBarCompanyWithTx(NavigationBarCompanyDTO dto);

	int updateNavigationBarCompanyWithTx(NavigationBarCompanyDTO dto);

	int deleteNavigationBarCompanyWithTx(NavigationBarCompanyDTO dto);
}
	