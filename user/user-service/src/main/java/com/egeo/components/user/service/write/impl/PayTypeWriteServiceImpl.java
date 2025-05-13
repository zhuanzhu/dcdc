package com.egeo.components.user.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.converter.PayTypeConverter;
import com.egeo.components.user.dto.PayTypeDTO;
import com.egeo.components.user.manage.write.PayTypeWriteManage;
import com.egeo.components.user.po.PayTypePO;
import com.egeo.components.user.service.write.PayTypeWriteService;

@Service("payTypeWriteService")
public class PayTypeWriteServiceImpl implements PayTypeWriteService {
	@Autowired
	private PayTypeWriteManage payTypeWriteManage;

	@Override
	public Long insertPayTypeWithTx(PayTypeDTO dto) {
		PayTypePO po = PayTypeConverter.toPO(dto);
		Long rt = payTypeWriteManage.insertPayTypeWithTx(po);		
		return rt;
	}

	@Override
	public int updatePayTypeWithTx(PayTypeDTO dto) {
		PayTypePO po = PayTypeConverter.toPO(dto);
		int rt = payTypeWriteManage.updatePayTypeWithTx(po);		
		return rt;
	}

	@Override
	public int deletePayTypeWithTx(PayTypeDTO dto) {
		PayTypePO po = PayTypeConverter.toPO(dto);
		int rt = payTypeWriteManage.deletePayTypeWithTx(po);		
		return rt;
	}
}
	