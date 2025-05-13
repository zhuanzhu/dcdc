package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.StockDictReadManage;
import com.egeo.components.stock.dao.read.StockDictReadDAO;
import com.egeo.components.stock.po.StockDictPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class StockDictReadManageImpl implements StockDictReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StockDictReadDAO stockDictReadDAO;
	
	public StockDictPO findStockDictById(StockDictPO po) {
		StockDictPO stockDictpo = new StockDictPO();
		stockDictpo.setId(po.getId());
		return stockDictReadDAO.findById(stockDictpo);
	}

	public PageResult<StockDictPO> findStockDictOfPage(StockDictPO po, Pagination page) {
		
		PageResult<StockDictPO> pageResult = new PageResult<StockDictPO>();
		List<StockDictPO> list = null;

		int cnt = stockDictReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = stockDictReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<StockDictPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<StockDictPO> findStockDictAll(StockDictPO po) {

		return stockDictReadDAO.findAll(po,null);
	}
	
}
	