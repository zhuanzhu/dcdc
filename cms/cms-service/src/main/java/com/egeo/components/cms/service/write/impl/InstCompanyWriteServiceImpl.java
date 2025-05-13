package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.InstCompanyWriteService;
import com.egeo.components.cms.manage.write.InstCompanyWriteManage;
import com.egeo.components.cms.converter.InstCompanyConverter;
import com.egeo.components.cms.dto.InstCompanyDTO;
import com.egeo.components.cms.po.InstCompanyPO;

@Service("instCompanyWriteService")
public class InstCompanyWriteServiceImpl  implements InstCompanyWriteService {
	@Autowired
	private InstCompanyWriteManage instCompanyWriteManage;

	@Override
	public Long insertInstCompanyWithTx(InstCompanyDTO dto) {
		InstCompanyPO po = InstCompanyConverter.toPO(dto);
		Long rt = instCompanyWriteManage.insertInstCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateInstCompanyWithTx(InstCompanyDTO dto) {
		InstCompanyPO po = InstCompanyConverter.toPO(dto);
		int rt = instCompanyWriteManage.updateInstCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteInstCompanyWithTx(InstCompanyDTO dto) {
		InstCompanyPO po = InstCompanyConverter.toPO(dto);
		int rt = instCompanyWriteManage.deleteInstCompanyWithTx(po);		
		return rt;
	}
}
	