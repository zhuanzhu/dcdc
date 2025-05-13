package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StoreTreeReadManage;
import com.egeo.components.product.dao.read.StoreTreeReadDAO;
import com.egeo.components.product.po.StoreTreePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StoreTreeReadManageImpl implements StoreTreeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreTreeReadDAO storeTreeReadDAO;
	
	public StoreTreePO findStoreTreeById(StoreTreePO po) {
		StoreTreePO storeTreepo = new StoreTreePO();
		storeTreepo.setId(po.getId());
		return storeTreeReadDAO.findById(storeTreepo);
	}

	public PageResult<StoreTreePO> findStoreTreeOfPage(StoreTreePO po, Pagination page) {
		
		PageResult<StoreTreePO> pageResult = new PageResult<StoreTreePO>();
		List<StoreTreePO> list = null;

		int cnt = storeTreeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeTreeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StoreTreePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StoreTreePO> findStoreTreeAll(StoreTreePO po) {

		return storeTreeReadDAO.findAll(po,null);
	}
	
}
	