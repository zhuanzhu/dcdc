package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StoreMenuNodeReadManage;
import com.egeo.components.product.dao.read.StoreMenuNodeReadDAO;
import com.egeo.components.product.po.StoreMenuNodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StoreMenuNodeReadManageImpl implements StoreMenuNodeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreMenuNodeReadDAO storeMenuNodeReadDAO;
	
	public StoreMenuNodePO findStoreMenuNodeById(StoreMenuNodePO po) {
		StoreMenuNodePO storeMenuNodepo = new StoreMenuNodePO();
		storeMenuNodepo.setId(po.getId());
		return storeMenuNodeReadDAO.findById(storeMenuNodepo);
	}

	public PageResult<StoreMenuNodePO> findStoreMenuNodeOfPage(StoreMenuNodePO po, Pagination page) {
		
		PageResult<StoreMenuNodePO> pageResult = new PageResult<StoreMenuNodePO>();
		List<StoreMenuNodePO> list = null;

		int cnt = storeMenuNodeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeMenuNodeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StoreMenuNodePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StoreMenuNodePO> findStoreMenuNodeAll(StoreMenuNodePO po) {

		return storeMenuNodeReadDAO.findAll(po,null);
	}
	
}
	