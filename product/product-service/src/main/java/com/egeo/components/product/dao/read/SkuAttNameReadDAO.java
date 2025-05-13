package com.egeo.components.product.dao.read;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.condition.SkuAttNameCondition;
import com.egeo.components.product.po.SkuAttNamePO;
import com.egeo.orm.BaseReadDAO;

public interface SkuAttNameReadDAO extends BaseReadDAO<SkuAttNamePO>{
	/**
	 * 根据skuid查询sku属性和属性值集合
	 * @param skuId
	 * @return
	 */
	List<SkuAttNameCondition> findSkuAttNameByskuId(@Param("skuId")Long skuId);

	/**
	 * 根据skuId查询attName列表
	 * @param skuId
	 * @return
	 */
	List<SkuAttNamePO> querySkuAttNamesBySkuId(@Param("skuId")Long skuId);

    Long findLastId();
}
	