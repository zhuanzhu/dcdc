package com.egeo.components.promotion.manage.read;

import java.util.List;
import java.util.Map;

import com.egeo.components.promotion.po.ECardPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface ECardReadManage {

	public ECardPO findECardById(ECardPO po);

	public PageResult<ECardPO> findECardOfPage(ECardPO po,Pagination page);

	public List<ECardPO> findECardAll(ECardPO po);

	public List<ECardPO> queryECardListByKey(Map<String, Object> keys);
}
	