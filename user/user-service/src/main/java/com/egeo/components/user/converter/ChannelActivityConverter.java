package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.condition.ChannelActivityCondition;
import com.egeo.components.user.dto.ChannelActivityDTO;
import com.egeo.components.user.po.ChannelActivityPO;
import com.egeo.components.user.vo.ChannelActivityVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-06 17:27:54
 */
public class ChannelActivityConverter {
	
		public static ChannelActivityDTO toDTO(ChannelActivityVO src) {
		if (src == null)
		return null;	
		ChannelActivityDTO tar = new ChannelActivityDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setShortCode(src.getShortCode());	
		tar.setPath(src.getPath());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setChannelName(src.getChannelName());
		tar.setChannelId(src.getChannelId());
		return tar;
	}

	public static ChannelActivityVO toVO(ChannelActivityDTO src) {
		if (src == null)
		return null;	
		ChannelActivityVO tar = new ChannelActivityVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setShortCode(src.getShortCode());	
		tar.setPath(src.getPath());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setChannelName(src.getChannelName());
		tar.setChannelId(src.getChannelId());
		return tar;
	}


	public static List<ChannelActivityVO> toVO(List<ChannelActivityDTO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelActivityVO> list = new ArrayList<ChannelActivityVO>();
		for (ChannelActivityDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	
	public static ChannelActivityDTO toDTO(ChannelActivityPO src) {
		if (src == null)
		return null;	
		ChannelActivityDTO tar = new ChannelActivityDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setShortCode(src.getShortCode());
		tar.setPath(src.getPath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setChannelId(src.getChannelId());
		return tar;
	}

	public static ChannelActivityPO toPO(ChannelActivityDTO src) {
		if (src == null)
		return null;	
		ChannelActivityPO tar = new ChannelActivityPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setShortCode(src.getShortCode());
		tar.setPath(src.getPath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setChannelId(src.getChannelId());
		return tar;
	}
	
	public static ChannelActivityCondition toCondition(ChannelActivityDTO src) {
		if (src == null)
		return null;	
		ChannelActivityCondition tar = new ChannelActivityCondition();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setShortCode(src.getShortCode());
		tar.setPath(src.getPath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setChannelName(src.getChannelName());
		tar.setChannelId(src.getChannelId());
		return tar;
	}

	public static List<ChannelActivityDTO> voToDTO(List<ChannelActivityVO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelActivityDTO> list = new ArrayList<ChannelActivityDTO>();
		for (ChannelActivityVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	public static List<ChannelActivityDTO> toDTO(List<ChannelActivityPO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelActivityDTO> list = new ArrayList<ChannelActivityDTO>();
		for (ChannelActivityPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ChannelActivityPO> toPO(List<ChannelActivityDTO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelActivityPO> list = new ArrayList<ChannelActivityPO>();
		for (ChannelActivityDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	