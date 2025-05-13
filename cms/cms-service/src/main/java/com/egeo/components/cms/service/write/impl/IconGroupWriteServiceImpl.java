package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.IconGroupWriteService;
import com.egeo.components.cms.manage.write.IconGroupWriteManage;
import com.egeo.components.cms.converter.IconGroupConverter;
import com.egeo.components.cms.dto.IconGroupDTO;
import com.egeo.components.cms.po.IconGroupPO;

@Service("iconGroupWriteService")
public class IconGroupWriteServiceImpl  implements IconGroupWriteService {
	@Autowired
	private IconGroupWriteManage iconGroupWriteManage;

	@Override
	public Long insertIconGroupWithTx(IconGroupDTO dto) {
		IconGroupPO po = IconGroupConverter.toPO(dto);
		Long rt = iconGroupWriteManage.insertIconGroupWithTx(po);		
		return rt;
	}

	@Override
	public int updateIconGroupWithTx(IconGroupDTO dto) {
		IconGroupPO po = IconGroupConverter.toPO(dto);
		int rt = iconGroupWriteManage.updateIconGroupWithTx(po);		
		return rt;
	}

	@Override
	public int deleteIconGroupWithTx(IconGroupDTO dto) {
		IconGroupPO po = IconGroupConverter.toPO(dto);
		int rt = iconGroupWriteManage.deleteIconGroupWithTx(po);		
		return rt;
	}
}
	