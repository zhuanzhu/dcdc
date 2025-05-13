package com.egeo.components.cms.service.write;

import com.egeo.components.cms.dto.NavigationBarLabelDTO;


public interface NavigationBarLabelWriteService {

	public Long insertNavigationBarLabelWithTx(NavigationBarLabelDTO dto);

	public int updateNavigationBarLabelWithTx(NavigationBarLabelDTO dto);

	public int deleteNavigationBarLabelWithTx(NavigationBarLabelDTO dto);
}
	