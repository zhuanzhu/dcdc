package com.egeo.components.config.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.dao.read.CardSaltReadDAO;
import com.egeo.components.config.manage.read.CardSaltReadManage;
import com.egeo.components.config.po.CardSaltPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CardSaltReadManageImpl implements CardSaltReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CardSaltReadDAO cardSaltReadDAO;
	
	public CardSaltPO findCardSaltById(CardSaltPO po) {
		CardSaltPO cardSaltpo = new CardSaltPO();
		cardSaltpo.setId(po.getId());
		return cardSaltReadDAO.findById(cardSaltpo);
	}

	public PageResult<CardSaltPO> findCardSaltOfPage(CardSaltPO po, Pagination page) {
		
		PageResult<CardSaltPO> pageResult = new PageResult<CardSaltPO>();
		List<CardSaltPO> list = null;

		int cnt = cardSaltReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cardSaltReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CardSaltPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CardSaltPO> findCardSaltAll(CardSaltPO po) {

		return cardSaltReadDAO.findAll(po,null);
	}

	@Override
	public CardSaltPO queryCardSaltByUUID(String uuid) {
		
		return cardSaltReadDAO.queryCardSaltByUUID(uuid);
	}
	
}
	