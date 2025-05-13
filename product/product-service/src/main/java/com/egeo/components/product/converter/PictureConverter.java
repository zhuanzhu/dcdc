package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.PictureDTO;
import com.egeo.components.product.po.PicturePO;
import com.egeo.components.product.vo.PictureVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:26
 */
public class PictureConverter {

	public static PictureDTO toDTO(PictureVO src) {
		PictureDTO tar = new PictureDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setUrl(src.getUrl());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setSortValue(src.getSortValue());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setStyleImage(PictureConverter.toDTOs(src.getStyleImage()));
		return tar;
	}

	public static PictureVO toVO(PictureDTO src) {  
		PictureVO tar = new PictureVO();
		tar.setId(src.getId());
		tar.setName(src.getName());		
		tar.setUrl(src.getUrl());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setSortValue(src.getSortValue());		
		tar.setType(src.getType());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		tar.setStyleImage(PictureConverter.toVO((src.getStyleImage())));
		return tar;
	}

	public static List<PictureDTO> toDTOs(List<PictureVO> srcs) {
		if (srcs == null)
			return null;
		List<PictureDTO> list = new ArrayList<PictureDTO>();
		for (PictureVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
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
		PictureDTO tar = new PictureDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setUrl(src.getUrl());
		tar.setPlatformId(src.getPlatformId());
		tar.setSortValue(src.getSortValue());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setStyleImage(PictureConverter.toDTO(src.getStyleImage()));
		return tar;
	}

	public static PicturePO toPO(PictureDTO src) {
		PicturePO tar = new PicturePO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setUrl(src.getUrl());
		tar.setPlatformId(src.getPlatformId());
		tar.setSortValue(src.getSortValue());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setStyleImage(PictureConverter.toPO(src.getStyleImage()));
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
	