package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.FuCoinHistoryWriteService;
import com.egeo.components.promotion.manage.write.FuCoinHistoryWriteManage;
import com.egeo.components.promotion.converter.FuCoinHistoryConverter;
import com.egeo.components.promotion.dto.FuCoinHistoryDTO;
import com.egeo.components.promotion.po.FuCoinHistoryPO;

@Service("fuCoinHistoryWriteService")
public class FuCoinHistoryWriteServiceImpl implements FuCoinHistoryWriteService {
	@Autowired
	private FuCoinHistoryWriteManage fuCoinHistoryWriteManage;

	@Override
	public Long insertFuCoinHistoryWithTx(FuCoinHistoryDTO dto) {
		FuCoinHistoryPO po = FuCoinHistoryConverter.toPO(dto);
		Long rt = fuCoinHistoryWriteManage.insertFuCoinHistoryWithTx(po);		
		return rt;
	}

	@Override
	public int updateFuCoinHistoryWithTx(FuCoinHistoryDTO dto) {
		FuCoinHistoryPO po = FuCoinHistoryConverter.toPO(dto);
		int rt = fuCoinHistoryWriteManage.updateFuCoinHistoryWithTx(po);		
		return rt;
	}

	@Override
	public int deleteFuCoinHistoryWithTx(FuCoinHistoryDTO dto) {
		FuCoinHistoryPO po = FuCoinHistoryConverter.toPO(dto);
		int rt = fuCoinHistoryWriteManage.deleteFuCoinHistoryWithTx(po);		
		return rt;
	}
}
	