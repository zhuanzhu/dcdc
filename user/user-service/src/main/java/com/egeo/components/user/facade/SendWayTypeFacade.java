package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.SendWayTypeDTO;
import com.egeo.components.user.service.read.SendWayTypeReadService;
import com.egeo.components.user.service.write.SendWayTypeWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class SendWayTypeFacade {
	
	@Resource
	private SendWayTypeReadService sendWayTypeReadService;
	
	@Resource
	private SendWayTypeWriteService sendWayTypeWriteService;
	
	
	public SendWayTypeDTO findSendWayTypeById(SendWayTypeDTO dto){
		
		return sendWayTypeReadService.findSendWayTypeById(dto);
	}

	public PageResult<SendWayTypeDTO> findSendWayTypeOfPage(SendWayTypeDTO dto,Pagination page){
		
		return sendWayTypeReadService.findSendWayTypeOfPage(dto, page);
		
	}

	public List<SendWayTypeDTO> findSendWayTypeAll(SendWayTypeDTO dto){
		
		return sendWayTypeReadService.findSendWayTypeAll(dto);
		
	}

	public Long insertSendWayTypeWithTx(SendWayTypeDTO dto){
		
		return sendWayTypeWriteService.insertSendWayTypeWithTx(dto);
	}

	public int updateSendWayTypeWithTx(SendWayTypeDTO dto){
		
		return sendWayTypeWriteService.updateSendWayTypeWithTx(dto);
	}

	public int deleteSendWayTypeWithTx(SendWayTypeDTO dto){
		
		return sendWayTypeWriteService.deleteSendWayTypeWithTx(dto);
		
	}

}
	