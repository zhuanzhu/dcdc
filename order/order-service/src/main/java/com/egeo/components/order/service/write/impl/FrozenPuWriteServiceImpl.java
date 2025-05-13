package com.egeo.components.order.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.order.service.write.FrozenPuWriteService;
import com.egeo.components.order.manage.write.FrozenPuWriteManage;
import com.egeo.components.order.converter.FrozenPuConverter;
import com.egeo.components.order.dto.FrozenPuDTO;
import com.egeo.components.order.po.FrozenPuPO;

@Service("frozenPuWriteService")
public class FrozenPuWriteServiceImpl  implements FrozenPuWriteService {
	@Autowired
	private FrozenPuWriteManage frozenPuWriteManage;

	@Override
	public Long insertFrozenPuWithTx(FrozenPuDTO dto) {
		FrozenPuPO po = FrozenPuConverter.toPO(dto);
		Long rt = frozenPuWriteManage.insertFrozenPuWithTx(po);		
		return rt;
	}

	@Override
	public int updateFrozenPuWithTx(FrozenPuDTO dto) {
		FrozenPuPO po = FrozenPuConverter.toPO(dto);
		int rt = frozenPuWriteManage.updateFrozenPuWithTx(po);		
		return rt;
	}

	@Override
	public int deleteFrozenPuWithTx(FrozenPuDTO dto) {
		FrozenPuPO po = FrozenPuConverter.toPO(dto);
		int rt = frozenPuWriteManage.deleteFrozenPuWithTx(po);		
		return rt;
	}
}
	