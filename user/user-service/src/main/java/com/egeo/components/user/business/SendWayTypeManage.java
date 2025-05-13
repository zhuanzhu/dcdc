package com.egeo.components.user.business;

import java.util.List;
import java.util.Map;

import com.egeo.components.user.dto.SendWayTypeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;	

public interface SendWayTypeManage {

	public SendWayTypeDTO findSendWayTypeById(SendWayTypeDTO dto);	

	public PageResult<SendWayTypeDTO> findSendWayTypeOfPage(SendWayTypeDTO dto,Pagination page);

	public List<Map<String, Object>> findSendWayTypeAll(SendWayTypeDTO dto);

	Long insertSendWayTypeWithTx(SendWayTypeDTO dto);

	int updateSendWayTypeWithTx(SendWayTypeDTO dto);

	int deleteSendWayTypeWithTx(SendWayTypeDTO dto);
}
	