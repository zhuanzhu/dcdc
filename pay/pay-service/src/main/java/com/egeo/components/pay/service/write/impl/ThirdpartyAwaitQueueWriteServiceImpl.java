package com.egeo.components.pay.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.converter.ThirdpartyAwaitQueueConverter;
import com.egeo.components.pay.dto.ThirdpartyAwaitQueueDTO;
import com.egeo.components.pay.manage.write.ThirdpartyAwaitQueueWriteManage;
import com.egeo.components.pay.po.ThirdpartyAwaitQueuePO;
import com.egeo.components.pay.service.write.ThirdpartyAwaitQueueWriteService;

@Service("thirdpartyAwaitQueueWriteService")
public class ThirdpartyAwaitQueueWriteServiceImpl  implements ThirdpartyAwaitQueueWriteService {
	@Autowired
	private ThirdpartyAwaitQueueWriteManage thirdpartyAwaitQueueWriteManage;

	@Override
	public Long insertThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto) {
		ThirdpartyAwaitQueuePO po = ThirdpartyAwaitQueueConverter.toPO(dto);
		Long rt = thirdpartyAwaitQueueWriteManage.insertThirdpartyAwaitQueueWithTx(po);		
		return rt;
	}

	@Override
	public int updateThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto) {
		ThirdpartyAwaitQueuePO po = ThirdpartyAwaitQueueConverter.toPO(dto);
		int rt = thirdpartyAwaitQueueWriteManage.updateThirdpartyAwaitQueueWithTx(po);		
		return rt;
	}

	@Override
	public int deleteThirdpartyAwaitQueueWithTx(ThirdpartyAwaitQueueDTO dto) {
		ThirdpartyAwaitQueuePO po = ThirdpartyAwaitQueueConverter.toPO(dto);
		int rt = thirdpartyAwaitQueueWriteManage.deleteThirdpartyAwaitQueueWithTx(po);		
		return rt;
	}

	@Override
	public int deleteThirdpartyAwaitQueueByCodeWithTx(ThirdpartyAwaitQueueDTO dto) {
		ThirdpartyAwaitQueuePO po = ThirdpartyAwaitQueueConverter.toPO(dto);
		int rt = thirdpartyAwaitQueueWriteManage.deleteThirdpartyAwaitQueueByCodeWithTx(po);
		return rt;
	}
}
	