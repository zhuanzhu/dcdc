package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.SkuAttNameCondition;
import com.egeo.components.product.po.SkuAttNamePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SkuAttNameReadManage {

	public SkuAttNamePO findSkuAttNameById(SkuAttNamePO po);

	public PageResult<SkuAttNamePO> findSkuAttNameOfPage(SkuAttNamePO po,Pagination page);

	public List<SkuAttNamePO> findSkuAttNameAll(SkuAttNamePO po);
	/**
	 * 根据skuid查询sku属性和属性值集合
	 * @param skuId
	 * @return
	 */
	public List<SkuAttNameCondition> findSkuAttNameByskuId(Long skuId);

    Long findLastId();
}
	