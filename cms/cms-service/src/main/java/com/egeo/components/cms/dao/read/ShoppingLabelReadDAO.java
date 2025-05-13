package com.egeo.components.cms.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.cms.po.ShoppingLabelPO;
import com.egeo.orm.BaseReadDAO;

public interface ShoppingLabelReadDAO extends BaseReadDAO<ShoppingLabelPO>{

	/**
	 * 根据组id查询商城标签列表
	 * @param groupId
	 * @return
	 */
	List<ShoppingLabelPO> queryShoppingLabelListByGroupId(@Param("groupId")Long groupId);
}
	