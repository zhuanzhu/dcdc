package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.NavigationBarLabelDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface NavigationBarLabelReadService {

	public NavigationBarLabelDTO findNavigationBarLabelById(NavigationBarLabelDTO dto);

	public PageResult<NavigationBarLabelDTO> findNavigationBarLabelOfPage(NavigationBarLabelDTO dto,Pagination page);

	public List<NavigationBarLabelDTO> findNavigationBarLabelAll(NavigationBarLabelDTO dto);
}
	