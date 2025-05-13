package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.NavigationBarDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface NavigationBarReadService {

	public NavigationBarDTO findNavigationBarById(NavigationBarDTO dto);

	public PageResult<NavigationBarDTO> findNavigationBarOfPage(NavigationBarDTO dto,Pagination page);

	public List<NavigationBarDTO> findNavigationBarAll(NavigationBarDTO dto);

	/**
	 * 模糊查询导航栏列表
	 * @param dto
	 * @param page
	 * @return
	 */
	public PageResult<NavigationBarDTO> findNavigationBarOfPageByBlurry(NavigationBarDTO dto, Pagination page);
}
	