package com.egeo.components.product.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.product.service.write.JdCategoryWriteService;
import com.egeo.components.product.manage.write.JdCategoryWriteManage;
import com.egeo.components.product.converter.JdCategoryConverter;
import com.egeo.components.product.dto.JdCategoryDTO;
import com.egeo.components.product.po.JdCategoryPO;

@Service("jdCategoryWriteService")
public class JdCategoryWriteServiceImpl  implements JdCategoryWriteService {
	@Autowired
	private JdCategoryWriteManage jdCategoryWriteManage;

	@Override
	public Long insertJdCategoryWithTx(JdCategoryDTO dto) {
		JdCategoryPO po = JdCategoryConverter.toPO(dto);
		Long rt = jdCategoryWriteManage.insertJdCategoryWithTx(po);		
		return rt;
	}

	@Override
	public int updateJdCategoryWithTx(JdCategoryDTO dto) {
		JdCategoryPO po = JdCategoryConverter.toPO(dto);
		int rt = jdCategoryWriteManage.updateJdCategoryWithTx(po);		
		return rt;
	}

	@Override
	public int deleteJdCategoryWithTx(JdCategoryDTO dto) {
		JdCategoryPO po = JdCategoryConverter.toPO(dto);
		int rt = jdCategoryWriteManage.deleteJdCategoryWithTx(po);		
		return rt;
	}
}
	