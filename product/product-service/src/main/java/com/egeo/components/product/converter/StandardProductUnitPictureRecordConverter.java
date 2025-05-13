package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitPictureRecordDTO;
import com.egeo.components.product.po.StandardProductUnitPictureRecordPO;
import com.egeo.components.product.vo.StandardProductUnitPictureRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 13:26:51
 */
public class StandardProductUnitPictureRecordConverter {
	
	public static StandardProductUnitPictureRecordDTO toDTO(StandardProductUnitPictureRecordVO src) {
		if (src == null)
		return null;	
		StandardProductUnitPictureRecordDTO tar = new StandardProductUnitPictureRecordDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());	
		tar.setType(src.getType());	
		tar.setPictureId(src.getPictureId());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardProductUnitPictureRecordVO toVO(StandardProductUnitPictureRecordDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitPictureRecordVO tar = new StandardProductUnitPictureRecordVO();
		tar.setId(src.getId());
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());	
		tar.setType(src.getType());	
		tar.setPictureId(src.getPictureId());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardProductUnitPictureRecordDTO> toDTOs(List<StandardProductUnitPictureRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitPictureRecordDTO> list = new ArrayList<StandardProductUnitPictureRecordDTO>();
		for (StandardProductUnitPictureRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitPictureRecordVO> toVO(List<StandardProductUnitPictureRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitPictureRecordVO> list = new ArrayList<StandardProductUnitPictureRecordVO>();
		for (StandardProductUnitPictureRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StandardProductUnitPictureRecordDTO toDTO(StandardProductUnitPictureRecordPO src) {
		if (src == null)
		return null;	
		StandardProductUnitPictureRecordDTO tar = new StandardProductUnitPictureRecordDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());
		tar.setType(src.getType());
		tar.setPictureId(src.getPictureId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StandardProductUnitPictureRecordPO toPO(StandardProductUnitPictureRecordDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitPictureRecordPO tar = new StandardProductUnitPictureRecordPO();
		tar.setId(src.getId());
		tar.setStandardProductUnitRecordId(src.getStandardProductUnitRecordId());
		tar.setType(src.getType());
		tar.setPictureId(src.getPictureId());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StandardProductUnitPictureRecordDTO> toDTO(List<StandardProductUnitPictureRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitPictureRecordDTO> list = new ArrayList<StandardProductUnitPictureRecordDTO>();
		for (StandardProductUnitPictureRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitPictureRecordPO> toPO(List<StandardProductUnitPictureRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitPictureRecordPO> list = new ArrayList<StandardProductUnitPictureRecordPO>();
		for (StandardProductUnitPictureRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	