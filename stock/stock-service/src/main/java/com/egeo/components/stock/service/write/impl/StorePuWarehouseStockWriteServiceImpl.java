package com.egeo.components.stock.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.stock.service.write.StorePuWarehouseStockWriteService;
import com.egeo.components.stock.manage.write.StorePuWarehouseStockWriteManage;
import com.egeo.components.stock.converter.StorePuStockRunningWaterConverter;
import com.egeo.components.stock.converter.StorePuWarehouseStockConverter;
import com.egeo.components.stock.dto.StorePuStockRunningWaterDTO;
import com.egeo.components.stock.dto.StorePuWarehouseStockDTO;
import com.egeo.components.stock.po.StorePuWarehouseStockPO;


@Service("storePuWarehouseStockWriteService")
public class StorePuWarehouseStockWriteServiceImpl  implements StorePuWarehouseStockWriteService {
	@Autowired
	private StorePuWarehouseStockWriteManage storePuWarehouseStockWriteManage;

	@Override
	public Long insertStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto) {
		StorePuWarehouseStockPO po = StorePuWarehouseStockConverter.toPO(dto);
		Long rt = storePuWarehouseStockWriteManage.insertStorePuWarehouseStockWithTx(po);		
		return rt;
	}

	@Override
	public int updateStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto) {
		StorePuWarehouseStockPO po = StorePuWarehouseStockConverter.toPO(dto);
		int rt = storePuWarehouseStockWriteManage.updateStorePuWarehouseStockWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStorePuWarehouseStockWithTx(StorePuWarehouseStockDTO dto) {
		StorePuWarehouseStockPO po = StorePuWarehouseStockConverter.toPO(dto);
		int rt = storePuWarehouseStockWriteManage.deleteStorePuWarehouseStockWithTx(po);		
		return rt;
	}

	@Override
	public int insertAllWithTx(List<StorePuWarehouseStockDTO> list) {
		
		return storePuWarehouseStockWriteManage.insertAllWithTx(StorePuWarehouseStockConverter.toPO(list));
	}

	@Override
	public int updateStorePuWarehouseStock(List<StorePuStockRunningWaterDTO> storePuStockRunningWaterDTOs, int type) {
		return storePuWarehouseStockWriteManage.updateStorePuWarehouseStock(
				StorePuStockRunningWaterConverter.toPO(storePuStockRunningWaterDTOs), type);
	}
}
	