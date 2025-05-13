package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.NavigationLabelReadService;
import com.egeo.components.cms.manage.read.NavigationLabelReadManage;
import com.egeo.components.cms.converter.NavigationLabelConverter;
import com.egeo.components.cms.dto.NavigationLabelDTO;
import com.egeo.components.cms.po.NavigationLabelPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("navigationLabelReadService")
public class NavigationLabelReadServiceImpl  implements NavigationLabelReadService {
	@Autowired
	private NavigationLabelReadManage navigationLabelReadManage;

	@Override
	public NavigationLabelDTO findNavigationLabelById(NavigationLabelDTO dto) {
		NavigationLabelPO po = NavigationLabelConverter.toPO(dto);
		NavigationLabelPO list = navigationLabelReadManage.findNavigationLabelById(po);		
		return NavigationLabelConverter.toDTO(list);
	}

	@Override
	public PageResult<NavigationLabelDTO> findNavigationLabelOfPage(NavigationLabelDTO dto, Pagination page) {
		NavigationLabelPO po = NavigationLabelConverter.toPO(dto);
        PageResult<NavigationLabelPO> pageResult = navigationLabelReadManage.findNavigationLabelOfPage(po, page);
        
        List<NavigationLabelDTO> list = NavigationLabelConverter.toDTO(pageResult.getList());
        PageResult<NavigationLabelDTO> result = new PageResult<NavigationLabelDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<NavigationLabelDTO> findNavigationLabelAll(NavigationLabelDTO dto) {
		NavigationLabelPO po = NavigationLabelConverter.toPO(dto);
		List<NavigationLabelPO> list = navigationLabelReadManage.findNavigationLabelAll(po);		
		return NavigationLabelConverter.toDTO(list);
	}
}
	