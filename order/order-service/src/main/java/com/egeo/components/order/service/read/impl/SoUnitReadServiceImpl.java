package com.egeo.components.order.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.order.service.read.SoUnitReadService;
import com.egeo.components.order.manage.read.SoUnitReadManage;
import com.egeo.components.order.converter.SoUnitConverter;
import com.egeo.components.order.dto.SoUnitDTO;
import com.egeo.components.order.po.SoUnitPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("soUnitReadService")
public class SoUnitReadServiceImpl  implements SoUnitReadService {
	@Autowired
	private SoUnitReadManage soUnitReadManage;

	@Override
	public SoUnitDTO findSoUnitById(SoUnitDTO dto) {
		SoUnitPO po = SoUnitConverter.toPO(dto);
		SoUnitPO list = soUnitReadManage.findSoUnitById(po);		
		return SoUnitConverter.toDTO(list);
	}

	@Override
	public PageResult<SoUnitDTO> findSoUnitOfPage(SoUnitDTO dto, Pagination page) {
		SoUnitPO po = SoUnitConverter.toPO(dto);
        PageResult<SoUnitPO> pageResult = soUnitReadManage.findSoUnitOfPage(po, page);
        
        List<SoUnitDTO> list = SoUnitConverter.toDTO(pageResult.getList());
        PageResult<SoUnitDTO> result = new PageResult<SoUnitDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SoUnitDTO> findSoUnitAll(SoUnitDTO dto) {
		SoUnitPO po = SoUnitConverter.toPO(dto);
		List<SoUnitPO> list = soUnitReadManage.findSoUnitAll(po);		
		return SoUnitConverter.toDTO(list);
	}
}
	