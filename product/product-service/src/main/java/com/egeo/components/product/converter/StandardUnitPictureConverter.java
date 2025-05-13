package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitPictureDTO;
import com.egeo.components.product.po.StandardUnitPicturePO;
import com.egeo.components.product.vo.StandardUnitPictureVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 11:18:56
 */
public class StandardUnitPictureConverter {
	
	public static StandardUnitPictureDTO toDTO(StandardUnitPicturePO src) {
		if (src == null)
		return null;	
		StandardUnitPictureDTO tar = new StandardUnitPictureDTO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStandardUnitId(src.getStandardUnitId());
		return tar;
	}

	public static StandardUnitPicturePO toPO(StandardUnitPictureDTO src) {
		if (src == null)
		return null;	
		StandardUnitPicturePO tar = new StandardUnitPicturePO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStandardUnitId(src.getStandardUnitId());
		return tar;
	}

	public static List<StandardUnitPictureDTO> toDTO(List<StandardUnitPicturePO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitPictureDTO> list = new ArrayList<StandardUnitPictureDTO>();
		for (StandardUnitPicturePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitPicturePO> toPO(List<StandardUnitPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitPicturePO> list = new ArrayList<StandardUnitPicturePO>();
		for (StandardUnitPictureDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitPictureDTO toDTO(StandardUnitPictureVO src) {
		if (src == null)
		return null;	
		StandardUnitPictureDTO tar = new StandardUnitPictureDTO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		return tar;
	}

	public static StandardUnitPictureVO toVO(StandardUnitPictureDTO src) {
		if (src == null)
		return null;	
		StandardUnitPictureVO tar = new StandardUnitPictureVO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		return tar;
	}

	public static List<StandardUnitPictureDTO> toDTOs(List<StandardUnitPictureVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitPictureDTO> list = new ArrayList<StandardUnitPictureDTO>();
		for (StandardUnitPictureVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitPictureVO> toVO(List<StandardUnitPictureDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitPictureVO> list = new ArrayList<StandardUnitPictureVO>();
		for (StandardUnitPictureDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	