package com.egeo.components.stock.manage.read.impl;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.po.ResidueStockNumCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.manage.read.CommodityProductUnitWarehouseStockReadManage;
import com.egeo.components.stock.dao.read.CommodityProductUnitWarehouseStockReadDAO;
import com.egeo.components.stock.po.CommodityProductUnitWarehouseStockPO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Service
public class CommodityProductUnitWarehouseStockReadManageImpl implements CommodityProductUnitWarehouseStockReadManage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CommodityProductUnitWarehouseStockReadDAO commodityProductUnitWarehouseStockReadDAO;
	
	public CommodityProductUnitWarehouseStockPO findCommodityProductUnitWarehouseStockById(CommodityProductUnitWarehouseStockPO po) {
		CommodityProductUnitWarehouseStockPO commodityProductUnitWarehouseStockpo = new CommodityProductUnitWarehouseStockPO();
		commodityProductUnitWarehouseStockpo.setId(po.getId());
		return commodityProductUnitWarehouseStockReadDAO.findById(commodityProductUnitWarehouseStockpo);
	}

	public PageResult<CommodityProductUnitWarehouseStockPO> findCommodityProductUnitWarehouseStockOfPage(CommodityProductUnitWarehouseStockPO po, Pagination page) {
		
		PageResult<CommodityProductUnitWarehouseStockPO> pageResult = new PageResult<CommodityProductUnitWarehouseStockPO>();
		List<CommodityProductUnitWarehouseStockPO> list = null;

		int cnt = commodityProductUnitWarehouseStockReadDAO.countOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			list = commodityProductUnitWarehouseStockReadDAO.findOfPage(po, page);
		} else {
			list = new ArrayList<CommodityProductUnitWarehouseStockPO>();
		}
		pageResult.setList(list);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;

	}

	public List<CommodityProductUnitWarehouseStockPO> findCommodityProductUnitWarehouseStockAll(CommodityProductUnitWarehouseStockPO po) {

		return commodityProductUnitWarehouseStockReadDAO.findAll(po,null);
	}
	/**
	 * 根据skuid查询pu库存
	 */
	@Override
	public List<CommodityProductUnitWarehouseStockPO> findByPUId(List<Long> commodityProductUnitIds) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockReadDAO.findByPUId(commodityProductUnitIds);
	}
	/**
	 * 根据puid查询pu库存信息(真实库存-冻结库存)
	 * @param id
	 * @return
	 */
	@Override
	public Long realStockNumByCommodityProductUnitId(Long commodityProductUnitId) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockReadDAO.realStockNumByCommodityProductUnitId(commodityProductUnitId);
	}
	/**
	 * 根据puid查询pu真实库存信息
	 * @param commodityProductUnitId
	 * @return
	 */
	@Override
	public Long findByCommodityProductUnitId(Long commodityProductUnitId) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockReadDAO.findByCommodityProductUnitId(commodityProductUnitId);
	}
	/**
	 * 根据puid查询pu库存信息
	 * @param puId
	 * @return
	 */
	@Override
	public CommodityProductUnitWarehouseStockPO findCommodityProductUnitWarehouseStockByPuId(Long puId) {
		return commodityProductUnitWarehouseStockReadDAO.findCommodityProductUnitWarehouseStockByPuId(puId);
	}
	/**
	 * 根据suId查询剩余库存数量
	 * @param standardUnitId
	 * @return
	 */
	@Override
	public Long residueStockNumByStandardUnitId(Long standardUnitId) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockReadDAO.residueStockNumByStandardUnitId(standardUnitId);
	}

	@Override
	public List<ResidueStockNumCondition> residueStockNumByStandardUnitIds(List<Long> standardUnitIds) {
		return commodityProductUnitWarehouseStockReadDAO.residueStockNumByStandardUnitIds(standardUnitIds);
	}

	/**
	 * 根据puId集合查询剩余库存数量
	 * @param skuId
	 * @return
	 */
	@Override
	public Long residueStockNumByPuId(List<Long> commodityProductUnitIds) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockReadDAO.residueStockNumByPuId(commodityProductUnitIds);
	}

	@Override
	public List<Long> findIsSkuIdsBySkuIds(List<Long> skuIds) {
		// TODO Auto-generated method stub
		return commodityProductUnitWarehouseStockReadDAO.findIsSkuIdsBySkuIds(skuIds);
	}

	@Override
	public Integer findPuSellOutSumByPuIds(List<Long> puIds) {
		return commodityProductUnitWarehouseStockReadDAO.findPuSellOutSumByPuIds(puIds);
	}
	
}
	