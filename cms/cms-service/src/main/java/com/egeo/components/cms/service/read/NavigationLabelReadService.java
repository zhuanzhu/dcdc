package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.NavigationLabelDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface NavigationLabelReadService {

	public NavigationLabelDTO findNavigationLabelById(NavigationLabelDTO dto);

	public PageResult<NavigationLabelDTO> findNavigationLabelOfPage(NavigationLabelDTO dto,Pagination page);

	public List<NavigationLabelDTO> findNavigationLabelAll(NavigationLabelDTO dto);
}
	