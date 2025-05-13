package com.egeo.components.cms.service.read;


import java.util.List;
	
import com.egeo.components.cms.dto.ShoppingLabelGroupDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ShoppingLabelGroupReadService {

	public ShoppingLabelGroupDTO findShoppingLabelGroupById(ShoppingLabelGroupDTO dto);

	public PageResult<ShoppingLabelGroupDTO> findShoppingLabelGroupOfPage(ShoppingLabelGroupDTO dto,Pagination page);

	public List<ShoppingLabelGroupDTO> findShoppingLabelGroupAll(ShoppingLabelGroupDTO dto);

	/**
	 * 根据实例id查询商城标签组
	 * @param instId
	 * @return
	 */
	public ShoppingLabelGroupDTO queryShoppingLabelGroupByInstId(Long instId);
}
	