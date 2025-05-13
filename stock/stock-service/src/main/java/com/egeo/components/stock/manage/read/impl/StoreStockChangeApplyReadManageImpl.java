package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.StoreStockChangeApplyReadManage;
import com.egeo.components.stock.dao.read.StoreStockChangeApplyReadDAO;
import com.egeo.components.stock.po.StoreStockChangeApplyPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StoreStockChangeApplyReadManageImpl implements StoreStockChangeApplyReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreStockChangeApplyReadDAO storeStockChangeApplyReadDAO;
	
	public StoreStockChangeApplyPO findStoreStockChangeApplyById(StoreStockChangeApplyPO po) {
		StoreStockChangeApplyPO storeStockChangeApplypo = new StoreStockChangeApplyPO();
		storeStockChangeApplypo.setId(po.getId());
		return storeStockChangeApplyReadDAO.findById(storeStockChangeApplypo);
	}

	public PageResult<StoreStockChangeApplyPO> findStoreStockChangeApplyOfPage(StoreStockChangeApplyPO po, Pagination page) {
		
		PageResult<StoreStockChangeApplyPO> pageResult = new PageResult<StoreStockChangeApplyPO>();
		List<StoreStockChangeApplyPO> list = null;

		int cnt = storeStockChangeApplyReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeStockChangeApplyReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StoreStockChangeApplyPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StoreStockChangeApplyPO> findStoreStockChangeApplyAll(StoreStockChangeApplyPO po) {

		return storeStockChangeApplyReadDAO.findAll(po,null);
	}
	
}
	