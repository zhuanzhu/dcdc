package com.egeo.components.config.manage.read;

import java.util.List;

import com.egeo.components.config.po.CardSaltPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface CardSaltReadManage {

	public CardSaltPO findCardSaltById(CardSaltPO po);

	public PageResult<CardSaltPO> findCardSaltOfPage(CardSaltPO po,Pagination page);

	public List<CardSaltPO> findCardSaltAll(CardSaltPO po);

	/**
	 * 根据uuid查询卡券盐
	 * @param uuid
	 * @return
	 */
	public CardSaltPO queryCardSaltByUUID(String uuid);
}
	