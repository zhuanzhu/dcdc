package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StoreMenuTreeReadManage;
import com.egeo.components.product.dao.read.StoreMenuTreeReadDAO;
import com.egeo.components.product.po.StoreMenuTreePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StoreMenuTreeReadManageImpl implements StoreMenuTreeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreMenuTreeReadDAO storeMenuTreeReadDAO;
	
	public StoreMenuTreePO findStoreMenuTreeById(StoreMenuTreePO po) {
		StoreMenuTreePO storeMenuTreepo = new StoreMenuTreePO();
		storeMenuTreepo.setId(po.getId());
		return storeMenuTreeReadDAO.findById(storeMenuTreepo);
	}

	public PageResult<StoreMenuTreePO> findStoreMenuTreeOfPage(StoreMenuTreePO po, Pagination page) {
		
		PageResult<StoreMenuTreePO> pageResult = new PageResult<StoreMenuTreePO>();
		List<StoreMenuTreePO> list = null;

		int cnt = storeMenuTreeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeMenuTreeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StoreMenuTreePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StoreMenuTreePO> findStoreMenuTreeAll(StoreMenuTreePO po) {

		return storeMenuTreeReadDAO.findAll(po,null);
	}
	
}
	