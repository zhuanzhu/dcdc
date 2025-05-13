package com.egeo.components.order.business;

import java.util.List;
	
import com.egeo.components.order.dto.SoChildFlowDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SoChildFlowManage {

	public SoChildFlowDTO findSoChildFlowById(SoChildFlowDTO dto);	

	public PageResult<SoChildFlowDTO> findSoChildFlowOfPage(SoChildFlowDTO dto,Pagination page);

	public List<SoChildFlowDTO> findSoChildFlowAll(SoChildFlowDTO dto);

	Long insertSoChildFlowWithTx(SoChildFlowDTO dto);

	int updateSoChildFlowWithTx(SoChildFlowDTO dto);

	int deleteSoChildFlowWithTx(SoChildFlowDTO dto);
}
	