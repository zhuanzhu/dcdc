package com.egeo.components.pay.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.converter.AlipayNativeSignLogConverter;
import com.egeo.components.pay.dto.AlipayNativeSignLogDTO;
import com.egeo.components.pay.manage.write.AlipayNativeSignLogWriteManage;
import com.egeo.components.pay.po.AlipayNativeSignLogPO;
import com.egeo.components.pay.service.write.AlipayNativeSignLogWriteService;

@Service("alipayNativeSignLogWriteService")
public class AlipayNativeSignLogWriteServiceImpl  implements AlipayNativeSignLogWriteService {
	@Autowired
	private AlipayNativeSignLogWriteManage alipayNativeSignLogWriteManage;

	@Override
	public Long insertAlipayNativeSignLogWithTx(AlipayNativeSignLogDTO dto) {
		AlipayNativeSignLogPO po = AlipayNativeSignLogConverter.toPO(dto);
		Long rt = alipayNativeSignLogWriteManage.insertAlipayNativeSignLogWithTx(po);		
		return rt;
	}

	@Override
	public int updateAlipayNativeSignLogWithTx(AlipayNativeSignLogDTO dto) {
		AlipayNativeSignLogPO po = AlipayNativeSignLogConverter.toPO(dto);
		int rt = alipayNativeSignLogWriteManage.updateAlipayNativeSignLogWithTx(po);		
		return rt;
	}

	@Override
	public int deleteAlipayNativeSignLogWithTx(AlipayNativeSignLogDTO dto) {
		AlipayNativeSignLogPO po = AlipayNativeSignLogConverter.toPO(dto);
		int rt = alipayNativeSignLogWriteManage.deleteAlipayNativeSignLogWithTx(po);		
		return rt;
	}
}
	