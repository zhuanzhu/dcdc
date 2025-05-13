package com.egeo.components.user.facade;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.service.read.ChannelReadService;
import com.egeo.components.user.service.write.ChannelWriteService;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;


@Component
public class ChannelFacade {
	
	public static final String SHORTZ_CODE = "YYB002";
	
	@Resource
	private ChannelReadService channelReadService;
	
	@Resource
	private ChannelWriteService channelWriteService;
	
	public ChannelDTO findChannelById(ChannelDTO dto){
		
		return channelReadService.findChannelById(dto);
	}

	public PageResult<ChannelDTO> findChannelOfPage(ChannelDTO dto,Pagination page){
		
		return channelReadService.findChannelOfPage(dto, page);
		
	}

	public List<ChannelDTO> findChannelAll(ChannelDTO dto){
		
		return channelReadService.findChannelAll(dto);
		
	}

	public Long insertChannelWithTx(ChannelDTO dto){
		
		return channelWriteService.insertChannelWithTx(dto);
	}

	public int updateChannelWithTx(ChannelDTO dto){
		
		return channelWriteService.updateChannelWithTx(dto);
	}

	public int deleteChannelWithTx(ChannelDTO dto){
		
		return channelWriteService.deleteChannelWithTx(dto);
		
	}
	/**
	 * 根据版本类型：1、安卓 2、ios查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	public List<ChannelDTO> findChannelByType(int type) {
		return channelReadService.findChannelByType(type);
	}

    public List<ChannelDTO> findChannelByPlatformId(ChannelDTO dto) {
		return channelReadService.findChannelAll(dto);
    }

}
	