package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.IconCompanyWriteService;
import com.egeo.components.cms.manage.write.IconCompanyWriteManage;
import com.egeo.components.cms.converter.IconCompanyConverter;
import com.egeo.components.cms.dto.IconCompanyDTO;
import com.egeo.components.cms.po.IconCompanyPO;

@Service("iconCompanyWriteService")
public class IconCompanyWriteServiceImpl  implements IconCompanyWriteService {
	@Autowired
	private IconCompanyWriteManage iconCompanyWriteManage;

	@Override
	public Long insertIconCompanyWithTx(IconCompanyDTO dto) {
		IconCompanyPO po = IconCompanyConverter.toPO(dto);
		Long rt = iconCompanyWriteManage.insertIconCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateIconCompanyWithTx(IconCompanyDTO dto) {
		IconCompanyPO po = IconCompanyConverter.toPO(dto);
		int rt = iconCompanyWriteManage.updateIconCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteIconCompanyWithTx(IconCompanyDTO dto) {
		IconCompanyPO po = IconCompanyConverter.toPO(dto);
		int rt = iconCompanyWriteManage.deleteIconCompanyWithTx(po);		
		return rt;
	}
}
	