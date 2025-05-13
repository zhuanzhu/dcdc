package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.ShoppingLabelGroupPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ShoppingLabelGroupReadManage {

	public ShoppingLabelGroupPO findShoppingLabelGroupById(ShoppingLabelGroupPO po);

	public PageResult<ShoppingLabelGroupPO> findShoppingLabelGroupOfPage(ShoppingLabelGroupPO po,Pagination page);

	public List<ShoppingLabelGroupPO> findShoppingLabelGroupAll(ShoppingLabelGroupPO po);

	/**
	 * 根据实例id查询商城标签组
	 * @param instId
	 * @return
	 */
	public ShoppingLabelGroupPO queryShoppingLabelGroupByInstId(Long instId);
}
	