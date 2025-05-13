package com.egeo.components.config.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.config.converter.PolymallUserConverter;
import com.egeo.components.config.dto.PolymallUserDTO;
import com.egeo.components.config.manage.read.PolymallUserReadManage;
import com.egeo.components.config.po.PolymallUserPO;
import com.egeo.components.config.service.read.PolymallUserReadService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("polymallUserReadService")
public class PolymallUserReadServiceImpl implements PolymallUserReadService {
	@Autowired
	private PolymallUserReadManage polymallUserReadManage;

	@Override
	public PolymallUserDTO findPolymallUserById(PolymallUserDTO dto) {
		PolymallUserPO po = PolymallUserConverter.toPO(dto);
		PolymallUserPO list = polymallUserReadManage.findPolymallUserById(po);		
		return PolymallUserConverter.toDTO(list);
	}

	@Override
	public PageResult<PolymallUserDTO> findPolymallUserOfPage(PolymallUserDTO dto, Pagination page) {
		PolymallUserPO po = PolymallUserConverter.toPO(dto);
        PageResult<PolymallUserPO> pageResult = polymallUserReadManage.findPolymallUserOfPage(po, page);
        
        List<PolymallUserDTO> list = PolymallUserConverter.toDTO(pageResult.getList());
        PageResult<PolymallUserDTO> result = new PageResult<PolymallUserDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<PolymallUserDTO> findPolymallUserAll(PolymallUserDTO dto) {
		PolymallUserPO po = PolymallUserConverter.toPO(dto);
		List<PolymallUserPO> list = polymallUserReadManage.findPolymallUserAll(po);		
		return PolymallUserConverter.toDTO(list);
	}
}
	