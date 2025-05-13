package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.IconGroupCompanyWriteService;
import com.egeo.components.cms.manage.write.IconGroupCompanyWriteManage;
import com.egeo.components.cms.converter.IconGroupCompanyConverter;
import com.egeo.components.cms.dto.IconGroupCompanyDTO;
import com.egeo.components.cms.po.IconGroupCompanyPO;

@Service("iconGroupCompanyWriteService")
public class IconGroupCompanyWriteServiceImpl  implements IconGroupCompanyWriteService {
	@Autowired
	private IconGroupCompanyWriteManage iconGroupCompanyWriteManage;

	@Override
	public Long insertIconGroupCompanyWithTx(IconGroupCompanyDTO dto) {
		IconGroupCompanyPO po = IconGroupCompanyConverter.toPO(dto);
		Long rt = iconGroupCompanyWriteManage.insertIconGroupCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateIconGroupCompanyWithTx(IconGroupCompanyDTO dto) {
		IconGroupCompanyPO po = IconGroupCompanyConverter.toPO(dto);
		int rt = iconGroupCompanyWriteManage.updateIconGroupCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteIconGroupCompanyWithTx(IconGroupCompanyDTO dto) {
		IconGroupCompanyPO po = IconGroupCompanyConverter.toPO(dto);
		int rt = iconGroupCompanyWriteManage.deleteIconGroupCompanyWithTx(po);		
		return rt;
	}
}
	