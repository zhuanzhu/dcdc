package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.JdPriceLimitUploadWriteService;
import com.egeo.components.product.manage.write.JdPriceLimitUploadWriteManage;
import com.egeo.components.product.converter.JdPriceLimitUploadConverter;
import com.egeo.components.product.dto.JdPriceLimitUploadDTO;
import com.egeo.components.product.po.JdPriceLimitUploadPO;

@Service("jdPriceLimitUploadWriteService")
public class JdPriceLimitUploadWriteServiceImpl  implements JdPriceLimitUploadWriteService {
	@Autowired
	private JdPriceLimitUploadWriteManage jdPriceLimitUploadWriteManage;

	@Override
	public Long insertJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto) {
		JdPriceLimitUploadPO po = JdPriceLimitUploadConverter.toPO(dto);
		Long rt = jdPriceLimitUploadWriteManage.insertJdPriceLimitUploadWithTx(po);		
		return rt;
	}

	@Override
	public int updateJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto) {
		JdPriceLimitUploadPO po = JdPriceLimitUploadConverter.toPO(dto);
		int rt = jdPriceLimitUploadWriteManage.updateJdPriceLimitUploadWithTx(po);		
		return rt;
	}

	@Override
	public int deleteJdPriceLimitUploadWithTx(JdPriceLimitUploadDTO dto) {
		JdPriceLimitUploadPO po = JdPriceLimitUploadConverter.toPO(dto);
		int rt = jdPriceLimitUploadWriteManage.deleteJdPriceLimitUploadWithTx(po);		
		return rt;
	}
}
	