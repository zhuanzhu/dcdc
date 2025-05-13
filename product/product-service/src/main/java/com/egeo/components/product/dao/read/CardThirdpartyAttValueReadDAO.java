package com.egeo.components.product.dao.read;

import org.apache.ibatis.annotations.Param;

import com.egeo.components.product.po.CardThirdpartyAttValuePO;
import com.egeo.orm.BaseReadDAO;

public interface CardThirdpartyAttValueReadDAO extends BaseReadDAO<CardThirdpartyAttValuePO>{
	/**
	 * 根据分组的mapkey查询spu第三方参数其对应的卡类型
	 * @param standardProductUnitId
	 * @return
	 */
	Integer findCardTypeByStandardProductUnitId(@Param("standardProductUnitId")Long standardProductUnitId);
}
	