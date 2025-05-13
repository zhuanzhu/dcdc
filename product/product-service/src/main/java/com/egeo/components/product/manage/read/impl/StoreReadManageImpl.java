package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StoreReadManage;
import com.egeo.components.product.condition.StoreCondition;
import com.egeo.components.product.dao.read.StoreReadDAO;
import com.egeo.components.product.po.StorePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StoreReadManageImpl implements StoreReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreReadDAO storeReadDAO;
	
	public StoreCondition findStoreById(StorePO po) {
		StorePO storepo = new StorePO();
		storepo.setId(po.getId());
		return storeReadDAO.findStoreById(storepo);
	}

	public PageResult<StorePO> findStoreOfPage(StorePO po, Pagination page) {
		
		PageResult<StorePO> pageResult = new PageResult<StorePO>();
		List<StorePO> list = null;

		int cnt = storeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StorePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StorePO> findStoreAll(StorePO po) {

		return storeReadDAO.findAll(po,null);
	}

	@Override
	public List<StoreCondition> findRootStoreAll(StorePO po) {
		return storeReadDAO.findRootStoreAll(po);
	}

	@Override
	public PageResult<StoreCondition> findRootStoreOfPage(StorePO po, Pagination page) {
		PageResult<StoreCondition> pageResult = new PageResult<StoreCondition>();
		List<StoreCondition> list = null;

		int cnt = storeReadDAO.countRootStoreOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeReadDAO.findRootStoreOfPage(po, page);
		} else {
			list = new ArrayList<StoreCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public List<StoreCondition> findStoreAllByTreeId(Long storeTreeId) {
		return storeReadDAO.findStoreAllByTreeId(storeTreeId);
	}

	@Override
	public StorePO findHeadStoreByStoreId(Long storeId) {
		return storeReadDAO.findHeadStoreByStoreId(storeId);
	}

	@Override
	public StoreCondition findStoreByNodeId(Long nodeId) {

		return storeReadDAO.findStoreByNodeId(nodeId);
	}

	@Override
	public List<Long> findStoreByName(String storeName) {
		return storeReadDAO.findStoreByName(storeName);
	}

	@Override
	public List<StorePO> findStoreByPlatformIdAndStoreMenu(StorePO storePO) {
		return storeReadDAO.findStoreByPlatformIdAndStoreMenu(storePO);
	}

}
	