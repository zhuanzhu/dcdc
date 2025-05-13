package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.SendWayTypeManage;
import com.egeo.components.user.facade.SendWayTypeFacade;
import com.egeo.components.user.dto.SendWayTypeDTO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sendWayType")
public class SendWayTypeManageImpl implements SendWayTypeManage{

	
	@Resource(name="sendWayTypeFacade")
	private SendWayTypeFacade sendWayTypeFacade;

	@Override
	public SendWayTypeDTO findSendWayTypeById(SendWayTypeDTO dto) {
		return sendWayTypeFacade.findSendWayTypeById(dto);
	}

	@Override
	public PageResult<SendWayTypeDTO> findSendWayTypeOfPage(SendWayTypeDTO dto, Pagination page) {
		return sendWayTypeFacade.findSendWayTypeOfPage(dto, page);
	}

	@Override
	public List<Map<String, Object>> findSendWayTypeAll(SendWayTypeDTO dto) {
		List<Map<String, Object>> maps = new ArrayList<>();
		List<SendWayTypeDTO> sendWayTypeList = sendWayTypeFacade.findSendWayTypeAll(dto);
		for (SendWayTypeDTO sendWayTypeDTO : sendWayTypeList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", sendWayTypeDTO.getId());
			map.put("name", sendWayTypeDTO.getName());
			maps.add(map);
		}
		return maps;
	}

	@Override
	public Long insertSendWayTypeWithTx(SendWayTypeDTO dto) {
		return sendWayTypeFacade.insertSendWayTypeWithTx(dto);
	}

	@Override
	public int updateSendWayTypeWithTx(SendWayTypeDTO dto) {
		return sendWayTypeFacade.updateSendWayTypeWithTx(dto);
	}

	@Override
	public int deleteSendWayTypeWithTx(SendWayTypeDTO dto) {
		return sendWayTypeFacade.deleteSendWayTypeWithTx(dto);
	}


}
	