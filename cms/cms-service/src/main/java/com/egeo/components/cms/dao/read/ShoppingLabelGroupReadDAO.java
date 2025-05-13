package com.egeo.components.cms.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.ShoppingLabelGroupPO;
import com.egeo.orm.BaseReadDAO;

public interface ShoppingLabelGroupReadDAO extends BaseReadDAO<ShoppingLabelGroupPO>{

	/**
	 * 根据实例id查询商城标签组
	 * @param instId
	 * @return
	 */
	ShoppingLabelGroupPO queryShoppingLabelGroupByInstId(@Param("instId")Long instId);
}
	