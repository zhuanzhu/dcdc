package com.egeo.components.promotion.manage.read.impl;

import com.egeo.components.promotion.dao.read.CardUseRecordReadDAO;
import com.egeo.components.promotion.manage.read.CardUseRecordReadManage;
import com.egeo.components.promotion.po.CardUseRecordPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CardUseRecordReadManageImpl implements CardUseRecordReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CardUseRecordReadDAO cardUseRecordReadDAO;

	@Override
	public CardUseRecordPO findCardUseRecordById(CardUseRecordPO po) {
		CardUseRecordPO CardUseRecordpo = new CardUseRecordPO();
		CardUseRecordpo.setId(po.getId());
		return cardUseRecordReadDAO.findById(CardUseRecordpo);
	}

	@Override
	public PageResult<CardUseRecordPO> findCardUseRecordOfPage(CardUseRecordPO po, Pagination page) {

		PageResult<CardUseRecordPO> pageResult = new PageResult<>();
		List<CardUseRecordPO> list = null;

		int cnt = cardUseRecordReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cardUseRecordReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	@Override
	public List<CardUseRecordPO> findCardUseRecordAll(CardUseRecordPO po) {

		return cardUseRecordReadDAO.findAll(po,null);
	}

}
