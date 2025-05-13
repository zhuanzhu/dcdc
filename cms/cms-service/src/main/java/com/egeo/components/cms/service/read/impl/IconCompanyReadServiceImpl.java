package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.IconCompanyReadService;
import com.egeo.components.cms.manage.read.IconCompanyReadManage;
import com.egeo.components.cms.converter.IconCompanyConverter;
import com.egeo.components.cms.dto.IconCompanyDTO;
import com.egeo.components.cms.po.IconCompanyPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("iconCompanyReadService")
public class IconCompanyReadServiceImpl  implements IconCompanyReadService {
	@Autowired
	private IconCompanyReadManage iconCompanyReadManage;

	@Override
	public IconCompanyDTO findIconCompanyById(IconCompanyDTO dto) {
		IconCompanyPO po = IconCompanyConverter.toPO(dto);
		IconCompanyPO list = iconCompanyReadManage.findIconCompanyById(po);		
		return IconCompanyConverter.toDTO(list);
	}

	@Override
	public PageResult<IconCompanyDTO> findIconCompanyOfPage(IconCompanyDTO dto, Pagination page) {
		IconCompanyPO po = IconCompanyConverter.toPO(dto);
        PageResult<IconCompanyPO> pageResult = iconCompanyReadManage.findIconCompanyOfPage(po, page);
        
        List<IconCompanyDTO> list = IconCompanyConverter.toDTO(pageResult.getList());
        PageResult<IconCompanyDTO> result = new PageResult<IconCompanyDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<IconCompanyDTO> findIconCompanyAll(IconCompanyDTO dto) {
		IconCompanyPO po = IconCompanyConverter.toPO(dto);
		List<IconCompanyPO> list = iconCompanyReadManage.findIconCompanyAll(po);		
		return IconCompanyConverter.toDTO(list);
	}

	@Override
	public List<IconCompanyDTO> queryIconCompanysByIconIdAndCompanyId(Long id, Long companyId) {
		
		return IconCompanyConverter.toDTO(iconCompanyReadManage.queryIconCompanysByIconIdAndCompanyId(id,companyId));
	}
}
	