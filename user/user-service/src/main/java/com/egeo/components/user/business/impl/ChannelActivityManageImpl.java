package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.ChannelActivityManage;
import com.egeo.components.user.converter.ChannelActivityConverter;
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.components.user.service.read.ChannelActivityReadService;
import com.egeo.components.user.service.write.ChannelActivityWriteService;
import com.egeo.components.user.vo.ChannelActivityVO;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service("channelActivity")
public class ChannelActivityManageImpl implements ChannelActivityManage{

	
	@Resource(name="channelActivityReadService")
	private ChannelActivityReadService channelActivityReadService;
	@Resource(name="channelActivityWriteService")
	private ChannelActivityWriteService channelActivityWriteService;

	@Override
	public Map<String, Object> findChannelActivityById(Long channelActivityId) {
		ChannelActivityDTO channelActivityDTO = new ChannelActivityDTO();
		channelActivityDTO.setId(channelActivityId);
		ChannelActivityDTO channelActivity = channelActivityReadService.findChannelActivityById(channelActivityDTO);
		if(StringUtils.isNotEmpty(channelActivity)){
			Map<String, Object> map = new HashMap<>();
			map.put("id", channelActivity.getId());
			map.put("name", channelActivity.getName());
			map.put("channelId", channelActivity.getChannelId());
			return map;
		}
		return null;
	}

	@Override
	public PageResult<Map<String, Object>> findChannelActivityOfPage(ChannelActivityDTO dto, Pagination page) {
		PageResult<ChannelActivityDTO> rt = channelActivityReadService.findChannelActivityOfPage(dto, page);
		List<ChannelActivityDTO> channelActivityList = rt.getList();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>(channelActivityList.size());
		for (ChannelActivityDTO channelActivityDTO : channelActivityList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", channelActivityDTO.getId());
			map.put("name", channelActivityDTO.getName());
			map.put("shortCode", channelActivityDTO.getShortCode());
			map.put("type", channelActivityDTO.getType());
			map.put("channelName", channelActivityDTO.getChannelName());
			map.put("channelShortCode", channelActivityDTO.getChannelShortCode());
			map.put("path", channelActivityDTO.getPath());
			list.add(map);
		}
		PageResult<Map<String, Object>> result = new PageResult<Map<String, Object>>();
        result.setList(list);
        result.setPageNo(rt.getPageNo());
        result.setPageSize(rt.getPageSize());
        result.setTotalSize(rt.getTotalSize());
		return result;
	}

	@Override
	public List<ChannelActivityDTO> findChannelActivityAll(ChannelActivityDTO dto) {
		return channelActivityReadService.findChannelActivityAll(dto);
	}

	@Override
	public Long insertChannelActivityWithTx(ChannelActivityDTO dto) {
		return channelActivityWriteService.insertChannelActivityWithTx(dto);
	}

	@Override
	public int updateChannelActivityWithTx(ChannelActivityDTO dto) {
		return channelActivityWriteService.updateChannelActivityWithTx(dto);
	}

	@Override
	public int deleteChannelActivityWithTx(ChannelActivityDTO dto) {
		return channelActivityWriteService.deleteChannelActivityWithTx(dto);
	}

	@Override
	public List<ChannelActivityVO> findChannelActivityByChannelId(Long channelId, Long platformId) {

		ChannelActivityDTO channelActivityDTO = new ChannelActivityDTO();
		channelActivityDTO.setChannelId(channelId);
		channelActivityDTO.setPlatformId(platformId);
		List<ChannelActivityDTO> dtoList=channelActivityReadService.findChannelActivityAll(channelActivityDTO);
		return ChannelActivityConverter.toVO(dtoList);
	}


}
	