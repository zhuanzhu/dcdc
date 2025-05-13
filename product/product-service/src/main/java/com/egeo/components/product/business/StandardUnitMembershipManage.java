package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitMembershipManage {

	public StandardUnitMembershipDTO findStandardUnitMembershipById(StandardUnitMembershipDTO dto);	

	public PageResult<StandardUnitMembershipDTO> findStandardUnitMembershipOfPage(StandardUnitMembershipDTO dto,Pagination page);

	public List<StandardUnitMembershipDTO> findStandardUnitMembershipAll(StandardUnitMembershipDTO dto);

	Long insertStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto);

	int updateStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto);

	int deleteStandardUnitMembershipWithTx(StandardUnitMembershipDTO dto);
}
	