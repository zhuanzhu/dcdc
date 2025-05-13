package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.NavigationBarCompanyWriteService;
import com.egeo.components.cms.manage.write.NavigationBarCompanyWriteManage;
import com.egeo.components.cms.converter.NavigationBarCompanyConverter;
import com.egeo.components.cms.dto.NavigationBarCompanyDTO;
import com.egeo.components.cms.po.NavigationBarCompanyPO;

@Service("navigationBarCompanyWriteService")
public class NavigationBarCompanyWriteServiceImpl  implements NavigationBarCompanyWriteService {
	@Autowired
	private NavigationBarCompanyWriteManage navigationBarCompanyWriteManage;

	@Override
	public Long insertNavigationBarCompanyWithTx(NavigationBarCompanyDTO dto) {
		NavigationBarCompanyPO po = NavigationBarCompanyConverter.toPO(dto);
		Long rt = navigationBarCompanyWriteManage.insertNavigationBarCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int updateNavigationBarCompanyWithTx(NavigationBarCompanyDTO dto) {
		NavigationBarCompanyPO po = NavigationBarCompanyConverter.toPO(dto);
		int rt = navigationBarCompanyWriteManage.updateNavigationBarCompanyWithTx(po);		
		return rt;
	}

	@Override
	public int deleteNavigationBarCompanyWithTx(NavigationBarCompanyDTO dto) {
		NavigationBarCompanyPO po = NavigationBarCompanyConverter.toPO(dto);
		int rt = navigationBarCompanyWriteManage.deleteNavigationBarCompanyWithTx(po);		
		return rt;
	}
}
	