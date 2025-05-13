package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.NavigationBarWriteService;
import com.egeo.components.cms.manage.write.NavigationBarWriteManage;
import com.egeo.components.cms.converter.NavigationBarConverter;
import com.egeo.components.cms.dto.NavigationBarDTO;
import com.egeo.components.cms.po.NavigationBarPO;

@Service("navigationBarWriteService")
public class NavigationBarWriteServiceImpl  implements NavigationBarWriteService {
	@Autowired
	private NavigationBarWriteManage navigationBarWriteManage;

	@Override
	public Long insertNavigationBarWithTx(NavigationBarDTO dto) {
		NavigationBarPO po = NavigationBarConverter.toPO(dto);
		Long rt = navigationBarWriteManage.insertNavigationBarWithTx(po);		
		return rt;
	}

	@Override
	public int updateNavigationBarWithTx(NavigationBarDTO dto) {
		NavigationBarPO po = NavigationBarConverter.toPO(dto);
		int rt = navigationBarWriteManage.updateNavigationBarWithTx(po);		
		return rt;
	}

	@Override
	public int deleteNavigationBarWithTx(NavigationBarDTO dto) {
		NavigationBarPO po = NavigationBarConverter.toPO(dto);
		int rt = navigationBarWriteManage.deleteNavigationBarWithTx(po);		
		return rt;
	}
}
	