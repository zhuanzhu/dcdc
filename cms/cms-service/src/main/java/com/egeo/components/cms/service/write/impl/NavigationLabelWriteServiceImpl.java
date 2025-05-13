package com.egeo.components.cms.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.egeo.components.cms.service.write.NavigationLabelWriteService;
import com.egeo.components.cms.manage.write.NavigationLabelWriteManage;
import com.egeo.components.cms.converter.LinkableButtonConverter;
import com.egeo.components.cms.converter.NavigationLabelConverter;
import com.egeo.components.cms.dto.LinkableButtonDTO;
import com.egeo.components.cms.dto.NavigationLabelDTO;
import com.egeo.components.cms.po.LinkableButtonPO;
import com.egeo.components.cms.po.NavigationLabelPO;

@Service("navigationLabelWriteService")
public class NavigationLabelWriteServiceImpl  implements NavigationLabelWriteService {
	@Autowired
	private NavigationLabelWriteManage navigationLabelWriteManage;

	@Override
	public Long insertNavigationLabelWithTx(NavigationLabelDTO dto) {
		NavigationLabelPO po = NavigationLabelConverter.toPO(dto);
		Long rt = navigationLabelWriteManage.insertNavigationLabelWithTx(po);		
		return rt;
	}

	@Override
	public int updateNavigationLabelWithTx(NavigationLabelDTO dto) {
		NavigationLabelPO po = NavigationLabelConverter.toPO(dto);
		int rt = navigationLabelWriteManage.updateNavigationLabelWithTx(po);		
		return rt;
	}

	@Override
	public int deleteNavigationLabelWithTx(NavigationLabelDTO dto) {
		NavigationLabelPO po = NavigationLabelConverter.toPO(dto);
		int rt = navigationLabelWriteManage.deleteNavigationLabelWithTx(po);		
		return rt;
	}

	@Override
	public Long insertOrUpdateNavigationLableWithTx(NavigationLabelDTO dto, LinkableButtonDTO linkableButtonDTO) {
		NavigationLabelPO po = NavigationLabelConverter.toPO(dto);
		LinkableButtonPO LinkableButtonPO = LinkableButtonConverter.toPO(linkableButtonDTO);
		
		return navigationLabelWriteManage.insertOrUpdateNavigationLableWithTx(po, LinkableButtonPO);
	}
}
	