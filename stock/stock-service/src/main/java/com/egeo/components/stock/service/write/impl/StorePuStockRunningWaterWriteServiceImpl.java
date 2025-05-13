package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.StorePuStockRunningWaterWriteService;
import com.egeo.components.stock.manage.write.StorePuStockRunningWaterWriteManage;
import com.egeo.components.stock.converter.StorePuStockRunningWaterConverter;
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.components.stock.po.StorePuStockRunningWaterPO;

@Service("storePuStockRunningWaterWriteService")
public class StorePuStockRunningWaterWriteServiceImpl  implements StorePuStockRunningWaterWriteService {
	@Autowired
	private StorePuStockRunningWaterWriteManage storePuStockRunningWaterWriteManage;

	@Override
	public Long insertStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto) {
		StorePuStockRunningWaterPO po = StorePuStockRunningWaterConverter.toPO(dto);
		Long rt = storePuStockRunningWaterWriteManage.insertStorePuStockRunningWaterWithTx(po);		
		return rt;
	}

	@Override
	public int updateStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto) {
		StorePuStockRunningWaterPO po = StorePuStockRunningWaterConverter.toPO(dto);
		int rt = storePuStockRunningWaterWriteManage.updateStorePuStockRunningWaterWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStorePuStockRunningWaterWithTx(StorePuStockRunningWaterDTO dto) {
		StorePuStockRunningWaterPO po = StorePuStockRunningWaterConverter.toPO(dto);
		int rt = storePuStockRunningWaterWriteManage.deleteStorePuStockRunningWaterWithTx(po);		
		return rt;
	}
}
	