package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.VideoDTO;
import com.egeo.components.product.po.VideoPO;
import com.egeo.components.product.vo.VideoVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-03-07 11:31:34
 */
public class VideoConverter {
	
	public static VideoDTO toDTO(VideoPO src) {
		if (src == null)
		return null;	
		VideoDTO tar = new VideoDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setUrl(src.getUrl());
		tar.setThumbnailUrl(src.getThumbnailUrl());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static VideoPO toPO(VideoDTO src) {
		if (src == null)
		return null;	
		VideoPO tar = new VideoPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setUrl(src.getUrl());
		tar.setThumbnailUrl(src.getThumbnailUrl());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<VideoDTO> toDTO(List<VideoPO> srcs) {
		if (srcs == null)
			return null;
		List<VideoDTO> list = new ArrayList<VideoDTO>();
		for (VideoPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<VideoPO> toPO(List<VideoDTO> srcs) {
		if (srcs == null)
			return null;
		List<VideoPO> list = new ArrayList<VideoPO>();
		for (VideoDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static VideoDTO toDTO(VideoVO src) {
		if (src == null)
		return null;	
		VideoDTO tar = new VideoDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setUrl(src.getUrl());	
		tar.setThumbnailUrl(src.getThumbnailUrl());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static VideoVO toVO(VideoDTO src) {
		if (src == null)
		return null;	
		VideoVO tar = new VideoVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setUrl(src.getUrl());	
		tar.setThumbnailUrl(src.getThumbnailUrl());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<VideoDTO> toDTOs(List<VideoVO> srcs) {
		if (srcs == null)
			return null;
		List<VideoDTO> list = new ArrayList<VideoDTO>();
		for (VideoVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<VideoVO> toVO(List<VideoDTO> srcs) {
		if (srcs == null)
			return null;
		List<VideoVO> list = new ArrayList<VideoVO>();
		for (VideoDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	