package com.egeo.components.finance.service.write.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.finance.converter.AdjustReasonConverter;
import com.egeo.components.finance.dto.AdjustReasonDTO;
import com.egeo.components.finance.manage.write.AdjustReasonWriteManage;
import com.egeo.components.finance.po.AdjustReasonPO;
import com.egeo.components.finance.service.write.AdjustReasonWriteService;

@Service("adjustReasonWriteService")
public class AdjustReasonWriteServiceImpl  implements AdjustReasonWriteService {
	@Autowired
	private AdjustReasonWriteManage adjustReasonWriteManage;

	@Override
	public Long insertAdjustReasonWithTx(AdjustReasonDTO dto) {
		AdjustReasonPO po = AdjustReasonConverter.toPO(dto);
		Long rt = adjustReasonWriteManage.insertAdjustReasonWithTx(po);		
		return rt;
	}

	@Override
	public int updateAdjustReasonWithTx(AdjustReasonDTO dto) {
		AdjustReasonPO po = AdjustReasonConverter.toPO(dto);
		int rt = adjustReasonWriteManage.updateAdjustReasonWithTx(po);		
		return rt;
	}

	@Override
	public int deleteAdjustReasonWithTx(AdjustReasonDTO dto) {
		AdjustReasonPO po = AdjustReasonConverter.toPO(dto);
		int rt = adjustReasonWriteManage.deleteAdjustReasonWithTx(po);		
		return rt;
	}

	@Override
	public Long createAdjustReason(AdjustReasonDTO dto,List<Long> cIds) {
		return adjustReasonWriteManage.createAdjustReasonWithTx(AdjustReasonConverter.toPO(dto),cIds);
	}

	@Override
	public int editAdjustReason(AdjustReasonDTO dto,List<Long> cIds) {
		return adjustReasonWriteManage.editAdjustReasonWithTx(AdjustReasonConverter.toPO(dto),cIds);
	}
}
	