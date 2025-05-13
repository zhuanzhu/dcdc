package com.egeo.components.product.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.write.StoreProductUnitWriteService;
import com.egeo.components.product.manage.write.StoreProductUnitWriteManage;
import com.egeo.components.product.converter.StoreProductUnitConverter;
import com.egeo.components.product.dto.StoreProductUnitDTO;
import com.egeo.components.product.po.StoreProductUnitPO;


@Service("storeProductUnitWriteService")
public class StoreProductUnitWriteServiceImpl  implements StoreProductUnitWriteService {
	@Autowired
	private StoreProductUnitWriteManage storeProductUnitWriteManage;

	@Override
	public Long insertStoreProductUnitWithTx(StoreProductUnitDTO dto) {
		StoreProductUnitPO po = StoreProductUnitConverter.toPO(dto);
		Long rt = storeProductUnitWriteManage.insertStoreProductUnitWithTx(po);		
		return rt;
	}

	@Override
	public int updateStoreProductUnitWithTx(StoreProductUnitDTO dto) {
		StoreProductUnitPO po = StoreProductUnitConverter.toPO(dto);
		int rt = storeProductUnitWriteManage.updateStoreProductUnitWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStoreProductUnitWithTx(StoreProductUnitDTO dto) {
		StoreProductUnitPO po = StoreProductUnitConverter.toPO(dto);
		int rt = storeProductUnitWriteManage.deleteStoreProductUnitWithTx(po);		
		return rt;
	}

	@Override
	public int insertAllWithTx(List<StoreProductUnitDTO> list) {
		return storeProductUnitWriteManage.insertAllWithTx(StoreProductUnitConverter.toPO(list));
	}
}
	