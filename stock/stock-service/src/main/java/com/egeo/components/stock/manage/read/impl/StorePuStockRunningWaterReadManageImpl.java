package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.StorePuStockRunningWaterReadManage;
import com.egeo.components.stock.condition.StorePuStockRunningWaterCondition;
import com.egeo.components.stock.dao.read.StorePuStockRunningWaterReadDAO;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StorePuStockRunningWaterReadManageImpl implements StorePuStockRunningWaterReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StorePuStockRunningWaterReadDAO storePuStockRunningWaterReadDAO;
	
	public StorePuStockRunningWaterPO findStorePuStockRunningWaterById(StorePuStockRunningWaterPO po) {
		StorePuStockRunningWaterPO storePuStockRunningWaterpo = new StorePuStockRunningWaterPO();
		storePuStockRunningWaterpo.setId(po.getId());
		return storePuStockRunningWaterReadDAO.findById(storePuStockRunningWaterpo);
	}

	public PageResult<StorePuStockRunningWaterCondition> findStorePuStockRunningWaterOfPage(StorePuStockRunningWaterPO po, Pagination page) {
		
		PageResult<StorePuStockRunningWaterCondition> pageResult = new PageResult<StorePuStockRunningWaterCondition>();
		List<StorePuStockRunningWaterCondition> list = null;

		int cnt = storePuStockRunningWaterReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storePuStockRunningWaterReadDAO.findStorePuStockRunningWaterConditionOfPage(po, page);
		} else {
			list = new ArrayList<StorePuStockRunningWaterCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StorePuStockRunningWaterPO> findStorePuStockRunningWaterAll(StorePuStockRunningWaterPO po) {

		return storePuStockRunningWaterReadDAO.findAll(po,null);
	}
	
}
	