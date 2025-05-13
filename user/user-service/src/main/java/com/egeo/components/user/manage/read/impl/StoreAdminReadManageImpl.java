package com.egeo.components.user.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.manage.read.StoreAdminReadManage;
import com.egeo.components.user.dao.read.StoreAdminReadDAO;
import com.egeo.components.user.po.StoreAdminPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StoreAdminReadManageImpl implements StoreAdminReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreAdminReadDAO storeAdminReadDAO;
	
	public StoreAdminPO findStoreAdminById(StoreAdminPO po) {
		StoreAdminPO storeAdminpo = new StoreAdminPO();
		storeAdminpo.setId(po.getId());
		return storeAdminReadDAO.findById(storeAdminpo);
	}

	public PageResult<StoreAdminPO> findStoreAdminOfPage(StoreAdminPO po, Pagination page) {
		
		PageResult<StoreAdminPO> pageResult = new PageResult<StoreAdminPO>();
		List<StoreAdminPO> list = null;

		int cnt = storeAdminReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeAdminReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StoreAdminPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StoreAdminPO> findStoreAdminAll(StoreAdminPO po) {

		return storeAdminReadDAO.findAll(po,null);
	}
	
}
	