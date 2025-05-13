package com.egeo.components.product.manage.read;

import java.util.List;

import com.egeo.components.product.condition.CardStampsAdministrationCondition;
import com.egeo.components.product.po.CardStampsAdministrationPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CardStampsAdministrationReadManage {

	public CardStampsAdministrationPO findCardStampsAdministrationById(CardStampsAdministrationPO po);

	public PageResult<CardStampsAdministrationPO> findCardStampsAdministrationOfPage(CardStampsAdministrationPO po,Pagination page);

	public List<CardStampsAdministrationPO> findCardStampsAdministrationAll(CardStampsAdministrationPO po);
	/**
	 * 分页查询卡券模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	public PageResult<CardStampsAdministrationCondition> findPage(CardStampsAdministrationPO po, Pagination page);
}
	