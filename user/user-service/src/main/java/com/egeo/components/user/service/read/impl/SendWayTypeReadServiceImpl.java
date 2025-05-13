package com.egeo.components.user.service.read.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.egeo.components.user.service.read.SendWayTypeReadService;
import com.egeo.components.user.dto.SendWayTypeDTO;
import com.egeo.components.user.po.SendWayTypePO;
import com.egeo.components.user.dao.read.SendWayTypeReadDAO;
import com.egeo.components.user.converter.SendWayTypeConverter;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("sendWayTypeReadService")
public class SendWayTypeReadServiceImpl implements SendWayTypeReadService {
	@Autowired
	private SendWayTypeReadDAO sendWayTypeReadDAO ;

	@Override
	public SendWayTypeDTO findSendWayTypeById(SendWayTypeDTO dto){
		SendWayTypePO po = SendWayTypeConverter.toPO(dto);
		SendWayTypePO sendWayTypepo = new SendWayTypePO();
		sendWayTypepo.setId(po.getId());
		SendWayTypePO list = sendWayTypeReadDAO.findById(sendWayTypepo);
		return SendWayTypeConverter.toDTO(list);
	}
	@Override
	public PageResult<SendWayTypeDTO> findSendWayTypeOfPage(SendWayTypeDTO dto, Pagination page) {
		SendWayTypePO po = SendWayTypeConverter.toPO(dto);
		PageResult<SendWayTypePO> pageResult = new PageResult<SendWayTypePO>();
		List<SendWayTypePO> listT = null;
		int cnt = sendWayTypeReadDAO.countOfPage(po);
		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = sendWayTypeReadDAO.findOfPage(po, page);
		} else {
			listT = new ArrayList<SendWayTypePO>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<SendWayTypeDTO> list = SendWayTypeConverter.toDTO(pageResult.getList());
        PageResult<SendWayTypeDTO> result = new PageResult<SendWayTypeDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<SendWayTypeDTO> findSendWayTypeAll(SendWayTypeDTO dto) {
		SendWayTypePO po = SendWayTypeConverter.toPO(dto);
		List<SendWayTypePO> list = sendWayTypeReadDAO.findAll(po,null);
		return SendWayTypeConverter.toDTO(list);
	}

	@Override
	public List<String> findSendWayTypeByInfoId(Long infoId) {
		return sendWayTypeReadDAO.findSendWayTypeByInfoId(infoId);
	}
}
	