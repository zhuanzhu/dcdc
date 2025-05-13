package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.ContactGroupStockReadManage;
import com.egeo.components.stock.dao.read.ContactGroupStockReadDAO;
import com.egeo.components.stock.po.ContactGroupStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ContactGroupStockReadManageImpl implements ContactGroupStockReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ContactGroupStockReadDAO contactGroupStockReadDAO;
	
	public ContactGroupStockPO findContactGroupStockById(ContactGroupStockPO po) {
		ContactGroupStockPO contactGroupStockpo = new ContactGroupStockPO();
		contactGroupStockpo.setId(po.getId());
		return contactGroupStockReadDAO.findById(contactGroupStockpo);
	}

	public PageResult<ContactGroupStockPO> findContactGroupStockOfPage(ContactGroupStockPO po, Pagination page) {
		
		PageResult<ContactGroupStockPO> pageResult = new PageResult<ContactGroupStockPO>();
		List<ContactGroupStockPO> list = null;

		int cnt = contactGroupStockReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = contactGroupStockReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ContactGroupStockPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ContactGroupStockPO> findContactGroupStockAll(ContactGroupStockPO po) {

		return contactGroupStockReadDAO.findAll(po,null);
	}

	@Override
	public PageResult<ContactGroupStockPO> findContactGroupStockMapOfPage(ContactGroupStockPO po, Pagination page, List<Long> spuIds, List<Long> merchantIds) {
		PageResult<ContactGroupStockPO> pageResult = new PageResult<ContactGroupStockPO>();
		List<ContactGroupStockPO> list = null;

		int cnt = contactGroupStockReadDAO.countMapOfPage(po, spuIds, merchantIds);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = contactGroupStockReadDAO.findMapOfPage(po, page, spuIds, merchantIds);
		} else {
			list = new ArrayList<ContactGroupStockPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public ContactGroupStockPO findContactGroupStockByMerchantIdAndSuId(ContactGroupStockPO po, Long suId) {
		List<ContactGroupStockPO> list = contactGroupStockReadDAO.findContactGroupStockByMerchantIdAndSuId(po, suId);
		return EmptyUtil.isEmpty(list) ? new ContactGroupStockPO() : list.get(0) ;
	}
	@Override
	public ContactGroupStockPO findContactGroupStockByPuId(Long commodityProductUnitId) {
		
		return contactGroupStockReadDAO.findByPuId(commodityProductUnitId);
	}

	@Override
	public List<ContactGroupStockPO> findAllByName(ContactGroupStockPO po) {
		return contactGroupStockReadDAO.findAllByName(po,null);
	}
}
	