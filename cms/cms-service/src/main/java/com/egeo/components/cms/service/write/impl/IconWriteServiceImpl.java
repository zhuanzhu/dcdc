package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.IconWriteService;
import com.egeo.components.cms.manage.write.IconWriteManage;
import com.egeo.components.cms.converter.IconConverter;
import com.egeo.components.cms.dto.IconDTO;
import com.egeo.components.cms.po.IconPO;

@Service("iconWriteService")
public class IconWriteServiceImpl  implements IconWriteService {
	@Autowired
	private IconWriteManage iconWriteManage;

	@Override
	public Long insertIconWithTx(IconDTO dto) {
		IconPO po = IconConverter.toPO(dto);
		Long rt = iconWriteManage.insertIconWithTx(po);		
		return rt;
	}

	@Override
	public int updateIconWithTx(IconDTO dto) {
		IconPO po = IconConverter.toPO(dto);
		int rt = iconWriteManage.updateIconWithTx(po);		
		return rt;
	}

	@Override
	public int deleteIconWithTx(IconDTO dto) {
		IconPO po = IconConverter.toPO(dto);
		int rt = iconWriteManage.deleteIconWithTx(po);		
		return rt;
	}
}
	