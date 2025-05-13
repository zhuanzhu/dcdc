package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitRecordMembershipManage;
import com.egeo.components.product.facade.StandardUnitRecordMembershipFacade;
import com.egeo.components.product.dto.StandardUnitRecordMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitRecordMembership")
public class StandardUnitRecordMembershipManageImpl implements StandardUnitRecordMembershipManage{

	
	@Resource(name="standardUnitRecordMembershipFacade")
	private StandardUnitRecordMembershipFacade standardUnitRecordMembershipFacade;

	@Override
	public StandardUnitRecordMembershipDTO findStandardUnitRecordMembershipById(StandardUnitRecordMembershipDTO dto) {
		return standardUnitRecordMembershipFacade.findStandardUnitRecordMembershipById(dto);
	}

	@Override
	public PageResult<StandardUnitRecordMembershipDTO> findStandardUnitRecordMembershipOfPage(StandardUnitRecordMembershipDTO dto, Pagination page) {
		return standardUnitRecordMembershipFacade.findStandardUnitRecordMembershipOfPage(dto, page);
	}

	@Override
	public List<StandardUnitRecordMembershipDTO> findStandardUnitRecordMembershipAll(StandardUnitRecordMembershipDTO dto) {
		return standardUnitRecordMembershipFacade.findStandardUnitRecordMembershipAll(dto);
	}

	@Override
	public Long insertStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto) {
		return standardUnitRecordMembershipFacade.insertStandardUnitRecordMembershipWithTx(dto);
	}

	@Override
	public int updateStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto) {
		return standardUnitRecordMembershipFacade.updateStandardUnitRecordMembershipWithTx(dto);
	}

	@Override
	public int deleteStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto) {
		return standardUnitRecordMembershipFacade.deleteStandardUnitRecordMembershipWithTx(dto);
	}


}
	