package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StoreMenuNodeStandardUnitReadManage;
import com.egeo.components.product.dao.read.StoreMenuNodeStandardUnitReadDAO;
import com.egeo.components.product.po.StoreMenuNodeStandardUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StoreMenuNodeStandardUnitReadManageImpl implements StoreMenuNodeStandardUnitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreMenuNodeStandardUnitReadDAO storeMenuNodeStandardUnitReadDAO;
	
	public StoreMenuNodeStandardUnitPO findStoreMenuNodeStandardUnitById(StoreMenuNodeStandardUnitPO po) {
		StoreMenuNodeStandardUnitPO storeMenuNodeStandardUnitpo = new StoreMenuNodeStandardUnitPO();
		storeMenuNodeStandardUnitpo.setId(po.getId());
		return storeMenuNodeStandardUnitReadDAO.findById(storeMenuNodeStandardUnitpo);
	}

	public PageResult<StoreMenuNodeStandardUnitPO> findStoreMenuNodeStandardUnitOfPage(StoreMenuNodeStandardUnitPO po, Pagination page) {
		
		PageResult<StoreMenuNodeStandardUnitPO> pageResult = new PageResult<StoreMenuNodeStandardUnitPO>();
		List<StoreMenuNodeStandardUnitPO> list = null;

		int cnt = storeMenuNodeStandardUnitReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeMenuNodeStandardUnitReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StoreMenuNodeStandardUnitPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StoreMenuNodeStandardUnitPO> findStoreMenuNodeStandardUnitAll(StoreMenuNodeStandardUnitPO po) {

		return storeMenuNodeStandardUnitReadDAO.findAll(po,null);
	}
	
}
	