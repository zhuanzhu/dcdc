package com.egeo.components.product.business;

import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitRecordMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface StandardUnitRecordMembershipManage {

	public StandardUnitRecordMembershipDTO findStandardUnitRecordMembershipById(StandardUnitRecordMembershipDTO dto);	

	public PageResult<StandardUnitRecordMembershipDTO> findStandardUnitRecordMembershipOfPage(StandardUnitRecordMembershipDTO dto,Pagination page);

	public List<StandardUnitRecordMembershipDTO> findStandardUnitRecordMembershipAll(StandardUnitRecordMembershipDTO dto);

	Long insertStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto);

	int updateStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto);

	int deleteStandardUnitRecordMembershipWithTx(StandardUnitRecordMembershipDTO dto);
}
	