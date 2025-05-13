package com.egeo.components.cms.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.cms.service.read.IconGroupReadService;
import com.egeo.components.cms.manage.read.IconGroupReadManage;
import com.egeo.components.cms.converter.IconGroupConverter;
import com.egeo.components.cms.dto.IconGroupDTO;
import com.egeo.components.cms.po.IconGroupPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("iconGroupReadService")
public class IconGroupReadServiceImpl  implements IconGroupReadService {
	@Autowired
	private IconGroupReadManage iconGroupReadManage;

	@Override
	public IconGroupDTO findIconGroupById(IconGroupDTO dto) {
		IconGroupPO po = IconGroupConverter.toPO(dto);
		IconGroupPO list = iconGroupReadManage.findIconGroupById(po);		
		return IconGroupConverter.toDTO(list);
	}

	@Override
	public PageResult<IconGroupDTO> findIconGroupOfPage(IconGroupDTO dto, Pagination page) {
		IconGroupPO po = IconGroupConverter.toPO(dto);
        PageResult<IconGroupPO> pageResult = iconGroupReadManage.findIconGroupOfPage(po, page);
        
        List<IconGroupDTO> list = IconGroupConverter.toDTO(pageResult.getList());
        PageResult<IconGroupDTO> result = new PageResult<IconGroupDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<IconGroupDTO> findIconGroupAll(IconGroupDTO dto) {
		IconGroupPO po = IconGroupConverter.toPO(dto);
		List<IconGroupPO> list = iconGroupReadManage.findIconGroupAll(po);		
		return IconGroupConverter.toDTO(list);
	}

	@Override
	public IconGroupDTO queryIconGroupByInstId(Long instId) {
		
		return IconGroupConverter.toDTO(iconGroupReadManage.queryIconGroupByInstId(instId));
	}
}
	