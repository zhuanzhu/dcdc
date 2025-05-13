package com.egeo.components.stock.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.CommodityProductUnitVirtualStockReadService;
import com.egeo.components.stock.service.write.CommodityProductUnitVirtualStockWriteService;
import com.egeo.components.stock.dto.CommodityProductUnitVirtualStockDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CommodityProductUnitVirtualStockFacade {
	
	@Resource
	private CommodityProductUnitVirtualStockReadService commodityProductUnitVirtualStockReadService;
	
	@Resource
	private CommodityProductUnitVirtualStockWriteService commodityProductUnitVirtualStockWriteService;
	
	
	public CommodityProductUnitVirtualStockDTO findCommodityProductUnitVirtualStockById(CommodityProductUnitVirtualStockDTO dto){
		
		return commodityProductUnitVirtualStockReadService.findCommodityProductUnitVirtualStockById(dto);
	}

	public PageResult<CommodityProductUnitVirtualStockDTO> findCommodityProductUnitVirtualStockOfPage(CommodityProductUnitVirtualStockDTO dto,Pagination page){
		
		return commodityProductUnitVirtualStockReadService.findCommodityProductUnitVirtualStockOfPage(dto, page);
		
	}

	public List<CommodityProductUnitVirtualStockDTO> findCommodityProductUnitVirtualStockAll(CommodityProductUnitVirtualStockDTO dto){
		
		return commodityProductUnitVirtualStockReadService.findCommodityProductUnitVirtualStockAll(dto);
		
	}

	public Long insertCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto){
		
		return commodityProductUnitVirtualStockWriteService.insertCommodityProductUnitVirtualStockWithTx(dto);
	}

	public int updateCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto){
		
		return commodityProductUnitVirtualStockWriteService.updateCommodityProductUnitVirtualStockWithTx(dto);
	}

	public int deleteCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto){
		
		return commodityProductUnitVirtualStockWriteService.deleteCommodityProductUnitVirtualStockWithTx(dto);
		
	}

}
	