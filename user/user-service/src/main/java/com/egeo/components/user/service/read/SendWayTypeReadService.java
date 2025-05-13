package com.egeo.components.user.service.read;


import java.util.List;
	
import com.egeo.components.user.dto.SendWayTypeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

public interface SendWayTypeReadService {

	public SendWayTypeDTO findSendWayTypeById(SendWayTypeDTO dto);

	public PageResult<SendWayTypeDTO> findSendWayTypeOfPage(SendWayTypeDTO dto,Pagination page);

	public List<SendWayTypeDTO> findSendWayTypeAll(SendWayTypeDTO dto);
	/**
	 * 根据消息id查询消息发送方式
	 * @param id
	 * @return
	 */
	public List<String> findSendWayTypeByInfoId(Long infoId);
}
	