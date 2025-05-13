package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.ContactGroupSkuStockReadManage;
import com.egeo.components.stock.dao.read.ContactGroupSkuStockReadDAO;
import com.egeo.components.stock.po.ContactGroupSkuStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ContactGroupSkuStockReadManageImpl implements ContactGroupSkuStockReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ContactGroupSkuStockReadDAO contactGroupSkuStockReadDAO;
	
	public ContactGroupSkuStockPO findContactGroupSkuStockById(ContactGroupSkuStockPO po) {
		ContactGroupSkuStockPO contactGroupSkuStockpo = new ContactGroupSkuStockPO();
		contactGroupSkuStockpo.setId(po.getId());
		return contactGroupSkuStockReadDAO.findById(contactGroupSkuStockpo);
	}

	public PageResult<ContactGroupSkuStockPO> findContactGroupSkuStockOfPage(ContactGroupSkuStockPO po, Pagination page) {
		
		PageResult<ContactGroupSkuStockPO> pageResult = new PageResult<ContactGroupSkuStockPO>();
		List<ContactGroupSkuStockPO> list = null;

		int cnt = contactGroupSkuStockReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = contactGroupSkuStockReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ContactGroupSkuStockPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ContactGroupSkuStockPO> findContactGroupSkuStockAll(ContactGroupSkuStockPO po) {

		return contactGroupSkuStockReadDAO.findAll(po,null);
	}

	@Override
	public List<ContactGroupSkuStockPO> findContactGroupSkuStockBySuId(Long suId) {
		return contactGroupSkuStockReadDAO.findBySuId(suId);
	}
	
}
	