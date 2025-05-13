package com.egeo.components.product.manage.read;

import java.util.List;
	
import com.egeo.components.product.po.CardThirdpartyAttValuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CardThirdpartyAttValueReadManage {

	public CardThirdpartyAttValuePO findCardThirdpartyAttValueById(CardThirdpartyAttValuePO po);

	public PageResult<CardThirdpartyAttValuePO> findCardThirdpartyAttValueOfPage(CardThirdpartyAttValuePO po,Pagination page);

	public List<CardThirdpartyAttValuePO> findCardThirdpartyAttValueAll(CardThirdpartyAttValuePO po);
	/**
	 * 根据分组的mapkey查询spu第三方参数其对应的卡类型
	 * @param standardProductUnitId
	 * @return
	 */
	public Integer findCardTypeByStandardProductUnitId(Long standardProductUnitId);
}
	