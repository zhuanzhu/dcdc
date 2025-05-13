package com.egeo.components.product.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.SkuAttValuePO;
import com.egeo.orm.BaseReadDAO;

public interface SkuAttValueReadDAO extends BaseReadDAO<SkuAttValuePO>{

	/**
	 * 根据sku_att_name_id字段查询结果
	 * @param id
	 * @return
	 */
	SkuAttValuePO querySkuAttValueBySkuAttNameId(@Param("id")Long id);
	/**
	 * 根据属性id和skuid查询属性值id
	 * @param attNameId
	 * @param skuId
	 * @return
	 */
	Long findAttValueIdByAttNameIdSkuId(@Param("attNameId")Long attNameId, @Param("skuId")Long skuId);
}
	