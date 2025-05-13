package com.egeo.components.pay.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.converter.PayAliLogConverter;
import com.egeo.components.pay.dto.PayAliLogDTO;
import com.egeo.components.pay.manage.write.PayAliLogWriteManage;
import com.egeo.components.pay.po.PayAliLogPO;
import com.egeo.components.pay.service.write.PayAliLogWriteService;

@Service("payAliLogWriteService")
public class PayAliLogWriteServiceImpl  implements PayAliLogWriteService {
	@Autowired
	private PayAliLogWriteManage payAliLogWriteManage;

	@Override
	public Long insertPayAliLogWithTx(PayAliLogDTO dto) {
		PayAliLogPO po = PayAliLogConverter.toPO(dto);
		Long rt = payAliLogWriteManage.insertPayAliLogWithTx(po);		
		return rt;
	}

	@Override
	public int updatePayAliLogWithTx(PayAliLogDTO dto) {
		PayAliLogPO po = PayAliLogConverter.toPO(dto);
		int rt = payAliLogWriteManage.updatePayAliLogWithTx(po);		
		return rt;
	}

	@Override
	public int deletePayAliLogWithTx(PayAliLogDTO dto) {
		PayAliLogPO po = PayAliLogConverter.toPO(dto);
		int rt = payAliLogWriteManage.deletePayAliLogWithTx(po);		
		return rt;
	}

	@Override
	public Long insertPayWeixinLogWithTx(PayAliLogDTO log) {
		PayAliLogPO po = PayAliLogConverter.toPO(log);
		return payAliLogWriteManage.insertPayAliLogWithTx(po);		
	}
}
	