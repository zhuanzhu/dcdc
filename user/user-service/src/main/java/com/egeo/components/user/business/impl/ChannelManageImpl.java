package com.egeo.components.user.business.impl;
	

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.egeo.components.user.business.ChannelManage;
import com.egeo.components.user.converter.ChannelConverter;
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.dto.VersionsDTO;
import com.egeo.components.user.service.read.ChannelActivityReadService;
import com.egeo.components.user.service.read.ChannelReadService;
import com.egeo.components.user.service.read.VersionsReadService;
import com.egeo.components.user.service.write.ChannelActivityWriteService;
import com.egeo.components.user.service.write.ChannelWriteService;
import com.egeo.components.user.service.write.VersionsWriteService;
import com.egeo.components.user.vo.ChannelVO;
import com.egeo.exception.BusinessException;
import com.egeo.orm.PageResult;
import com.egeo.orm.Pagination;
import com.egeo.utils.StringUtils;

@Service("channel")
public class ChannelManageImpl implements ChannelManage{

	
	@Resource(name="channelReadService")
	private ChannelReadService channelReadService;
	
	@Resource(name="versionsReadService")
	private VersionsReadService versionsReadService;
	
	@Resource(name="channelActivityReadService")
	private ChannelActivityReadService channelActivityReadService;
	
	

	@Resource(name="channelWriteService")
	private ChannelWriteService channelWriteService;
	
	@Resource(name="versionsWriteService")
	private VersionsWriteService versionsWriteService;
	
	@Resource(name="channelActivityWriteService")
	private ChannelActivityWriteService channelActivityWriteService;

	@Override
	public Map<String, Object> findChannelById(ChannelDTO dto) {
		Map<String, Object> map = new HashMap<>();
		ChannelDTO channelDTO = channelReadService.findChannelById(dto);
		map.put("id", channelDTO.getId());
		map.put("name", channelDTO.getName());
		map.put("type", channelDTO.getType());
		map.put("shortCode", channelDTO.getShortCode());
		map.put("path", channelDTO.getPath());
		
		ChannelActivityDTO channelActivityDTO = new ChannelActivityDTO();
		channelActivityDTO.setChannelId(channelDTO.getId());
		List<ChannelActivityDTO> channelActivityList = channelActivityReadService.findChannelActivityAll(channelActivityDTO);
		map.put("channelActivityName", channelActivityList.get(0).getName());
		map.put("channelActivityshortCode", channelActivityList.get(0).getShortCode());
		return map;
	}

	@Override
	public PageResult<Map<String, Object>> findChannelOfPage(ChannelDTO dto, Pagination page) {
		PageResult<ChannelDTO> rt = channelReadService.findChannelOfPage(dto, page);
		List<ChannelDTO> channelList = rt.getList();
		List<Map<String, Object>> list = new ArrayList<>();
		for (ChannelDTO channelDTO : channelList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", channelDTO.getId());
			map.put("name", channelDTO.getName());
			map.put("type", channelDTO.getType());
			map.put("shortCode", channelDTO.getShortCode());
			map.put("path", channelDTO.getPath());
			
			ChannelActivityDTO channelActivityDTO = new ChannelActivityDTO();
			channelActivityDTO.setChannelId(channelDTO.getId());
			List<ChannelActivityDTO> channelActivityList = channelActivityReadService.findChannelActivityAll(channelActivityDTO);
			map.put("channelActivityName", channelActivityList.get(0).getName());
			map.put("channelActivityshortCode", channelActivityList.get(0).getShortCode());
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
	public List<Map<String, Object>> findChannelAll(ChannelDTO dto) {
		List<ChannelDTO> channelList = channelReadService.findChannelAll(dto);
		List<Map<String, Object>> list = new ArrayList<>(channelList.size());
		for (ChannelDTO channelDTO : channelList) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", channelDTO.getId());
			map.put("name", channelDTO.getName());
			map.put("shortCode", channelDTO.getShortCode());
			list.add(map);
		}
		return list;
	}

	@Override
	public Long insertChannelWithTx(ChannelDTO dto) {
		//验证
		verificationNonEmpty(dto);
		return channelWriteService.insertChannelWithTx(dto);
	}

	@Override
	public int updateChannelWithTx(ChannelDTO dto) {
		//验证
		if(StringUtils.isEmpty(dto.getId())){
			throw new BusinessException("渠道id不能为空");
		}
		verificationNonEmpty(dto);
		return channelWriteService.updateChannelWithTx(dto);
	}

	@Override
	public int deleteChannelWithTx(ChannelDTO dto) {
		return channelWriteService.deleteChannelWithTx(dto);
	}
	/**
	 * 根据版本类型：1、安卓 2、ios查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findChannelByType(int type) {
		List<Map<String, Object>> maps = new ArrayList<>();
		List<ChannelDTO> channelList = channelReadService.findChannelByType(type);
		for (ChannelDTO channelDTO : channelList) {
			Map<String, Object> map = new HashMap<>();
			map.put("channelId", channelDTO.getId());
			map.put("name", channelDTO.getName());
			maps.add(map);
		}
		return maps;
	}
	/**
	 * 根据版本id查询渠道信息
	 * @param vo
	 * @param req
	 * @return
	 */
	@Override
	public List<Map<String, Object>> findChannelByVersionsId(Long versionsId) {
		//根据版本id查询版本信息
		VersionsDTO versionsDTO = new VersionsDTO();
		versionsDTO.setId(versionsId);
		VersionsDTO versionsDTO2 = versionsReadService.findVersionsById(versionsDTO);
		List<Map<String, Object>> maps = new ArrayList<>();
		List<ChannelDTO> channelList = channelReadService.findChannelByType(versionsDTO2.getType());
		for (ChannelDTO channelDTO : channelList) {
			Map<String, Object> map = new HashMap<>();
			map.put("channelId", channelDTO.getId());
			map.put("name", channelDTO.getName());
			maps.add(map);
		}
		return maps;
	}

	@Override
	public List<ChannelVO> findChannelByPlatformId(Long platformId) {
		ChannelDTO channelDTO = new ChannelDTO();
		channelDTO.setPlatformId(platformId);
		List<ChannelDTO> channelDTOList=channelReadService.findChannelAll(channelDTO);
		return ChannelConverter.toVO(channelDTOList);
	}

	private void verificationNonEmpty(ChannelDTO dto){
		if(StringUtils.isEmpty(dto.getName())){
			throw new BusinessException("请输入渠道名称");
		}
		if(StringUtils.isEmpty(dto.getType())){
			throw new BusinessException("请选择渠道类型");
		}
		if(StringUtils.isEmpty(dto.getShortCode())){
			throw new BusinessException("请输入渠道短码");
		}
		if(StringUtils.isEmpty(dto.getPath())){
			throw new BusinessException("请输入渠道链接");
		}
		if(StringUtils.isEmpty(dto.getChannelActivityName())){
			throw new BusinessException("请输入渠道活动名称");
		}
	}

}
	