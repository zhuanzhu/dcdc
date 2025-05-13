package com.egeo.components.stock.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.business.CommodityProductUnitVirtualStockManage;
import com.egeo.components.stock.facade.CommodityProductUnitVirtualStockFacade;
import com.egeo.components.stock.dto.CommodityProductUnitVirtualStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("commodityProductUnitVirtualStock")
public class CommodityProductUnitVirtualStockManageImpl implements CommodityProductUnitVirtualStockManage{

	
	@Resource(name="commodityProductUnitVirtualStockFacade")
	private CommodityProductUnitVirtualStockFacade commodityProductUnitVirtualStockFacade;

	@Override
	public CommodityProductUnitVirtualStockDTO findCommodityProductUnitVirtualStockById(CommodityProductUnitVirtualStockDTO dto) {
		return commodityProductUnitVirtualStockFacade.findCommodityProductUnitVirtualStockById(dto);
	}

	@Override
	public PageResult<CommodityProductUnitVirtualStockDTO> findCommodityProductUnitVirtualStockOfPage(CommodityProductUnitVirtualStockDTO dto, Pagination page) {
		return commodityProductUnitVirtualStockFacade.findCommodityProductUnitVirtualStockOfPage(dto, page);
	}

	@Override
	public List<CommodityProductUnitVirtualStockDTO> findCommodityProductUnitVirtualStockAll(CommodityProductUnitVirtualStockDTO dto) {
		return commodityProductUnitVirtualStockFacade.findCommodityProductUnitVirtualStockAll(dto);
	}

	@Override
	public Long insertCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto) {
		return commodityProductUnitVirtualStockFacade.insertCommodityProductUnitVirtualStockWithTx(dto);
	}

	@Override
	public int updateCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto) {
		return commodityProductUnitVirtualStockFacade.updateCommodityProductUnitVirtualStockWithTx(dto);
	}

	@Override
	public int deleteCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto) {
		return commodityProductUnitVirtualStockFacade.deleteCommodityProductUnitVirtualStockWithTx(dto);
	}


}
	