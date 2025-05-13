package com.egeo.components.product.business.impl;
	

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.egeo.components.product.business.StandardUnitMembershipManage;
import com.egeo.components.product.facade.StandardUnitMembershipFacade;
import com.egeo.components.product.dto.StandardUnitMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("standardUnitMembership")
public class StandardUnitMembershipManageImpl implements StandardUnitMembershipManage{

	
	@Resource(name="standardUnitMembershipFacade")
	private StandardUnitMembershipFacade standardUnitMembershipFacade;

	@Override
	public StandardUnitMembershipDTO findStandardUnitMembershipById(StandardUnitMembershipDTO dto) {
		return standardUnitMembershipFacade.findStandardUnitMembershipById(dto);
	}

	@Override
	public PageResult<StandardUnitMembershipDTO> findStandardUnitMembershipOfPage(StandardUnitMembershipDTO dto, Pagination page) {
		return standardUnitMembershipFacade.findStandardUnitMembershipOfPage(dto, page);
	}

	@Override
	public List<StandardUnitMembershipDTO> findStandardUnitMembershipAll(StandardUnitMembershipDTO dto) {
		return standardUnitMembershipFacade.findStandardUnitMembershipAll(dto);
	}

	@Override
	public Long insertStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto) {
		return standardUnitMembershipFacade.insertStandardUnitMembershipWithTx(dto);
	}

	@Override
	public int updateStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto) {
		return standardUnitMembershipFacade.updateStandardUnitMembershipWithTx(dto);
	}

	@Override
	public int deleteStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto) {
		return standardUnitMembershipFacade.deleteStandardUnitMembershipWithTx(dto);
	}


}
	