package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.NavigationBarLabelReadService;
import com.egeo.components.cms.manage.read.NavigationBarLabelReadManage;
import com.egeo.components.cms.converter.NavigationBarLabelConverter;
import com.egeo.components.cms.dto.NavigationBarLabelDTO;
import com.egeo.components.cms.po.NavigationBarLabelPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("navigationBarLabelReadService")
public class NavigationBarLabelReadServiceImpl  implements NavigationBarLabelReadService {
	@Autowired
	private NavigationBarLabelReadManage navigationBarLabelReadManage;

	@Override
	public NavigationBarLabelDTO findNavigationBarLabelById(NavigationBarLabelDTO dto) {
		NavigationBarLabelPO po = NavigationBarLabelConverter.toPO(dto);
		NavigationBarLabelPO list = navigationBarLabelReadManage.findNavigationBarLabelById(po);		
		return NavigationBarLabelConverter.toDTO(list);
	}

	@Override
	public PageResult<NavigationBarLabelDTO> findNavigationBarLabelOfPage(NavigationBarLabelDTO dto, Pagination page) {
		NavigationBarLabelPO po = NavigationBarLabelConverter.toPO(dto);
        PageResult<NavigationBarLabelPO> pageResult = navigationBarLabelReadManage.findNavigationBarLabelOfPage(po, page);
        
        List<NavigationBarLabelDTO> list = NavigationBarLabelConverter.toDTO(pageResult.getList());
        PageResult<NavigationBarLabelDTO> result = new PageResult<NavigationBarLabelDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<NavigationBarLabelDTO> findNavigationBarLabelAll(NavigationBarLabelDTO dto) {
		NavigationBarLabelPO po = NavigationBarLabelConverter.toPO(dto);
		List<NavigationBarLabelPO> list = navigationBarLabelReadManage.findNavigationBarLabelAll(po);		
		return NavigationBarLabelConverter.toDTO(list);
	}
}
	