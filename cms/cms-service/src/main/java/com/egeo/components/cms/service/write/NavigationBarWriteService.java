package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.NavigationBarDTO;


public interface NavigationBarWriteService {

	public Long insertNavigationBarWithTx(NavigationBarDTO dto);

	public int updateNavigationBarWithTx(NavigationBarDTO dto);

	public int deleteNavigationBarWithTx(NavigationBarDTO dto);
}
	