package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.ContactGroupPuStockReadManage;
import com.egeo.components.stock.dao.read.ContactGroupPuStockReadDAO;
import com.egeo.components.stock.po.ContactGroupPuStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class ContactGroupPuStockReadManageImpl implements ContactGroupPuStockReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ContactGroupPuStockReadDAO contactGroupPuStockReadDAO;
	
	public ContactGroupPuStockPO findContactGroupPuStockById(ContactGroupPuStockPO po) {
		ContactGroupPuStockPO contactGroupPuStockpo = new ContactGroupPuStockPO();
		contactGroupPuStockpo.setId(po.getId());
		return contactGroupPuStockReadDAO.findById(contactGroupPuStockpo);
	}

	public PageResult<ContactGroupPuStockPO> findContactGroupPuStockOfPage(ContactGroupPuStockPO po, Pagination page) {
		
		PageResult<ContactGroupPuStockPO> pageResult = new PageResult<ContactGroupPuStockPO>();
		List<ContactGroupPuStockPO> list = null;

		int cnt = contactGroupPuStockReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = contactGroupPuStockReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<ContactGroupPuStockPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<ContactGroupPuStockPO> findContactGroupPuStockAll(ContactGroupPuStockPO po) {

		return contactGroupPuStockReadDAO.findAll(po,null);
	}
	
	/**
	 * 根据 PuID 查询共享库存puId组
	 * @return
	 */
	@Override
	public List<Long> findPuIdListByPuId(Long puid){
		return contactGroupPuStockReadDAO.findPuIdListByPuId(puid);
	}

	@Override
	public List<ContactGroupPuStockPO> findContactGroupPuStockBySkuId(Long contactGroupSkuId) {
		
		return contactGroupPuStockReadDAO.findContactGroupPuStockBySkuId(contactGroupSkuId);
	}
	
}
	