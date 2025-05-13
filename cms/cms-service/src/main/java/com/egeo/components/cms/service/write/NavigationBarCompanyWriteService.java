package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.NavigationBarCompanyDTO;


public interface NavigationBarCompanyWriteService {

	public Long insertNavigationBarCompanyWithTx(NavigationBarCompanyDTO dto);

	public int updateNavigationBarCompanyWithTx(NavigationBarCompanyDTO dto);

	public int deleteNavigationBarCompanyWithTx(NavigationBarCompanyDTO dto);
}
	