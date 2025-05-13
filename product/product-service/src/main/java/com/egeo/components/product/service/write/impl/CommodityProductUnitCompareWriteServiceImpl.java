package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.CommodityProductUnitCompareWriteService;
import com.egeo.components.product.manage.write.CommodityProductUnitCompareWriteManage;
import com.egeo.components.product.converter.CommodityProductUnitCompareConverter;
import com.egeo.components.product.dto.CommodityProductUnitCompareDTO;
import com.egeo.components.product.po.CommodityProductUnitComparePO;

import java.util.Date;

@Service("commodityProductUnitCompareWriteService")
public class CommodityProductUnitCompareWriteServiceImpl  implements CommodityProductUnitCompareWriteService {
	@Autowired
	private CommodityProductUnitCompareWriteManage commodityProductUnitCompareWriteManage;

	@Override
	public Long insertCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto) {
		CommodityProductUnitComparePO po = CommodityProductUnitCompareConverter.toPO(dto);
		po.setUpdateTime(new Date());
		Long rt = commodityProductUnitCompareWriteManage.insertCommodityProductUnitCompareWithTx(po);		
		return rt;
	}

	@Override
	public int updateCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto) {
		CommodityProductUnitComparePO po = CommodityProductUnitCompareConverter.toPO(dto);
		po.setUpdateTime(new Date());
		int rt = commodityProductUnitCompareWriteManage.updateCommodityProductUnitCompareWithTx(po);		
		return rt;
	}

	@Override
	public int deleteCommodityProductUnitCompareWithTx(CommodityProductUnitCompareDTO dto) {
		CommodityProductUnitComparePO po = CommodityProductUnitCompareConverter.toPO(dto);
		int rt = commodityProductUnitCompareWriteManage.deleteCommodityProductUnitCompareWithTx(po);		
		return rt;
	}
}
	