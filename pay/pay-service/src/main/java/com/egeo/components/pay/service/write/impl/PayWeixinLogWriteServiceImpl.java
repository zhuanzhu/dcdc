package com.egeo.components.pay.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.pay.converter.PayWeixinLogConverter;
import com.egeo.components.pay.dto.PayWeixinLogDTO;
import com.egeo.components.pay.manage.write.PayWeixinLogWriteManage;
import com.egeo.components.pay.po.PayWeixinLogPO;
import com.egeo.components.pay.service.write.PayWeixinLogWriteService;

@Service("payWeixinLogWriteService")
public class PayWeixinLogWriteServiceImpl  implements PayWeixinLogWriteService {
	@Autowired
	private PayWeixinLogWriteManage payWeixinLogWriteManage;

	@Override
	public Long insertPayWeixinLogWithTx(PayWeixinLogDTO dto) {
		PayWeixinLogPO po = PayWeixinLogConverter.toPO(dto);
		Long rt = payWeixinLogWriteManage.insertPayWeixinLogWithTx(po);		
		return rt;
	}

	@Override
	public int updatePayWeixinLogWithTx(PayWeixinLogDTO dto) {
		PayWeixinLogPO po = PayWeixinLogConverter.toPO(dto);
		int rt = payWeixinLogWriteManage.updatePayWeixinLogWithTx(po);		
		return rt;
	}

	@Override
	public int deletePayWeixinLogWithTx(PayWeixinLogDTO dto) {
		PayWeixinLogPO po = PayWeixinLogConverter.toPO(dto);
		int rt = payWeixinLogWriteManage.deletePayWeixinLogWithTx(po);		
		return rt;
	}
}
	