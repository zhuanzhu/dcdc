package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.CommodityProductUnitVirtualStockReadManage;
import com.egeo.components.stock.dao.read.CommodityProductUnitVirtualStockReadDAO;
import com.egeo.components.stock.po.CommodityProductUnitVirtualStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CommodityProductUnitVirtualStockReadManageImpl implements CommodityProductUnitVirtualStockReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitVirtualStockReadDAO commodityProductUnitVirtualStockReadDAO;
	
	public CommodityProductUnitVirtualStockPO findCommodityProductUnitVirtualStockById(CommodityProductUnitVirtualStockPO po) {
		CommodityProductUnitVirtualStockPO commodityProductUnitVirtualStockpo = new CommodityProductUnitVirtualStockPO();
		commodityProductUnitVirtualStockpo.setId(po.getId());
		return commodityProductUnitVirtualStockReadDAO.findById(commodityProductUnitVirtualStockpo);
	}

	public PageResult<CommodityProductUnitVirtualStockPO> findCommodityProductUnitVirtualStockOfPage(CommodityProductUnitVirtualStockPO po, Pagination page) {
		
		PageResult<CommodityProductUnitVirtualStockPO> pageResult = new PageResult<CommodityProductUnitVirtualStockPO>();
		List<CommodityProductUnitVirtualStockPO> list = null;

		int cnt = commodityProductUnitVirtualStockReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = commodityProductUnitVirtualStockReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CommodityProductUnitVirtualStockPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CommodityProductUnitVirtualStockPO> findCommodityProductUnitVirtualStockAll(CommodityProductUnitVirtualStockPO po) {

		return commodityProductUnitVirtualStockReadDAO.findAll(po,null);
	}
	
}
	