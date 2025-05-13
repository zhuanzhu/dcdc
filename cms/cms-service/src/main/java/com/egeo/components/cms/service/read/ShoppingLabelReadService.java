package com.egeo.components.cms.service.read;


import java.util.List;

import com.egeo.components.cms.dto.ShoppingLabelDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ShoppingLabelReadService {

	public ShoppingLabelDTO findShoppingLabelById(ShoppingLabelDTO dto);

	public PageResult<ShoppingLabelDTO> findShoppingLabelOfPage(ShoppingLabelDTO dto,Pagination page);

	public List<ShoppingLabelDTO> findShoppingLabelAll(ShoppingLabelDTO dto);

	/**
	 * 根据组id查询商城标签列表
	 * @param groupId
	 * @return
	 */
	public List<ShoppingLabelDTO> queryShoppingLabelListByGroupId(Long groupId);

}
	