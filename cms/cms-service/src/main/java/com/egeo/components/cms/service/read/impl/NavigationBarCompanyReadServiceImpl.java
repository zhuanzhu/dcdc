package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.NavigationBarCompanyReadService;
import com.egeo.components.cms.manage.read.NavigationBarCompanyReadManage;
import com.egeo.components.cms.converter.NavigationBarCompanyConverter;
import com.egeo.components.cms.dto.NavigationBarCompanyDTO;
import com.egeo.components.cms.po.NavigationBarCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("navigationBarCompanyReadService")
public class NavigationBarCompanyReadServiceImpl  implements NavigationBarCompanyReadService {
	@Autowired
	private NavigationBarCompanyReadManage navigationBarCompanyReadManage;

	@Override
	public NavigationBarCompanyDTO findNavigationBarCompanyById(NavigationBarCompanyDTO dto) {
		NavigationBarCompanyPO po = NavigationBarCompanyConverter.toPO(dto);
		NavigationBarCompanyPO list = navigationBarCompanyReadManage.findNavigationBarCompanyById(po);
		return NavigationBarCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<NavigationBarCompanyDTO> findNavigationBarCompanyOfPage(NavigationBarCompanyDTO dto,
			Pagination page) {
		NavigationBarCompanyPO po = NavigationBarCompanyConverter.toPO(dto);
		PageResult<NavigationBarCompanyPO> pageResult = navigationBarCompanyReadManage
				.findNavigationBarCompanyOfPage(po, page);

		List<NavigationBarCompanyDTO> list = NavigationBarCompanyConverter.toDTO(pageResult.getList());
		PageResult<NavigationBarCompanyDTO> result = new PageResult<NavigationBarCompanyDTO>();
		result.setList(list);
		result.setPageNo(pageResult.getPageNo());
		result.setPageSize(pageResult.getPageSize());
		result.setTotalSize(pageResult.getTotalSize());
		return result;
	}

	@Override
	public List<NavigationBarCompanyDTO> findNavigationBarCompanyAll(NavigationBarCompanyDTO dto) {
		NavigationBarCompanyPO po = NavigationBarCompanyConverter.toPO(dto);
		List<NavigationBarCompanyPO> list = navigationBarCompanyReadManage.findNavigationBarCompanyAll(po);
		return NavigationBarCompanyConverter.toDTO(list);
	}

	@Override
	public List<NavigationBarCompanyDTO> findPageTabAllByCompanyId(List<Long> companyIdList) {

		return NavigationBarCompanyConverter
				.toDTO(navigationBarCompanyReadManage.findPageTabAllByCompanyId(companyIdList));
	}
}
