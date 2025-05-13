package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.StoreTreeNodeReadManage;
import com.egeo.components.product.condition.StoreTreeNodeCondition;
import com.egeo.components.product.dao.read.StoreTreeNodeReadDAO;
import com.egeo.components.product.po.StoreTreeNodePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StoreTreeNodeReadManageImpl implements StoreTreeNodeReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StoreTreeNodeReadDAO storeTreeNodeReadDAO;
	
	public StoreTreeNodePO findStoreTreeNodeById(StoreTreeNodePO po) {
		StoreTreeNodePO storeTreeNodepo = new StoreTreeNodePO();
		storeTreeNodepo.setId(po.getId());
		return storeTreeNodeReadDAO.findById(storeTreeNodepo);
	}

	public PageResult<StoreTreeNodePO> findStoreTreeNodeOfPage(StoreTreeNodePO po, Pagination page) {
		
		PageResult<StoreTreeNodePO> pageResult = new PageResult<StoreTreeNodePO>();
		List<StoreTreeNodePO> list = null;

		int cnt = storeTreeNodeReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storeTreeNodeReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StoreTreeNodePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StoreTreeNodePO> findStoreTreeNodeAll(StoreTreeNodePO po) {

		return storeTreeNodeReadDAO.findAll(po,null);
	}

	@Override
	public List<Long> findStoreTreeNodeAllByPlatformId(Long platformId) {
		return storeTreeNodeReadDAO.findStoreTreeNodeAllByPlatformId(platformId);
	}

	@Override
	public List<String> findByPids(String pids) {
		return storeTreeNodeReadDAO.findByPids(pids);
	}

	@Override
	public boolean findHeadStoreByStoreId(Long storeId) {
		Long parentId = storeTreeNodeReadDAO.findHeadStoreByStoreId(storeId);
		return parentId.equals(0L);
	}
	
	@Override
	public List<StoreTreeNodeCondition> findByStoreIdAndName(List<Long> storeIds, String name, Long platformId) {
		return storeTreeNodeReadDAO.findByStoreIdAndName(storeIds, name, platformId);
	}
	
}
	