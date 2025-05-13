package com.egeo.components.promotion.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 
import com.egeo.components.promotion.service.write.PraisePointWriteService;
import com.egeo.components.promotion.manage.write.PraisePointWriteManage;
import com.egeo.components.promotion.converter.PraisePointConverter;
import com.egeo.components.promotion.dto.PraisePointDTO;
import com.egeo.components.promotion.po.PraisePointPO;

@Service("praisePointWriteService")
public class PraisePointWriteServiceImpl implements PraisePointWriteService {
	@Autowired
	private PraisePointWriteManage praisePointWriteManage;

	@Override
	public Long insertPraisePointWithTx(PraisePointDTO dto) {
		PraisePointPO po = PraisePointConverter.toPO(dto);
		Long rt = praisePointWriteManage.insertPraisePointWithTx(po);		
		return rt;
	}

	@Override
	public int updatePraisePointWithTx(PraisePointDTO dto) {
		PraisePointPO po = PraisePointConverter.toPO(dto);
		int rt = praisePointWriteManage.updatePraisePointWithTx(po);		
		return rt;
	}

	@Override
	public int deletePraisePointWithTx(PraisePointDTO dto) {
		PraisePointPO po = PraisePointConverter.toPO(dto);
		int rt = praisePointWriteManage.deletePraisePointWithTx(po);		
		return rt;
	}

	@Override
	public int increasePraisePoint(double delta,Long userId,String ciphertextNew) {
		return praisePointWriteManage.increasePraisePointWithTx(delta,userId,ciphertextNew);
	}
}
	