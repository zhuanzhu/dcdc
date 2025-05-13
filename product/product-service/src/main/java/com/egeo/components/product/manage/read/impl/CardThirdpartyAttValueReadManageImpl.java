package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CardThirdpartyAttValueReadManage;
import com.egeo.components.product.dao.read.CardThirdpartyAttValueReadDAO;
import com.egeo.components.product.po.CardThirdpartyAttValuePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CardThirdpartyAttValueReadManageImpl implements CardThirdpartyAttValueReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CardThirdpartyAttValueReadDAO cardThirdpartyAttValueReadDAO;
	
	public CardThirdpartyAttValuePO findCardThirdpartyAttValueById(CardThirdpartyAttValuePO po) {
		CardThirdpartyAttValuePO cardThirdpartyAttValuepo = new CardThirdpartyAttValuePO();
		cardThirdpartyAttValuepo.setId(po.getId());
		return cardThirdpartyAttValueReadDAO.findById(cardThirdpartyAttValuepo);
	}

	public PageResult<CardThirdpartyAttValuePO> findCardThirdpartyAttValueOfPage(CardThirdpartyAttValuePO po, Pagination page) {
		
		PageResult<CardThirdpartyAttValuePO> pageResult = new PageResult<CardThirdpartyAttValuePO>();
		List<CardThirdpartyAttValuePO> list = null;

		int cnt = cardThirdpartyAttValueReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = cardThirdpartyAttValueReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CardThirdpartyAttValuePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CardThirdpartyAttValuePO> findCardThirdpartyAttValueAll(CardThirdpartyAttValuePO po) {

		return cardThirdpartyAttValueReadDAO.findAll(po,null);
	}

	@Override
	public Integer findCardTypeByStandardProductUnitId(Long standardProductUnitId) {
		return cardThirdpartyAttValueReadDAO.findCardTypeByStandardProductUnitId(standardProductUnitId);
	}
	
}
	