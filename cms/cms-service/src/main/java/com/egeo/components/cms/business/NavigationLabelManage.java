package com.egeo.components.cms.business;

import java.util.List;
	
import com.egeo.components.cms.dto.NavigationLabelDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface NavigationLabelManage {

	public NavigationLabelDTO findNavigationLabelById(NavigationLabelDTO dto);	

	public PageResult<NavigationLabelDTO> findNavigationLabelOfPage(NavigationLabelDTO dto,Pagination page);

	public List<NavigationLabelDTO> findNavigationLabelAll(NavigationLabelDTO dto);

	Long insertNavigationLabelWithTx(NavigationLabelDTO dto);

	int updateNavigationLabelWithTx(NavigationLabelDTO dto);

	int deleteNavigationLabelWithTx(NavigationLabelDTO dto);
}
	