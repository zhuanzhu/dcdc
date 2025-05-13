package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.CommodityProductUnitCompareReadService;
import com.egeo.components.product.service.write.CommodityProductUnitCompareWriteService;
import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class CommodityProductUnitCompareFacade {
	
	@Resource
	private CommodityProductUnitCompareReadService commodityProductUnitCompareReadService;
	
	@Resource
	private CommodityProductUnitCompareWriteService commodityProductUnitCompareWriteService;
	
	
	public CommodityProductUnitCompareDTO findCommodityProductUnitCompareById(CommodityProductUnitCompareDTO dto){
		
		return commodityProductUnitCompareReadService.findCommodityProductUnitCompareById(dto);
	}

	public PageResult<CommodityProductUnitCompareDTO> findCommodityProductUnitCompareOfPage(CommodityProductUnitCompareDTO dto,Pagination page){
		
		return commodityProductUnitCompareReadService.findCommodityProductUnitCompareOfPage(dto, page);
		
	}

	public List<CommodityProductUnitCompareDTO> findCommodityProductUnitCompareAll(CommodityProductUnitCompareDTO dto){
		
		return commodityProductUnitCompareReadService.findCommodityProductUnitCompareAll(dto);
		
	}

	public Long insertCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto){
		
		return commodityProductUnitCompareWriteService.insertCommodityProductUnitCompareWithTx(dto);
	}

	public int updateCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto){
		
		return commodityProductUnitCompareWriteService.updateCommodityProductUnitCompareWithTx(dto);
	}

	public int deleteCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto){
		
		return commodityProductUnitCompareWriteService.deleteCommodityProductUnitCompareWithTx(dto);
		
	}

}
	