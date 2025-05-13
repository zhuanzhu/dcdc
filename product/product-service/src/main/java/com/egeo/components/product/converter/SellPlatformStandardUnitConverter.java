package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SellPlatformStandardUnitDTO;
import com.egeo.components.product.po.SellPlatformStandardUnitPO;
import com.egeo.components.product.vo.SellPlatformStandardUnitVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 11:18:55
 */
public class SellPlatformStandardUnitConverter {
	

	public static SellPlatformStandardUnitVO toVO(SellPlatformStandardUnitDTO src) {
		if (src == null)
		return null;	
		SellPlatformStandardUnitVO tar = new SellPlatformStandardUnitVO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setPath(src.getPath());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<SellPlatformStandardUnitDTO> toDTOs(List<SellPlatformStandardUnitPO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformStandardUnitDTO> list = new ArrayList<SellPlatformStandardUnitDTO>();
		for (SellPlatformStandardUnitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	
	public static SellPlatformStandardUnitDTO toDTO(SellPlatformStandardUnitPO src) {
		if (src == null)
		return null;	
		SellPlatformStandardUnitDTO tar = new SellPlatformStandardUnitDTO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setSalePrice(src.getSalePrice());
		tar.setPath(src.getPath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SellPlatformStandardUnitPO toPO(SellPlatformStandardUnitDTO src) {
		if (src == null)
		return null;	
		SellPlatformStandardUnitPO tar = new SellPlatformStandardUnitPO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setSalePrice(src.getSalePrice());
		tar.setPath(src.getPath());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}
	public static SellPlatformStandardUnitDTO toDTO(SellPlatformStandardUnitVO src) {
		if (src == null)
		return null;	
		SellPlatformStandardUnitDTO tar = new SellPlatformStandardUnitDTO();
		tar.setId(src.getId());
		tar.setSellPlatformId(src.getSellPlatformId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setPath(src.getPath());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}
	public static List<SellPlatformStandardUnitDTO> toDTO(List<SellPlatformStandardUnitPO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformStandardUnitDTO> list = new ArrayList<SellPlatformStandardUnitDTO>();
		for (SellPlatformStandardUnitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SellPlatformStandardUnitVO> toVOs(List<SellPlatformStandardUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformStandardUnitVO> list = new ArrayList<SellPlatformStandardUnitVO>();
		for (SellPlatformStandardUnitDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static List<SellPlatformStandardUnitPO> toPO(List<SellPlatformStandardUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformStandardUnitPO> list = new ArrayList<SellPlatformStandardUnitPO>();
		for (SellPlatformStandardUnitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	