package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.ChannelDTO;
import com.egeo.components.user.po.ChannelPO;
import com.egeo.components.user.vo.ChannelVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-02-04 16:05:48
 */
public class ChannelConverter {
	
	public static ChannelDTO toDTO(ChannelPO src) {
		if (src == null)
		return null;	
		ChannelDTO tar = new ChannelDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setShortCode(src.getShortCode());
		tar.setPath(src.getPath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setChannelActivityName(src.getChannelActivityName());
		return tar;
	}

	public static ChannelPO toPO(ChannelDTO src) {
		if (src == null)
		return null;	
		ChannelPO tar = new ChannelPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setShortCode(src.getShortCode());
		tar.setPath(src.getPath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setChannelActivityName(src.getChannelActivityName());
		return tar;
	}

	public static List<ChannelDTO> toDTO(List<ChannelPO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelDTO> list = new ArrayList<ChannelDTO>();
		for (ChannelPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ChannelPO> toPO(List<ChannelDTO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelPO> list = new ArrayList<ChannelPO>();
		for (ChannelDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	public static ChannelDTO toDTO(ChannelVO src) {
		if (src == null)
		return null;	
		ChannelDTO tar = new ChannelDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setType(src.getType());	
		tar.setShortCode(src.getShortCode());	
		tar.setPath(src.getPath());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setPlatformId(src.getPlatformId());
		tar.setChannelActivityName(src.getChannelActivityName());
		return tar;
	}

	public static ChannelVO toVO(ChannelDTO src) {
		if (src == null)
		return null;	
		ChannelVO tar = new ChannelVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setType(src.getType());	
		tar.setShortCode(src.getShortCode());	
		tar.setPath(src.getPath());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setPlatformId(src.getPlatformId());
		tar.setChannelActivityName(src.getChannelActivityName());
		return tar;
	}

	public static List<ChannelDTO> voToDTO(List<ChannelVO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelDTO> list = new ArrayList<ChannelDTO>();
		for (ChannelVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ChannelVO> toVO(List<ChannelDTO> srcs) {
		if (srcs == null)
			return null;
		List<ChannelVO> list = new ArrayList<ChannelVO>();
		for (ChannelDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	