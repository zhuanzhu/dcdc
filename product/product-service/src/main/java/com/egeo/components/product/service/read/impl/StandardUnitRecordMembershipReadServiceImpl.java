package com.egeo.components.product.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.product.service.read.StandardUnitRecordMembershipReadService;
import com.egeo.components.product.manage.read.StandardUnitRecordMembershipReadManage;
import com.egeo.components.product.converter.StandardUnitRecordMembershipConverter;
import com.egeo.components.product.dto.StandardUnitRecordMembershipDTO;
import com.egeo.components.product.po.StandardUnitRecordMembershipPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitRecordMembershipReadService")
public class StandardUnitRecordMembershipReadServiceImpl  implements StandardUnitRecordMembershipReadService {
	@Autowired
	private StandardUnitRecordMembershipReadManage standardUnitRecordMembershipReadManage;

	@Override
	public StandardUnitRecordMembershipDTO findStandardUnitRecordMembershipById(StandardUnitRecordMembershipDTO dto) {
		StandardUnitRecordMembershipPO po = StandardUnitRecordMembershipConverter.toPO(dto);
		StandardUnitRecordMembershipPO list = standardUnitRecordMembershipReadManage.findStandardUnitRecordMembershipById(po);		
		return StandardUnitRecordMembershipConverter.toDTO(list);
	}

	@Override
	public PageResult<StandardUnitRecordMembershipDTO> findStandardUnitRecordMembershipOfPage(StandardUnitRecordMembershipDTO dto, Pagination page) {
		StandardUnitRecordMembershipPO po = StandardUnitRecordMembershipConverter.toPO(dto);
        PageResult<StandardUnitRecordMembershipPO> pageResult = standardUnitRecordMembershipReadManage.findStandardUnitRecordMembershipOfPage(po, page);
        
        List<StandardUnitRecordMembershipDTO> list = StandardUnitRecordMembershipConverter.toDTO(pageResult.getList());
        PageResult<StandardUnitRecordMembershipDTO> result = new PageResult<StandardUnitRecordMembershipDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<StandardUnitRecordMembershipDTO> findStandardUnitRecordMembershipAll(StandardUnitRecordMembershipDTO dto) {
		StandardUnitRecordMembershipPO po = StandardUnitRecordMembershipConverter.toPO(dto);
		List<StandardUnitRecordMembershipPO> list = standardUnitRecordMembershipReadManage.findStandardUnitRecordMembershipAll(po);		
		return StandardUnitRecordMembershipConverter.toDTO(list);
	}
}
	