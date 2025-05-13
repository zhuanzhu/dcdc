package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.components.user.service.read.ChannelActivityReadService;
import com.egeo.components.user.service.write.ChannelActivityWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ChannelActivityFacade {
	
	@Resource
	private ChannelActivityReadService channelActivityReadService;
	
	@Resource
	private ChannelActivityWriteService channelActivityWriteService;
	
	
	public ChannelActivityDTO findChannelActivityById(ChannelActivityDTO dto){
		
		return channelActivityReadService.findChannelActivityById(dto);
	}

	public PageResult<ChannelActivityDTO> findChannelActivityOfPage(ChannelActivityDTO dto,Pagination page){
		
		return channelActivityReadService.findChannelActivityOfPage(dto, page);
		
	}

	public List<ChannelActivityDTO> findChannelActivityAll(ChannelActivityDTO dto){
		
		return channelActivityReadService.findChannelActivityAll(dto);
		
	}

	public Long insertChannelActivityWithTx(ChannelActivityDTO dto){
		
		return channelActivityWriteService.insertChannelActivityWithTx(dto);
	}

	public int updateChannelActivityWithTx(ChannelActivityDTO dto){
		
		return channelActivityWriteService.updateChannelActivityWithTx(dto);
	}

	public int deleteChannelActivityWithTx(ChannelActivityDTO dto){
		
		return channelActivityWriteService.deleteChannelActivityWithTx(dto);
		
	}

    public List<ChannelActivityDTO> findChannelActivityByChannelId(Long channelId, Long platformId) {
		ChannelActivityDTO channelActivityDTO = new ChannelActivityDTO();
		channelActivityDTO.setChannelId(channelId);
		channelActivityDTO.setPlatformId(platformId);
		return channelActivityReadService.findChannelActivityAll(channelActivityDTO);
	}
}
	