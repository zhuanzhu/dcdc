package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitClientReadService;
import com.egeo.components.product.manage.read.StandardUnitClientReadManage;
import com.egeo.components.product.converter.StandardUnitClientConverter;
import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.components.product.po.StandardUnitClientPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitClientReadService")
public class StandardUnitClientReadServiceImpl  implements StandardUnitClientReadService {
	@Autowired
	private StandardUnitClientReadManage standardUnitClientReadManage;

	@Override
	public StandardUnitClientDTO findStandardUnitClientById(StandardUnitClientDTO dto) {
		StandardUnitClientPO po = StandardUnitClientConverter.toPO(dto);
		StandardUnitClientPO list = standardUnitClientReadManage.findStandardUnitClientById(po);		
		return StandardUnitClientConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitClientDTO> findStandardUnitClientOfPage(StandardUnitClientDTO dto, Pagination page) {
		StandardUnitClientPO po = StandardUnitClientConverter.toPO(dto);
        PageResult<StandardUnitClientPO> pageResult = standardUnitClientReadManage.findStandardUnitClientOfPage(po, page);
        
        List<StandardUnitClientDTO> list = StandardUnitClientConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitClientDTO> result = new PageResult<StandardUnitClientDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitClientDTO> findStandardUnitClientAll(StandardUnitClientDTO dto) {
		StandardUnitClientPO po = StandardUnitClientConverter.toPO(dto);
		List<StandardUnitClientPO> list = standardUnitClientReadManage.findStandardUnitClientAll(po);		
		return StandardUnitClientConverter.toDTO(list);
	}
}
	