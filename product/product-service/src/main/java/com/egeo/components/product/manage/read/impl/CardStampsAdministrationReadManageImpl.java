package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CardStampsAdministrationReadManage;
import com.egeo.components.product.condition.CardStampsAdministrationCondition;
import com.egeo.components.product.dao.read.CardStampsAdministrationReadDAO;
import com.egeo.components.product.po.CardStampsAdministrationPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CardStampsAdministrationReadManageImpl implements CardStampsAdministrationReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CardStampsAdministrationReadDAO cardStampsAdministrationReadDAO;
	
	public CardStampsAdministrationPO findCardStampsAdministrationById(CardStampsAdministrationPO po) {
		CardStampsAdministrationPO cardStampsAdministrationpo = new CardStampsAdministrationPO();
		cardStampsAdministrationpo.setId(po.getId());
		return cardStampsAdministrationReadDAO.findById(cardStampsAdministrationpo);
	}

	public PageResult<CardStampsAdministrationPO> findCardStampsAdministrationOfPage(CardStampsAdministrationPO po, Pagination page) {
		
		PageResult<CardStampsAdministrationPO> pageResult = new PageResult<CardStampsAdministrationPO>();
		List<CardStampsAdministrationPO> list = null;

		int cnt = cardStampsAdministrationReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cardStampsAdministrationReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CardStampsAdministrationPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CardStampsAdministrationPO> findCardStampsAdministrationAll(CardStampsAdministrationPO po) {

		return cardStampsAdministrationReadDAO.findAll(po,null);
	}
	/**
	 * 分页查询卡券模版信息
	 * @param vo
	 * @param page
	 * @param req
	 * @return
	 */
	@Override
	public PageResult<CardStampsAdministrationCondition> findPage(CardStampsAdministrationPO po, Pagination page) {
		PageResult<CardStampsAdministrationCondition> pageResult = new PageResult<CardStampsAdministrationCondition>();
		List<CardStampsAdministrationCondition> list = null;

		int cnt = cardStampsAdministrationReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cardStampsAdministrationReadDAO.findCardStampsAdministrationConditionOfPage(po, page);
		} else {
			list = new ArrayList<CardStampsAdministrationCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
}
	