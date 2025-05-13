package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.NavigationBarLabelWriteService;
import com.egeo.components.cms.manage.write.NavigationBarLabelWriteManage;
import com.egeo.components.cms.converter.NavigationBarLabelConverter;
import com.egeo.components.cms.dto.NavigationBarLabelDTO;
import com.egeo.components.cms.po.NavigationBarLabelPO;

@Service("navigationBarLabelWriteService")
public class NavigationBarLabelWriteServiceImpl  implements NavigationBarLabelWriteService {
	@Autowired
	private NavigationBarLabelWriteManage navigationBarLabelWriteManage;

	@Override
	public Long insertNavigationBarLabelWithTx(NavigationBarLabelDTO dto) {
		NavigationBarLabelPO po = NavigationBarLabelConverter.toPO(dto);
		Long rt = navigationBarLabelWriteManage.insertNavigationBarLabelWithTx(po);		
		return rt;
	}

	@Override
	public int updateNavigationBarLabelWithTx(NavigationBarLabelDTO dto) {
		NavigationBarLabelPO po = NavigationBarLabelConverter.toPO(dto);
		int rt = navigationBarLabelWriteManage.updateNavigationBarLabelWithTx(po);		
		return rt;
	}

	@Override
	public int deleteNavigationBarLabelWithTx(NavigationBarLabelDTO dto) {
		NavigationBarLabelPO po = NavigationBarLabelConverter.toPO(dto);
		int rt = navigationBarLabelWriteManage.deleteNavigationBarLabelWithTx(po);		
		return rt;
	}
}
	