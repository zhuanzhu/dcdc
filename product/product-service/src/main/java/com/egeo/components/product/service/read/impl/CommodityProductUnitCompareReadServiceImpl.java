package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.CommodityProductUnitCompareReadService;
import com.egeo.components.product.manage.read.CommodityProductUnitCompareReadManage;
import com.egeo.components.product.converter.CommodityProductUnitCompareConverter;
import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;
import com.egeo.components.product.po.CommodityProductUnitComparePO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("commodityProductUnitCompareReadService")
public class CommodityProductUnitCompareReadServiceImpl  implements CommodityProductUnitCompareReadService {
	@Autowired
	private CommodityProductUnitCompareReadManage commodityProductUnitCompareReadManage;

	@Override
	public CommodityProductUnitCompareDTO findCommodityProductUnitCompareById(CommodityProductUnitCompareDTO dto) {
		CommodityProductUnitComparePO po = CommodityProductUnitCompareConverter.toPO(dto);
		CommodityProductUnitComparePO list = commodityProductUnitCompareReadManage.findCommodityProductUnitCompareById(po);		
		return CommodityProductUnitCompareConverter.toDTO(list);
	}

	@Override
	public PageResult<CommodityProductUnitCompareDTO> findCommodityProductUnitCompareOfPage(CommodityProductUnitCompareDTO dto, Pagination page) {
		CommodityProductUnitComparePO po = CommodityProductUnitCompareConverter.toPO(dto);
        PageResult<CommodityProductUnitComparePO> pageResult = commodityProductUnitCompareReadManage.findCommodityProductUnitCompareOfPage(po, page);
        
        List<CommodityProductUnitCompareDTO> list = CommodityProductUnitCompareConverter.toDTO(pageResult.getList());
        PageResult<CommodityProductUnitCompareDTO> result = new PageResult<CommodityProductUnitCompareDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CommodityProductUnitCompareDTO> findCommodityProductUnitCompareAll(CommodityProductUnitCompareDTO dto) {
		CommodityProductUnitComparePO po = CommodityProductUnitCompareConverter.toPO(dto);
		List<CommodityProductUnitComparePO> list = commodityProductUnitCompareReadManage.findCommodityProductUnitCompareAll(po);		
		return CommodityProductUnitCompareConverter.toDTO(list);
	}
}
	