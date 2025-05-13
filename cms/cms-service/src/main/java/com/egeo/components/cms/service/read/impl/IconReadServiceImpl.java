package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.IconReadService;
import com.egeo.components.cms.manage.read.IconReadManage;
import com.egeo.components.cms.converter.IconConverter;
import com.egeo.components.cms.dto.IconDTO;
import com.egeo.components.cms.po.IconPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("iconReadService")
public class IconReadServiceImpl  implements IconReadService {
	@Autowired
	private IconReadManage iconReadManage;

	@Override
	public IconDTO findIconById(IconDTO dto) {
		IconPO po = IconConverter.toPO(dto);
		IconPO list = iconReadManage.findIconById(po);		
		return IconConverter.toDTO(list);
	}

	@Override
	public PageResult<IconDTO> findIconOfPage(IconDTO dto, Pagination page) {
		IconPO po = IconConverter.toPO(dto);
        PageResult<IconPO> pageResult = iconReadManage.findIconOfPage(po, page);
        
        List<IconDTO> list = IconConverter.toDTO(pageResult.getList());
        PageResult<IconDTO> result = new PageResult<IconDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<IconDTO> findIconAll(IconDTO dto) {
		IconPO po = IconConverter.toPO(dto);
		List<IconPO> list = iconReadManage.findIconAll(po);		
		return IconConverter.toDTO(list);
	}

	@Override
	public List<IconDTO> queryIconsByGroupId(Long groupId) {
		return IconConverter.toDTO(iconReadManage.queryIconsByGroupId(groupId));
	}
}
	