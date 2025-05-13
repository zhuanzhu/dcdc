package com.egeo.components.stock.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.stock.service.write.StoreStockChangeApplyPictureWriteService;
import com.egeo.components.stock.manage.write.StoreStockChangeApplyPictureWriteManage;
import com.egeo.components.stock.converter.StoreStockChangeApplyPictureConverter;
import com.egeo.components.stock.dto.StoreStockChangeApplyPictureDTO;
import com.egeo.components.stock.po.StoreStockChangeApplyPicturePO;

@Service("storeStockChangeApplyPictureWriteService")
public class StoreStockChangeApplyPictureWriteServiceImpl  implements StoreStockChangeApplyPictureWriteService {
	@Autowired
	private StoreStockChangeApplyPictureWriteManage storeStockChangeApplyPictureWriteManage;

	@Override
	public Long insertStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto) {
		StoreStockChangeApplyPicturePO po = StoreStockChangeApplyPictureConverter.toPO(dto);
		Long rt = storeStockChangeApplyPictureWriteManage.insertStoreStockChangeApplyPictureWithTx(po);		
		return rt;
	}

	@Override
	public int updateStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto) {
		StoreStockChangeApplyPicturePO po = StoreStockChangeApplyPictureConverter.toPO(dto);
		int rt = storeStockChangeApplyPictureWriteManage.updateStoreStockChangeApplyPictureWithTx(po);		
		return rt;
	}

	@Override
	public int deleteStoreStockChangeApplyPictureWithTx(StoreStockChangeApplyPictureDTO dto) {
		StoreStockChangeApplyPicturePO po = StoreStockChangeApplyPictureConverter.toPO(dto);
		int rt = storeStockChangeApplyPictureWriteManage.deleteStoreStockChangeApplyPictureWithTx(po);		
		return rt;
	}
}
	