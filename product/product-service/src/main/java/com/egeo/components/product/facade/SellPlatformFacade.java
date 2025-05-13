package com.egeo.components.product.facade;

import java.util.List;

import com.egeo.components.product.service.read.CommodityProductUnitCompareReadService;
import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;
import com.egeo.utils.EmptyUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import com.egeo.components.product.service.read.SellPlatformReadService;
import com.egeo.components.product.service.write.SellPlatformWriteService;
import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SellPlatformFacade {
	
	@Resource
	private SellPlatformReadService sellPlatformReadService;
	
	@Resource
	private SellPlatformWriteService sellPlatformWriteService;

	@Resource
	private CommodityProductUnitCompareReadService cpCompareReadService;
	
	public SellPlatformDTO findSellPlatformById(SellPlatformDTO dto){
		
		return sellPlatformReadService.findSellPlatformById(dto);
	}

	public PageResult<SellPlatformDTO> findSellPlatformOfPage(SellPlatformDTO dto,Pagination page){
		
		return sellPlatformReadService.findSellPlatformOfPage(dto, page);
		
	}

	public List<SellPlatformDTO> findSellPlatformAll(SellPlatformDTO dto){
		
		return sellPlatformReadService.findSellPlatformAll(dto);
		
	}

	public Long insertSellPlatformWithTx(SellPlatformDTO dto){
		
		return sellPlatformWriteService.insertSellPlatformWithTx(dto);
	}

	public int updateSellPlatformWithTx(SellPlatformDTO dto){
		
		return sellPlatformWriteService.updateSellPlatformWithTx(dto);
	}

	public int deleteSellPlatformWithTx(SellPlatformDTO dto){
		
		return sellPlatformWriteService.deleteSellPlatformWithTx(dto);
		
	}

	public CommodityProductUnitCompareDTO findCommodityProductUnitCompareAll(CommodityProductUnitCompareDTO cpuc) {
		List<CommodityProductUnitCompareDTO> ls = cpCompareReadService.findCommodityProductUnitCompareAll(cpuc);
		if (EmptyUtil.isEmpty(ls)) {
			return null;
		}
		return ls.get(0);
	}
}
	