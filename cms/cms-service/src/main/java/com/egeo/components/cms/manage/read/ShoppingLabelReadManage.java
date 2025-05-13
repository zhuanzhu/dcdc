package com.egeo.components.cms.manage.read;

import java.util.List;
	
import com.egeo.components.cms.po.ShoppingLabelPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface ShoppingLabelReadManage {

	public ShoppingLabelPO findShoppingLabelById(ShoppingLabelPO po);

	public PageResult<ShoppingLabelPO> findShoppingLabelOfPage(ShoppingLabelPO po,Pagination page);

	public List<ShoppingLabelPO> findShoppingLabelAll(ShoppingLabelPO po);

	/**
	 * 根据组id查询商城标签列表
	 * @param groupId
	 * @return
	 */
	public List<ShoppingLabelPO> queryShoppingLabelListByGroupId(Long groupId);

}	