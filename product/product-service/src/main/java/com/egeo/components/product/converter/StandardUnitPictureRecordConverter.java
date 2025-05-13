package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitPictureRecordDTO;
import com.egeo.components.product.po.StandardUnitPictureRecordPO;
import com.egeo.components.product.vo.StandardUnitPictureRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 11:18:56
 */
public class StandardUnitPictureRecordConverter {
	
	public static StandardUnitPictureRecordDTO toDTO(StandardUnitPictureRecordPO src) {
		if (src == null)
		return null;	
		StandardUnitPictureRecordDTO tar = new StandardUnitPictureRecordDTO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		return tar;
	}

	public static StandardUnitPictureRecordPO toPO(StandardUnitPictureRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitPictureRecordPO tar = new StandardUnitPictureRecordPO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		return tar;
	}

	public static List<StandardUnitPictureRecordDTO> toDTO(List<StandardUnitPictureRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitPictureRecordDTO> list = new ArrayList<StandardUnitPictureRecordDTO>();
		for (StandardUnitPictureRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitPictureRecordPO> toPO(List<StandardUnitPictureRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitPictureRecordPO> list = new ArrayList<StandardUnitPictureRecordPO>();
		for (StandardUnitPictureRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitPictureRecordDTO toDTO(StandardUnitPictureRecordVO src) {
		if (src == null)
		return null;	
		StandardUnitPictureRecordDTO tar = new StandardUnitPictureRecordDTO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		return tar;
	}

	public static StandardUnitPictureRecordVO toVO(StandardUnitPictureRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitPictureRecordVO tar = new StandardUnitPictureRecordVO();
		tar.setId(src.getId());
		tar.setMerchantPictureId(src.getMerchantPictureId());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		return tar;
	}

	public static List<StandardUnitPictureRecordDTO> toDTOs(List<StandardUnitPictureRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitPictureRecordDTO> list = new ArrayList<StandardUnitPictureRecordDTO>();
		for (StandardUnitPictureRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitPictureRecordVO> toVO(List<StandardUnitPictureRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitPictureRecordVO> list = new ArrayList<StandardUnitPictureRecordVO>();
		for (StandardUnitPictureRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	