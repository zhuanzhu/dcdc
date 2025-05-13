package com.egeo.components.product.manage.read.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.manage.read.CommodityProductUnitCompareReadManage;
import com.egeo.components.product.dao.read.CommodityProductUnitCompareReadDAO;
import com.egeo.components.product.po.CommodityProductUnitComparePO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CommodityProductUnitCompareReadManageImpl implements CommodityProductUnitCompareReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitCompareReadDAO commodityProductUnitCompareReadDAO;
	
	public CommodityProductUnitComparePO findCommodityProductUnitCompareById(CommodityProductUnitComparePO po) {
		CommodityProductUnitComparePO commodityProductUnitComparepo = new CommodityProductUnitComparePO();
		commodityProductUnitComparepo.setId(po.getId());
		return commodityProductUnitCompareReadDAO.findById(commodityProductUnitComparepo);
	}

	public PageResult<CommodityProductUnitComparePO> findCommodityProductUnitCompareOfPage(CommodityProductUnitComparePO po, Pagination page) {
		
		PageResult<CommodityProductUnitComparePO> pageResult = new PageResult<CommodityProductUnitComparePO>();
		List<CommodityProductUnitComparePO> list = null;

		int cnt = commodityProductUnitCompareReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = commodityProductUnitCompareReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CommodityProductUnitComparePO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CommodityProductUnitComparePO> findCommodityProductUnitCompareAll(CommodityProductUnitComparePO po) {

		return commodityProductUnitCompareReadDAO.findAll(po,null);
	}
	
}
	