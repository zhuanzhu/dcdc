package com.egeo.components.promotion.manage.read;

import com.egeo.components.promotion.po.CardUseRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

import java.util.List;

public interface CardUseRecordReadManage {

	public CardUseRecordPO findCardUseRecordById(CardUseRecordPO po);

	public PageResult<CardUseRecordPO> findCardUseRecordOfPage(CardUseRecordPO po,Pagination page);

	public List<CardUseRecordPO> findCardUseRecordAll(CardUseRecordPO po);
}
