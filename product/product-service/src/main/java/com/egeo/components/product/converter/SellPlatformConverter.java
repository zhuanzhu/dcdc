package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SellPlatformDTO;
import com.egeo.components.product.po.SellPlatformPO;
import com.egeo.components.product.vo.SellPlatformVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author paul
 * @date 2018-09-13 18:06:13
 */
public class SellPlatformConverter {
	
	public static SellPlatformDTO toDTO(SellPlatformVO src) {
		if (src == null)
		return null;	
		SellPlatformDTO tar = new SellPlatformDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStatus(src.getStatus());	
		tar.setSortValue(src.getSortValue());	
		tar.setRemark(src.getRemark());	
		return tar;
	}

	public static SellPlatformVO toVO(SellPlatformDTO src) {
		if (src == null)
		return null;	
		SellPlatformVO tar = new SellPlatformVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStatus(src.getStatus());	
		tar.setSortValue(src.getSortValue());	
		tar.setRemark(src.getRemark());	
		return tar;
	}

	public static List<SellPlatformDTO> toDTOs(List<SellPlatformVO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformDTO> list = new ArrayList<SellPlatformDTO>();
		for (SellPlatformVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SellPlatformVO> toVO(List<SellPlatformDTO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformVO> list = new ArrayList<SellPlatformVO>();
		for (SellPlatformDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SellPlatformDTO toDTO(SellPlatformPO src) {
		if (src == null)
		return null;	
		SellPlatformDTO tar = new SellPlatformDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStatus(src.getStatus());
		tar.setSortValue(src.getSortValue());
		tar.setRemark(src.getRemark());
		return tar;
	}

	public static SellPlatformPO toPO(SellPlatformDTO src) {
		if (src == null)
		return null;	
		SellPlatformPO tar = new SellPlatformPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStatus(src.getStatus());
		tar.setSortValue(src.getSortValue());
		tar.setRemark(src.getRemark());
		return tar;
	}

	public static List<SellPlatformDTO> toDTO(List<SellPlatformPO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformDTO> list = new ArrayList<SellPlatformDTO>();
		for (SellPlatformPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SellPlatformPO> toPO(List<SellPlatformDTO> srcs) {
		if (srcs == null)
			return null;
		List<SellPlatformPO> list = new ArrayList<SellPlatformPO>();
		for (SellPlatformDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	