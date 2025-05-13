package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.StorePuWarehouseStockReadManage;
import com.egeo.components.stock.dao.read.StorePuWarehouseStockReadDAO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StorePuWarehouseStockReadManageImpl implements StorePuWarehouseStockReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StorePuWarehouseStockReadDAO storePuWarehouseStockReadDAO;
	
	public StorePuWarehouseStockPO findStorePuWarehouseStockById(StorePuWarehouseStockPO po) {
		StorePuWarehouseStockPO storePuWarehouseStockpo = new StorePuWarehouseStockPO();
		storePuWarehouseStockpo.setId(po.getId());
		return storePuWarehouseStockReadDAO.findById(storePuWarehouseStockpo);
	}

	public PageResult<StorePuWarehouseStockPO> findStorePuWarehouseStockOfPage(StorePuWarehouseStockPO po, Pagination page) {
		
		PageResult<StorePuWarehouseStockPO> pageResult = new PageResult<StorePuWarehouseStockPO>();
		List<StorePuWarehouseStockPO> list = null;

		int cnt = storePuWarehouseStockReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = storePuWarehouseStockReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StorePuWarehouseStockPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StorePuWarehouseStockPO> findStorePuWarehouseStockAll(StorePuWarehouseStockPO po) {

		return storePuWarehouseStockReadDAO.findAll(po,null);
	}

	@Override
	public StorePuWarehouseStockPO findByStorePuId(Long storePuId) {
		return storePuWarehouseStockReadDAO.findByStorePuId(storePuId);
	}

	@Override
	public Integer findByStorePuSellOutSum(Long storeId, Long platformId) {
		return storePuWarehouseStockReadDAO.findByStorePuSellOutSum(storeId, platformId);
	}

	@Override
	public Integer findPuSellOutSumStoreByPuIds(List<Long> storePuIds) {
		return storePuWarehouseStockReadDAO.findPuSellOutSumStoreByPuIds(storePuIds);
	}
	
	/**
	 * 查询门店商品真实库存
	 * @param storeProductUnitId
	 * @return
	 */
	@Override
	public Long realStockNumByStoreProductUnitId(Long storeProductUnitId, Long storeId) {
		return storePuWarehouseStockReadDAO.realStockNumByStoreProductUnitId(storeProductUnitId, storeId);
	}
	
}
	