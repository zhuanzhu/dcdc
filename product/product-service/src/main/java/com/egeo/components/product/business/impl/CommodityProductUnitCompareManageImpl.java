package com.egeo.components.product.business.impl;
	

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.product.business.CommodityProductUnitCompareManage;
import com.egeo.components.product.facade.CommodityProductUnitCompareFacade;
import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.EmptyUtil;

@Service("commodityProductUnitCompare")
public class CommodityProductUnitCompareManageImpl implements CommodityProductUnitCompareManage{

	
	@Resource(name="commodityProductUnitCompareFacade")
	private CommodityProductUnitCompareFacade commodityProductUnitCompareFacade;

	@Override
	public CommodityProductUnitCompareDTO findCommodityProductUnitCompareById(CommodityProductUnitCompareDTO dto) {
		return commodityProductUnitCompareFacade.findCommodityProductUnitCompareById(dto);
	}

	@Override
	public PageResult<CommodityProductUnitCompareDTO> findCommodityProductUnitCompareOfPage(CommodityProductUnitCompareDTO dto, Pagination page) {
		return commodityProductUnitCompareFacade.findCommodityProductUnitCompareOfPage(dto, page);
	}

	@Override
	public List<CommodityProductUnitCompareDTO> findCommodityProductUnitCompareAll(CommodityProductUnitCompareDTO dto) {
		return commodityProductUnitCompareFacade.findCommodityProductUnitCompareAll(dto);
	}

	@Override
	public Long insertCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto) {
		CommodityProductUnitCompareDTO cpucDTO = new CommodityProductUnitCompareDTO();
		cpucDTO.setPlatformId(dto.getPlatformId());
		List<CommodityProductUnitCompareDTO> ls = commodityProductUnitCompareFacade.findCommodityProductUnitCompareAll(cpucDTO);
		if (EmptyUtil.isNotEmpty(ls)) {
			CommodityProductUnitCompareDTO cpuc = ls.get(0);
			cpuc.setPlatformName(dto.getPlatformName());
			cpuc.setStatus(1);
			cpuc.setUpdateTime(new Date());
			int i = commodityProductUnitCompareFacade.updateCommodityProductUnitCompareWithTx(cpuc);
			return  Long.valueOf(i);
		}
		dto.setStatus(1);
		return commodityProductUnitCompareFacade.insertCommodityProductUnitCompareWithTx(dto);
	}

	@Override
	public int updateCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto) {
		return commodityProductUnitCompareFacade.updateCommodityProductUnitCompareWithTx(dto);
	}

	@Override
	public int deleteCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto) {
		return commodityProductUnitCompareFacade.deleteCommodityProductUnitCompareWithTx(dto);
	}


}
	