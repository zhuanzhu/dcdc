package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitMembershipReadService;
import com.egeo.components.product.manage.read.StandardUnitMembershipReadManage;
import com.egeo.components.product.converter.StandardUnitMembershipConverter;
import com.egeo.components.product.dto.StandardUnitMembershipDTO;
import com.egeo.components.product.po.StandardUnitMembershipPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitMembershipReadService")
public class StandardUnitMembershipReadServiceImpl  implements StandardUnitMembershipReadService {
	@Autowired
	private StandardUnitMembershipReadManage standardUnitMembershipReadManage;

	@Override
	public StandardUnitMembershipDTO findStandardUnitMembershipById(StandardUnitMembershipDTO dto) {
		StandardUnitMembershipPO po = StandardUnitMembershipConverter.toPO(dto);
		StandardUnitMembershipPO list = standardUnitMembershipReadManage.findStandardUnitMembershipById(po);		
		return StandardUnitMembershipConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitMembershipDTO> findStandardUnitMembershipOfPage(StandardUnitMembershipDTO dto, Pagination page) {
		StandardUnitMembershipPO po = StandardUnitMembershipConverter.toPO(dto);
        PageResult<StandardUnitMembershipPO> pageResult = standardUnitMembershipReadManage.findStandardUnitMembershipOfPage(po, page);
        
        List<StandardUnitMembershipDTO> list = StandardUnitMembershipConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitMembershipDTO> result = new PageResult<StandardUnitMembershipDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitMembershipDTO> findStandardUnitMembershipAll(StandardUnitMembershipDTO dto) {
		StandardUnitMembershipPO po = StandardUnitMembershipConverter.toPO(dto);
		List<StandardUnitMembershipPO> list = standardUnitMembershipReadManage.findStandardUnitMembershipAll(po);		
		return StandardUnitMembershipConverter.toDTO(list);
	}
}
	