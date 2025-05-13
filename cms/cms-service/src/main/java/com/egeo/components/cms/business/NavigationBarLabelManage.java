package com.egeo.components.cms.business;

import java.util.List;
	
import com.egeo.components.cms.dto.NavigationBarLabelDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface NavigationBarLabelManage {

	public NavigationBarLabelDTO findNavigationBarLabelById(NavigationBarLabelDTO dto);	

	public PageResult<NavigationBarLabelDTO> findNavigationBarLabelOfPage(NavigationBarLabelDTO dto,Pagination page);

	public List<NavigationBarLabelDTO> findNavigationBarLabelAll(NavigationBarLabelDTO dto);

	Long insertNavigationBarLabelWithTx(NavigationBarLabelDTO dto);

	int updateNavigationBarLabelWithTx(NavigationBarLabelDTO dto);

	int deleteNavigationBarLabelWithTx(NavigationBarLabelDTO dto);
}
	