package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.CommodityProductUnitVirtualStockReadService;
import com.egeo.components.stock.manage.read.CommodityProductUnitVirtualStockReadManage;
import com.egeo.components.stock.converter.CommodityProductUnitVirtualStockConverter;
import com.egeo.components.stock.dto.CommodityProductUnitVirtualStockDTO;
import com.egeo.components.stock.po.CommodityProductUnitVirtualStockPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("commodityProductUnitVirtualStockReadService")
public class CommodityProductUnitVirtualStockReadServiceImpl  implements CommodityProductUnitVirtualStockReadService {
	@Autowired
	private CommodityProductUnitVirtualStockReadManage commodityProductUnitVirtualStockReadManage;

	@Override
	public CommodityProductUnitVirtualStockDTO findCommodityProductUnitVirtualStockById(CommodityProductUnitVirtualStockDTO dto) {
		CommodityProductUnitVirtualStockPO po = CommodityProductUnitVirtualStockConverter.toPO(dto);
		CommodityProductUnitVirtualStockPO list = commodityProductUnitVirtualStockReadManage.findCommodityProductUnitVirtualStockById(po);		
		return CommodityProductUnitVirtualStockConverter.toDTO(list);
	}

	@Override
	public PageResult<CommodityProductUnitVirtualStockDTO> findCommodityProductUnitVirtualStockOfPage(CommodityProductUnitVirtualStockDTO dto, Pagination page) {
		CommodityProductUnitVirtualStockPO po = CommodityProductUnitVirtualStockConverter.toPO(dto);
        PageResult<CommodityProductUnitVirtualStockPO> pageResult = commodityProductUnitVirtualStockReadManage.findCommodityProductUnitVirtualStockOfPage(po, page);
        
        List<CommodityProductUnitVirtualStockDTO> list = CommodityProductUnitVirtualStockConverter.toDTO(pageResult.getList());
        PageResult<CommodityProductUnitVirtualStockDTO> result = new PageResult<CommodityProductUnitVirtualStockDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CommodityProductUnitVirtualStockDTO> findCommodityProductUnitVirtualStockAll(CommodityProductUnitVirtualStockDTO dto) {
		CommodityProductUnitVirtualStockPO po = CommodityProductUnitVirtualStockConverter.toPO(dto);
		List<CommodityProductUnitVirtualStockPO> list = commodityProductUnitVirtualStockReadManage.findCommodityProductUnitVirtualStockAll(po);		
		return CommodityProductUnitVirtualStockConverter.toDTO(list);
	}
}
	