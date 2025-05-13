package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.PictureDTO;
import com.egeo.components.config.po.PicturePO;
import com.egeo.components.config.vo.PictureVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-11-29 21:58:45
 */
public class PictureConverter {

	public static PictureDTO toDTO(PictureVO src) {
		if (src == null)
		return null;	
		PictureDTO tar = new PictureDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setUrl(src.getUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static PictureVO toVO(PictureDTO src) {
		if (src == null)
		return null;	
		PictureVO tar = new PictureVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setUrl(src.getUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}



	public static List<PictureVO> toVO(List<PictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<PictureVO> list = new ArrayList<PictureVO>();
		for (PictureDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static PictureDTO toDTO(PicturePO src) {
		if (src == null)
		return null;	
		PictureDTO tar = new PictureDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setUrl(src.getUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static PicturePO toPO(PictureDTO src) {
		if (src == null)
		return null;	
		PicturePO tar = new PicturePO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setUrl(src.getUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<PictureDTO> toDTO(List<PicturePO> srcs) {
		if (srcs == null)
			return null;
		List<PictureDTO> list = new ArrayList<PictureDTO>();
		for (PicturePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PicturePO> toPO(List<PictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<PicturePO> list = new ArrayList<PicturePO>();
		for (PictureDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	