package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.FuCoinWriteService;
import com.egeo.components.promotion.manage.write.FuCoinWriteManage;
import com.egeo.components.promotion.converter.FuCoinConverter;
import com.egeo.components.promotion.dto.FuCoinDTO;
import com.egeo.components.promotion.po.FuCoinPO;

@Service("fuCoinWriteService")
public class FuCoinWriteServiceImpl implements FuCoinWriteService {
	@Autowired
	private FuCoinWriteManage fuCoinWriteManage;

	@Override
	public Long insertFuCoinWithTx(FuCoinDTO dto) {
		FuCoinPO po = FuCoinConverter.toPO(dto);
		Long rt = fuCoinWriteManage.insertFuCoinWithTx(po);		
		return rt;
	}

	@Override
	public int updateFuCoinWithTx(FuCoinDTO dto) {
		FuCoinPO po = FuCoinConverter.toPO(dto);
		int rt = fuCoinWriteManage.updateFuCoinWithTx(po);		
		return rt;
	}

	@Override
	public int deleteFuCoinWithTx(FuCoinDTO dto) {
		FuCoinPO po = FuCoinConverter.toPO(dto);
		int rt = fuCoinWriteManage.deleteFuCoinWithTx(po);		
		return rt;
	}
}
	