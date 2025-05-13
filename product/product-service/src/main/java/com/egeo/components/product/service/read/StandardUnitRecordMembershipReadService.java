package com.egeo.components.product.service.read;


import java.util.List;
	
import com.egeo.components.product.dto.StandardUnitRecordMembershipDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface StandardUnitRecordMembershipReadService {

	public StandardUnitRecordMembershipDTO findStandardUnitRecordMembershipById(StandardUnitRecordMembershipDTO dto);

	public PageResult<StandardUnitRecordMembershipDTO> findStandardUnitRecordMembershipOfPage(StandardUnitRecordMembershipDTO dto,Pagination page);

	public List<StandardUnitRecordMembershipDTO> findStandardUnitRecordMembershipAll(StandardUnitRecordMembershipDTO dto);
}
	