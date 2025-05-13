package com.egeo.components.promotion.manage.read;

import java.util.List;
	
import com.egeo.components.promotion.po.CardBatchPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CardBatchReadManage {

	public CardBatchPO findCardBatchById(CardBatchPO po);

	public PageResult<CardBatchPO> findCardBatchOfPage(CardBatchPO po,Pagination page);

	public List<CardBatchPO> findCardBatchAll(CardBatchPO po);
}
	