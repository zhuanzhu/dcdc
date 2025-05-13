package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SellPlatformStandardUnitRecordDTO;
import com.egeo.components.product.po.SellPlatformStandardUnitRecordPO;
import com.egeo.components.product.vo.SellPlatformStandardUnitRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 11:18:55
 */
public class SellPlatformStandardUnitRecordConverter {
	
	public static SellPlatformStandardUnitRecordDTO toDTO(SellPlatformStandardUnitRecordVO src) {
		if (src == null)
		return null;	
		SellPlatformStandardUnitRecordDTO tar = new SellPlatformStandardUnitRecordDTO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());	
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setPath(src.getPath());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static SellPlatformStandardUnitRecordVO toVO(SellPlatformStandardUnitRecordDTO src) {
		if (src == null)
		return null;	
		SellPlatformStandardUnitRecordVO tar = new SellPlatformStandardUnitRecordVO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());	
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setPath(src.getPath());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<SellPlatformStandardUnitRecordDTO> toDTOs(List<SellPlatformStandardUnitRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformStandardUnitRecordDTO> list = new ArrayList<SellPlatformStandardUnitRecordDTO>();
		for (SellPlatformStandardUnitRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SellPlatformStandardUnitRecordVO> toVO(List<SellPlatformStandardUnitRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformStandardUnitRecordVO> list = new ArrayList<SellPlatformStandardUnitRecordVO>();
		for (SellPlatformStandardUnitRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SellPlatformStandardUnitRecordDTO toDTO(SellPlatformStandardUnitRecordPO src) {
		if (src == null)
		return null;	
		SellPlatformStandardUnitRecordDTO tar = new SellPlatformStandardUnitRecordDTO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setSalePrice(src.getSalePrice());
		tar.setPath(src.getPath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SellPlatformStandardUnitRecordPO toPO(SellPlatformStandardUnitRecordDTO src) {
		if (src == null)
		return null;	
		SellPlatformStandardUnitRecordPO tar = new SellPlatformStandardUnitRecordPO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setSalePrice(src.getSalePrice());
		tar.setPath(src.getPath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SellPlatformStandardUnitRecordDTO> toDTO(List<SellPlatformStandardUnitRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformStandardUnitRecordDTO> list = new ArrayList<SellPlatformStandardUnitRecordDTO>();
		for (SellPlatformStandardUnitRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SellPlatformStandardUnitRecordPO> toPO(List<SellPlatformStandardUnitRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformStandardUnitRecordPO> list = new ArrayList<SellPlatformStandardUnitRecordPO>();
		for (SellPlatformStandardUnitRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	