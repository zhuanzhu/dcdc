package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.CommodityProductUnitStockRunningWaterReadManage;
import com.egeo.components.stock.condition.CommodityProductUnitStockRunningWaterCondition;
import com.egeo.components.stock.dao.read.CommodityProductUnitStockRunningWaterReadDAO;
import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CommodityProductUnitStockRunningWaterReadManageImpl implements CommodityProductUnitStockRunningWaterReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitStockRunningWaterReadDAO commodityProductUnitStockRunningWaterReadDAO;
	
	public CommodityProductUnitStockRunningWaterPO findCommodityProductUnitStockRunningWaterById(CommodityProductUnitStockRunningWaterPO po) {
		CommodityProductUnitStockRunningWaterPO commodityProductUnitStockRunningWaterpo = new CommodityProductUnitStockRunningWaterPO();
		commodityProductUnitStockRunningWaterpo.setId(po.getId());
		return commodityProductUnitStockRunningWaterReadDAO.findById(commodityProductUnitStockRunningWaterpo);
	}

	public PageResult<CommodityProductUnitStockRunningWaterPO> findCommodityProductUnitStockRunningWaterOfPage(CommodityProductUnitStockRunningWaterPO po, Pagination page) {
		
		PageResult<CommodityProductUnitStockRunningWaterPO> pageResult = new PageResult<CommodityProductUnitStockRunningWaterPO>();
		List<CommodityProductUnitStockRunningWaterPO> list = null;

		int cnt = commodityProductUnitStockRunningWaterReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = commodityProductUnitStockRunningWaterReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CommodityProductUnitStockRunningWaterPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}
	
	public PageResult<CommodityProductUnitStockRunningWaterCondition> findConditionOfPage(CommodityProductUnitStockRunningWaterPO po, Pagination page) {
		
		PageResult<CommodityProductUnitStockRunningWaterCondition> pageResult = new PageResult<CommodityProductUnitStockRunningWaterCondition>();
		List<CommodityProductUnitStockRunningWaterCondition> list = null;

		int cnt = commodityProductUnitStockRunningWaterReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = commodityProductUnitStockRunningWaterReadDAO.findConditionOfPage(po, page);
		} else {
			list = new ArrayList<CommodityProductUnitStockRunningWaterCondition>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CommodityProductUnitStockRunningWaterPO> findCommodityProductUnitStockRunningWaterAll(CommodityProductUnitStockRunningWaterPO po) {

		return commodityProductUnitStockRunningWaterReadDAO.findAll(po,null);
	}
	
	public List<CommodityProductUnitStockRunningWaterPO> findCommodityProductUnitStockRunningWaterByOrderCodes(List<String> orderCodes) {

		return commodityProductUnitStockRunningWaterReadDAO.findAllByOrderCodes(orderCodes);
	}

	@Override
	public List<CommodityProductUnitStockRunningWaterPO> findCommodityProductUnitStockRunningWaterByOrderCodes(
			List<String> orderCodes, Integer status) {
		
		return commodityProductUnitStockRunningWaterReadDAO.findAllByOrderCodesAndType(orderCodes,status);
	}
	
}
	