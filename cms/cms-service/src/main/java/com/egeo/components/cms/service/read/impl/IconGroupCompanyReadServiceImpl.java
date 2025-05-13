package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.IconGroupCompanyReadService;
import com.egeo.components.cms.manage.read.IconGroupCompanyReadManage;
import com.egeo.components.cms.converter.IconGroupCompanyConverter;
import com.egeo.components.cms.dto.IconGroupCompanyDTO;
import com.egeo.components.cms.po.IconGroupCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("iconGroupCompanyReadService")
public class IconGroupCompanyReadServiceImpl  implements IconGroupCompanyReadService {
	@Autowired
	private IconGroupCompanyReadManage iconGroupCompanyReadManage;

	@Override
	public IconGroupCompanyDTO findIconGroupCompanyById(IconGroupCompanyDTO dto) {
		IconGroupCompanyPO po = IconGroupCompanyConverter.toPO(dto);
		IconGroupCompanyPO list = iconGroupCompanyReadManage.findIconGroupCompanyById(po);		
		return IconGroupCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<IconGroupCompanyDTO> findIconGroupCompanyOfPage(IconGroupCompanyDTO dto, Pagination page) {
		IconGroupCompanyPO po = IconGroupCompanyConverter.toPO(dto);
        PageResult<IconGroupCompanyPO> pageResult = iconGroupCompanyReadManage.findIconGroupCompanyOfPage(po, page);
        
        List<IconGroupCompanyDTO> list = IconGroupCompanyConverter.toDTO(pageResult.getList());
        PageResult<IconGroupCompanyDTO> result = new PageResult<IconGroupCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<IconGroupCompanyDTO> findIconGroupCompanyAll(IconGroupCompanyDTO dto) {
		IconGroupCompanyPO po = IconGroupCompanyConverter.toPO(dto);
		List<IconGroupCompanyPO> list = iconGroupCompanyReadManage.findIconGroupCompanyAll(po);		
		return IconGroupCompanyConverter.toDTO(list);
	}
}
	