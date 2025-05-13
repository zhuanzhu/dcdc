package com.egeo.components.promotion.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.promotion.manage.read.CardBatchReadManage;
import com.egeo.components.promotion.dao.read.CardBatchReadDAO;
import com.egeo.components.promotion.po.CardBatchPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CardBatchReadManageImpl implements CardBatchReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CardBatchReadDAO cardBatchReadDAO;
	
	public CardBatchPO findCardBatchById(CardBatchPO po) {
		CardBatchPO cardBatchpo = new CardBatchPO();
		cardBatchpo.setId(po.getId());
		return cardBatchReadDAO.findById(cardBatchpo);
	}

	public PageResult<CardBatchPO> findCardBatchOfPage(CardBatchPO po, Pagination page) {
		
		PageResult<CardBatchPO> pageResult = new PageResult<CardBatchPO>();
		List<CardBatchPO> list = null;

		int cnt = cardBatchReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cardBatchReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CardBatchPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CardBatchPO> findCardBatchAll(CardBatchPO po) {

		return cardBatchReadDAO.findAll(po,null);
	}
	
}
	