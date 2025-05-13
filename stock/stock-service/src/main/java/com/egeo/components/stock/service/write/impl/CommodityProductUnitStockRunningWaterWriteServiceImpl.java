package com.egeo.components.stock.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


import com.egeo.exception.BusinessException;
import com.egeo.components.stock.service.write.CommodityProductUnitStockRunningWaterWriteService;
import com.egeo.components.stock.manage.write.CommodityProductUnitStockRunningWaterWriteManage;
import com.egeo.components.stock.converter.CommodityProductUnitStockRunningWaterConverter;
import com.egeo.components.stock.dto.CommodityProductUnitStockRunningWaterDTO;
import com.egeo.components.stock.po.CommodityProductUnitStockRunningWaterPO;

@Service("commodityProductUnitStockRunningWaterWriteService")
public class CommodityProductUnitStockRunningWaterWriteServiceImpl  implements CommodityProductUnitStockRunningWaterWriteService {
	@Autowired
	private CommodityProductUnitStockRunningWaterWriteManage commodityProductUnitStockRunningWaterWriteManage;

	@Override
	public Long insertCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto) {
		CommodityProductUnitStockRunningWaterPO po = CommodityProductUnitStockRunningWaterConverter.toPO(dto);
		Long rt = commodityProductUnitStockRunningWaterWriteManage.insertCommodityProductUnitStockRunningWaterWithTx(po);		
		return rt;
	}

	@Override
	public int updateCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto) {
		CommodityProductUnitStockRunningWaterPO po = CommodityProductUnitStockRunningWaterConverter.toPO(dto);
		int rt = commodityProductUnitStockRunningWaterWriteManage.updateCommodityProductUnitStockRunningWaterWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCommodityProductUnitStockRunningWaterWithTx(CommodityProductUnitStockRunningWaterDTO dto) {
		CommodityProductUnitStockRunningWaterPO po = CommodityProductUnitStockRunningWaterConverter.toPO(dto);
		int rt = commodityProductUnitStockRunningWaterWriteManage.deleteCommodityProductUnitStockRunningWaterWithTx(po);		
		return rt;
	}
	
	/**
	 * 批量生成流水
	 */
	@Override
	public int insertBatchCommodityProductUnitStockRunningWaterWithTx(List<CommodityProductUnitStockRunningWaterDTO> dtos) {
		
		List<CommodityProductUnitStockRunningWaterPO> pos = CommodityProductUnitStockRunningWaterConverter.toPO(dtos);
		
		int	i = commodityProductUnitStockRunningWaterWriteManage.insertBatchCommodityProductUnitStockRunningWaterWithTx(pos);
		return  i;
	}
}
	