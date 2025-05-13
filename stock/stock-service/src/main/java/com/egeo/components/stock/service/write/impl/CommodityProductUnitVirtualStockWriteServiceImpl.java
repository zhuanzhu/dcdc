package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.CommodityProductUnitVirtualStockWriteService;
import com.egeo.components.stock.manage.write.CommodityProductUnitVirtualStockWriteManage;
import com.egeo.components.stock.converter.CommodityProductUnitVirtualStockConverter;
import com.egeo.components.stock.dto.CommodityProductUnitVirtualStockDTO;
import com.egeo.components.stock.po.CommodityProductUnitVirtualStockPO;

@Service("commodityProductUnitVirtualStockWriteService")
public class CommodityProductUnitVirtualStockWriteServiceImpl  implements CommodityProductUnitVirtualStockWriteService {
	@Autowired
	private CommodityProductUnitVirtualStockWriteManage commodityProductUnitVirtualStockWriteManage;

	@Override
	public Long insertCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto) {
		CommodityProductUnitVirtualStockPO po = CommodityProductUnitVirtualStockConverter.toPO(dto);
		Long rt = commodityProductUnitVirtualStockWriteManage.insertCommodityProductUnitVirtualStockWithTx(po);		
		return rt;
	}

	@Override
	public int updateCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto) {
		CommodityProductUnitVirtualStockPO po = CommodityProductUnitVirtualStockConverter.toPO(dto);
		int rt = commodityProductUnitVirtualStockWriteManage.updateCommodityProductUnitVirtualStockWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCommodityProductUnitVirtualStockWithTx(CommodityProductUnitVirtualStockDTO dto) {
		CommodityProductUnitVirtualStockPO po = CommodityProductUnitVirtualStockConverter.toPO(dto);
		int rt = commodityProductUnitVirtualStockWriteManage.deleteCommodityProductUnitVirtualStockWithTx(po);		
		return rt;
	}
}
	