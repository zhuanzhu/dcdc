package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.NavigationBarReadService;
import com.egeo.components.cms.manage.read.NavigationBarReadManage;
import com.egeo.components.cms.converter.NavigationBarConverter;
import com.egeo.components.cms.dto.NavigationBarDTO;
import com.egeo.components.cms.po.NavigationBarPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("navigationBarReadService")
public class NavigationBarReadServiceImpl  implements NavigationBarReadService {
	@Autowired
	private NavigationBarReadManage navigationBarReadManage;

	@Override
	public NavigationBarDTO findNavigationBarById(NavigationBarDTO dto) {
		NavigationBarPO po = NavigationBarConverter.toPO(dto);
		NavigationBarPO list = navigationBarReadManage.findNavigationBarById(po);		
		return NavigationBarConverter.toDTO(list);
	}

	@Override
	public PageResult<NavigationBarDTO> findNavigationBarOfPage(NavigationBarDTO dto, Pagination page) {
		NavigationBarPO po = NavigationBarConverter.toPO(dto);
        PageResult<NavigationBarPO> pageResult = navigationBarReadManage.findNavigationBarOfPage(po, page);
        
        List<NavigationBarDTO> list = NavigationBarConverter.toDTO(pageResult.getList());
        PageResult<NavigationBarDTO> result = new PageResult<NavigationBarDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<NavigationBarDTO> findNavigationBarAll(NavigationBarDTO dto) {
		NavigationBarPO po = NavigationBarConverter.toPO(dto);
		List<NavigationBarPO> list = navigationBarReadManage.findNavigationBarAll(po);		
		return NavigationBarConverter.toDTO(list);
	}

	@Override
	public PageResult<NavigationBarDTO> findNavigationBarOfPageByBlurry(NavigationBarDTO dto, Pagination page) {
		NavigationBarPO po = NavigationBarConverter.toPO(dto);
        PageResult<NavigationBarPO> pageResult = navigationBarReadManage.findNavigationBarOfPageByBlurry(po, page);
        
        List<NavigationBarDTO> list = NavigationBarConverter.toDTO(pageResult.getList());
        PageResult<NavigationBarDTO> result = new PageResult<NavigationBarDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}
}
	