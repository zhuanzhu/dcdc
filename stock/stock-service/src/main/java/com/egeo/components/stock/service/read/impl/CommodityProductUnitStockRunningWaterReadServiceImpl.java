package com.egeo.components.stock.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.read.CommodityProductUnitStockRunningWaterReadService;
import com.egeo.config.RuntimeContext;
import com.egeo.components.stock.manage.read.CommodityProductUnitStockRunningWaterReadManage;
import com.egeo.components.stock.condition.CommodityProductUnitStockRunningWaterCondition;
import com.egeo.components.stock.converter.CommodityProductUnitStockRunningWaterConverter;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("commodityProductUnitStockRunningWaterReadService")
public class CommodityProductUnitStockRunningWaterReadServiceImpl  implements CommodityProductUnitStockRunningWaterReadService {
	@Autowired
	private CommodityProductUnitStockRunningWaterReadManage commodityProductUnitStockRunningWaterReadManage;

	@Override
	public CommodityProductUnitStockRunningWaterDTO findCommodityProductUnitStockRunningWaterById(CommodityProductUnitStockRunningWaterDTO dto) {
		CommodityProductUnitStockRunningWaterPO po = CommodityProductUnitStockRunningWaterConverter.toPO(dto);
		CommodityProductUnitStockRunningWaterPO list = commodityProductUnitStockRunningWaterReadManage.findCommodityProductUnitStockRunningWaterById(po);		
		return CommodityProductUnitStockRunningWaterConverter.toDTO(list);
	}

	@Override
	public PageResult<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterOfPage(CommodityProductUnitStockRunningWaterDTO dto, Pagination page) {
		CommodityProductUnitStockRunningWaterPO po = CommodityProductUnitStockRunningWaterConverter.toPO(dto);
		if(RuntimeContext.cacheUser()!=null && RuntimeContext.cacheUser().getEnterpriseId()!=null) {
			po.setEnterpriseId(RuntimeContext.cacheUser().getEnterpriseId());
		}

        PageResult<CommodityProductUnitStockRunningWaterCondition> pageResult = commodityProductUnitStockRunningWaterReadManage.findConditionOfPage(po, page);
        
        List<CommodityProductUnitStockRunningWaterDTO> list = CommodityProductUnitStockRunningWaterConverter.conditionToDTO(pageResult.getList());
        PageResult<CommodityProductUnitStockRunningWaterDTO> result = new PageResult<CommodityProductUnitStockRunningWaterDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterAll(CommodityProductUnitStockRunningWaterDTO dto) {
		CommodityProductUnitStockRunningWaterPO po = CommodityProductUnitStockRunningWaterConverter.toPO(dto);
		List<CommodityProductUnitStockRunningWaterPO> list = commodityProductUnitStockRunningWaterReadManage.findCommodityProductUnitStockRunningWaterAll(po);		
		return CommodityProductUnitStockRunningWaterConverter.toDTO(list);
	}
	
	@Override
	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterByOrderCodes(List<String> orderCodes) {
		if(orderCodes != null && orderCodes.size() > 0) {
			List<CommodityProductUnitStockRunningWaterPO> list = commodityProductUnitStockRunningWaterReadManage.findCommodityProductUnitStockRunningWaterByOrderCodes(orderCodes);		
			return CommodityProductUnitStockRunningWaterConverter.toDTO(list);
		}
		return null;
	}
	
	@Override
	public List<CommodityProductUnitStockRunningWaterDTO> findCommodityProductUnitStockRunningWaterByOrderCodes(List<String> orderCodes,Integer status) {
		if(orderCodes != null && orderCodes.size() > 0) {
			List<CommodityProductUnitStockRunningWaterPO> list = commodityProductUnitStockRunningWaterReadManage.findCommodityProductUnitStockRunningWaterByOrderCodes(orderCodes,status);		
			return CommodityProductUnitStockRunningWaterConverter.toDTO(list);
		}
		return null;
	}
}
	