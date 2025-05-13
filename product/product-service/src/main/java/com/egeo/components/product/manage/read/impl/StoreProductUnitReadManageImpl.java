package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StoreProductUnitReadManage;
import com.egeo.components.product.dao.read.StoreProductUnitReadDAO;
import com.egeo.components.product.po.StoreProductUnitPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StoreProductUnitReadManageImpl implements StoreProductUnitReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreProductUnitReadDAO storeProductUnitReadDAO;
	
	public StoreProductUnitPO findStoreProductUnitById(StoreProductUnitPO po) {
		StoreProductUnitPO storeProductUnitpo = new StoreProductUnitPO();
		storeProductUnitpo.setId(po.getId());
		return storeProductUnitReadDAO.findById(storeProductUnitpo);
	}

	public PageResult<StoreProductUnitPO> findStoreProductUnitOfPage(StoreProductUnitPO po, Pagination page) {
		
		PageResult<StoreProductUnitPO> pageResult = new PageResult<StoreProductUnitPO>();
		List<StoreProductUnitPO> list = null;

		int cnt = storeProductUnitReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeProductUnitReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StoreProductUnitPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StoreProductUnitPO> findStoreProductUnitAll(StoreProductUnitPO po) {

		return storeProductUnitReadDAO.findAll(po,null);
	}

	@Override
	public List<Long> findStorePuIdsByStoreId(Long storeId, Long platformId) {
		return storeProductUnitReadDAO.findStorePuIdsByStoreId(storeId, platformId);
	}
	
}
	