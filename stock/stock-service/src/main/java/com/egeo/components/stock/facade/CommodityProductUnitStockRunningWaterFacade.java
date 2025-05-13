package com.egeo.components.stock.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.stock.service.read.CommodityProductUnitStockRunningWaterReadService;
import com.egeo.components.stock.service.read.ContactGroupStockReadService;
import com.egeo.components.stock.service.write.CommodityProductUnitStockRunningWaterWriteService;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CommodityProductUnitStockRunningWaterFacade {
	
	@Resource
	private CommodityProductUnitStockRunningWaterReadService commodityProductUnitStockRunningWaterReadService;
	
	@Resource
	private CommodityProductUnitStockRunningWaterWriteService commodityProductUnitStockRunningWaterWriteService;
	
	private ContactGroupStockReadService contactGroupStockReadService;
	
	
	public CommodityProductUnitStockRunningWaterDTO findCommodityProductUnitStockRunningWaterById(CommodityProductUnitStockRunningWaterDTO dto){
		
		return commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterById(dto);
	}

	public PageResult<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterOfPage(CommodityProductUnitStockRunningWaterDTO dto,Pagination page){
		
		return commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterOfPage(dto, page);
		
	}

	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterAll(CommodityProductUnitStockRunningWaterDTO dto){
		
		return commodityProductUnitStockRunningWaterReadService.findCommodityProductUnitStockRunningWaterAll(dto);
		
	}

	public Long insertCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto){
		
		return commodityProductUnitStockRunningWaterWriteService.insertCommodityProductUnitStockRunningWaterWithTx(dto);
	}

	public int updateCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto){
		
		return commodityProductUnitStockRunningWaterWriteService.updateCommodityProductUnitStockRunningWaterWithTx(dto);
	}

	public int deleteCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto){
		
		return commodityProductUnitStockRunningWaterWriteService.deleteCommodityProductUnitStockRunningWaterWithTx(dto);
		
	}

}
	