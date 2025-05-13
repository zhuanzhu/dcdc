package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitPictureDTO;
import com.egeo.components.product.po.StandardProductUnitPicturePO;
import com.egeo.components.product.vo.StandardProductUnitPictureVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 13:26:51
 */
public class StandardProductUnitPictureConverter {
	
	public static StandardProductUnitPictureDTO toDTO(StandardProductUnitPictureVO src) {
		if (src == null)
		return null;	
		StandardProductUnitPictureDTO tar = new StandardProductUnitPictureDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setPictureId(src.getPictureId());	
		tar.setType(src.getType());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardProductUnitPictureVO toVO(StandardProductUnitPictureDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitPictureVO tar = new StandardProductUnitPictureVO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setPictureId(src.getPictureId());	
		tar.setType(src.getType());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardProductUnitPictureDTO> toDTOs(List<StandardProductUnitPictureVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitPictureDTO> list = new ArrayList<StandardProductUnitPictureDTO>();
		for (StandardProductUnitPictureVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitPictureVO> toVO(List<StandardProductUnitPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitPictureVO> list = new ArrayList<StandardProductUnitPictureVO>();
		for (StandardProductUnitPictureDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StandardProductUnitPictureDTO toDTO(StandardProductUnitPicturePO src) {
		if (src == null)
		return null;	
		StandardProductUnitPictureDTO tar = new StandardProductUnitPictureDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setPictureId(src.getPictureId());
		tar.setType(src.getType());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StandardProductUnitPicturePO toPO(StandardProductUnitPictureDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitPicturePO tar = new StandardProductUnitPicturePO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setPictureId(src.getPictureId());
		tar.setType(src.getType());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StandardProductUnitPictureDTO> toDTO(List<StandardProductUnitPicturePO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitPictureDTO> list = new ArrayList<StandardProductUnitPictureDTO>();
		for (StandardProductUnitPicturePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitPicturePO> toPO(List<StandardProductUnitPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitPicturePO> list = new ArrayList<StandardProductUnitPicturePO>();
		for (StandardProductUnitPictureDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	