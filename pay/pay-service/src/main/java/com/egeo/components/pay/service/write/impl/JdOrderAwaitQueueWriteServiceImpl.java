package com.egeo.components.pay.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.converter.JdOrderAwaitQueueConverter;
import com.egeo.components.pay.dto.JdOrderAwaitQueueDTO;
import com.egeo.components.pay.manage.write.JdOrderAwaitQueueWriteManage;
import com.egeo.components.pay.po.JdOrderAwaitQueuePO;
import com.egeo.components.pay.service.write.JdOrderAwaitQueueWriteService;

@Service("jdOrderAwaitQueueWriteService")
public class JdOrderAwaitQueueWriteServiceImpl  implements JdOrderAwaitQueueWriteService {
	@Autowired
	private JdOrderAwaitQueueWriteManage jdOrderAwaitQueueWriteManage;

	@Override
	public Long insertJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto) {
		JdOrderAwaitQueuePO po = JdOrderAwaitQueueConverter.toPO(dto);
		Long rt = jdOrderAwaitQueueWriteManage.insertJdOrderAwaitQueueWithTx(po);		
		return rt;
	}

	@Override
	public int updateJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto) {
		JdOrderAwaitQueuePO po = JdOrderAwaitQueueConverter.toPO(dto);
		int rt = jdOrderAwaitQueueWriteManage.updateJdOrderAwaitQueueWithTx(po);		
		return rt;
	}

	@Override
	public int deleteJdOrderAwaitQueueWithTx(JdOrderAwaitQueueDTO dto) {
		JdOrderAwaitQueuePO po = JdOrderAwaitQueueConverter.toPO(dto);
		int rt = jdOrderAwaitQueueWriteManage.deleteJdOrderAwaitQueueWithTx(po);		
		return rt;
	}
}
	