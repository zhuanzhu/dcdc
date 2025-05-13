package com.egeo.components.product.manage.write;

import com.egeo.components.product.po.SerachSortPO;


public interface SerachSortWriteManage {

	Long insertSerachSortWithTx(SerachSortPO po);

	int updateSerachSortWithTx(SerachSortPO po);

	int deleteSerachSortWithTx(SerachSortPO po);
}
	