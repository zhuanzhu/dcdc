package com.egeo.components.user.service.read.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.egeo.components.user.service.read.ChannelActivityReadService;
import com.egeo.components.user.condition.ChannelActivityCondition;
import com.egeo.components.user.converter.ChannelActivityConverter;
import com.egeo.components.user.dao.read.ChannelActivityReadDAO;
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.components.user.po.ChannelActivityPO;

import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;

@Service("channelActivityReadService")
public class ChannelActivityReadServiceImpl implements ChannelActivityReadService {
	@Autowired
	private ChannelActivityReadDAO channelActivityReadDAO;

	@Override
	public ChannelActivityDTO findChannelActivityById(ChannelActivityDTO dto) {
		ChannelActivityPO po = ChannelActivityConverter.toPO(dto);
		ChannelActivityPO list = channelActivityReadDAO.findById(po);	
		return ChannelActivityConverter.toDTO(list);
	}
	@Override
	public PageResult<ChannelActivityDTO> findChannelActivityOfPage(ChannelActivityDTO dto, Pagination page) {
		ChannelActivityCondition po = ChannelActivityConverter.toCondition(dto);

		PageResult<ChannelActivityCondition> pageResult = new PageResult<ChannelActivityCondition>();
		List<ChannelActivityCondition> listT = null;

		int cnt = channelActivityReadDAO.countChannelActivityConditionOfPage(po);

		if (cnt >= (page.getPageNo() - 1) * page.getPageSize()) {
			page.setLimitStart((page.getPageNo() - 1) * page.getPageSize());
			listT = channelActivityReadDAO.findChannelActivityConditionOfPage(po, page);
		} else {
			listT = new ArrayList<ChannelActivityCondition>();
		}
		pageResult.setList(listT);
		pageResult.setTotalSize(cnt);
		pageResult.setPageNo(page.getPageNo());
		pageResult.setPageSize(page.getPageSize());
		
        List<ChannelActivityCondition> channelActivityConditionList = pageResult.getList();
        List<ChannelActivityDTO> list = new ArrayList<>(channelActivityConditionList.size());
        for (ChannelActivityCondition channelActivityCondition : channelActivityConditionList) {
        	ChannelActivityDTO channelActivityDTO = ChannelActivityConverter.toDTO(channelActivityCondition);
        	channelActivityDTO.setType(channelActivityCondition.getType());
        	channelActivityDTO.setChannelName(channelActivityCondition.getChannelName());
        	channelActivityDTO.setChannelShortCode(channelActivityCondition.getChannelShortCode());
        	list.add(channelActivityDTO);
		}
        PageResult<ChannelActivityDTO> result = new PageResult<ChannelActivityDTO>();
        result.setList(list);
        result.setPageNo(pageResult.getPageNo());
        result.setPageSize(pageResult.getPageSize());
        result.setTotalSize(pageResult.getTotalSize());
        return result;
	}

	@Override
	public List<ChannelActivityDTO> findChannelActivityAll(ChannelActivityDTO dto) {
		ChannelActivityPO po = ChannelActivityConverter.toPO(dto);
		List<ChannelActivityPO> list = channelActivityReadDAO.findAll(po,null);		
		return ChannelActivityConverter.toDTO(list);
	}

	@Override
	public ChannelActivityDTO findByShortCode(String shortCode) {
		ChannelActivityPO channelActivityPO = channelActivityReadDAO.findByShortCode(shortCode);
		return ChannelActivityConverter.toDTO(channelActivityPO);
	}
}
	