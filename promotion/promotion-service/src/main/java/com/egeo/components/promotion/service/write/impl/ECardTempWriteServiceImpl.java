package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.ECardTempWriteService;
import com.egeo.components.promotion.manage.write.ECardTempWriteManage;
import com.egeo.components.promotion.converter.ECardTempConverter;
import com.egeo.components.promotion.dto.ECardTempDTO;
import com.egeo.components.promotion.po.ECardTempPO;

@Service("eCardTempWriteService")
public class ECardTempWriteServiceImpl implements ECardTempWriteService {
	@Autowired
	private ECardTempWriteManage eCardTempWriteManage;

	@Override
	public Long insertECardTempWithTx(ECardTempDTO dto) {
		ECardTempPO po = ECardTempConverter.toPO(dto);
		Long rt = eCardTempWriteManage.insertECardTempWithTx(po);		
		return rt;
	}

	@Override
	public int updateECardTempWithTx(ECardTempDTO dto) {
		ECardTempPO po = ECardTempConverter.toPO(dto);
		int rt = eCardTempWriteManage.updateECardTempWithTx(po);		
		return rt;
	}

	@Override
	public int deleteECardTempWithTx(ECardTempDTO dto) {
		ECardTempPO po = ECardTempConverter.toPO(dto);
		int rt = eCardTempWriteManage.deleteECardTempWithTx(po);		
		return rt;
	}
}
	