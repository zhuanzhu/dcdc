package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.JdPriceConfigWriteService;
import com.egeo.components.product.manage.write.JdPriceConfigWriteManage;
import com.egeo.components.product.converter.JdPriceConfigConverter;
import com.egeo.components.product.dto.JdPriceConfigDTO;
import com.egeo.components.product.po.JdPriceConfigPO;

@Service("jdPriceConfigWriteService")
public class JdPriceConfigWriteServiceImpl  implements JdPriceConfigWriteService {
	@Autowired
	private JdPriceConfigWriteManage jdPriceConfigWriteManage;

	@Override
	public Long insertJdPriceConfigWithTx(JdPriceConfigDTO dto) {
		JdPriceConfigPO po = JdPriceConfigConverter.toPO(dto);
		Long rt = jdPriceConfigWriteManage.insertJdPriceConfigWithTx(po);		
		return rt;
	}

	@Override
	public int updateJdPriceConfigWithTx(JdPriceConfigDTO dto) {
		JdPriceConfigPO po = JdPriceConfigConverter.toPO(dto);
		int rt = jdPriceConfigWriteManage.updateJdPriceConfigWithTx(po);		
		return rt;
	}

	@Override
	public int deleteJdPriceConfigWithTx(JdPriceConfigDTO dto) {
		JdPriceConfigPO po = JdPriceConfigConverter.toPO(dto);
		int rt = jdPriceConfigWriteManage.deleteJdPriceConfigWithTx(po);		
		return rt;
	}
}
	