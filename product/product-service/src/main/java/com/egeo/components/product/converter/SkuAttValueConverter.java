package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SkuAttValueDTO;
import com.egeo.components.product.po.SkuAttValuePO;
import com.egeo.components.product.vo.SkuAttValueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-06 09:08:12
 */
public class SkuAttValueConverter {
	
	public static SkuAttValueDTO toDTO(SkuAttValueVO src) {
		if (src == null)
		return null;	
		SkuAttValueDTO tar = new SkuAttValueDTO();
		tar.setId(src.getId());
		tar.setSkuAttNameId(src.getSkuAttNameId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static SkuAttValueVO toVO(SkuAttValueDTO src) {
		if (src == null)
		return null;	
		SkuAttValueVO tar = new SkuAttValueVO();
		tar.setId(src.getId());
		tar.setSkuAttNameId(src.getSkuAttNameId());	
		tar.setAttValueId(src.getAttValueId());	
		tar.setAttValueCustom(src.getAttValueCustom());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setSortValue(src.getSortValue());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<SkuAttValueDTO> toDTOs(List<SkuAttValueVO> srcs) {
		if (srcs == null)
			return null;
		List<SkuAttValueDTO> list = new ArrayList<SkuAttValueDTO>();
		for (SkuAttValueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SkuAttValueVO> toVO(List<SkuAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<SkuAttValueVO> list = new ArrayList<SkuAttValueVO>();
		for (SkuAttValueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SkuAttValueDTO toDTO(SkuAttValuePO src) {
		if (src == null)
		return null;	
		SkuAttValueDTO tar = new SkuAttValueDTO();
		tar.setId(src.getId());
		tar.setSkuAttNameId(src.getSkuAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SkuAttValuePO toPO(SkuAttValueDTO src) {
		if (src == null)
		return null;	
		SkuAttValuePO tar = new SkuAttValuePO();
		tar.setId(src.getId());
		tar.setSkuAttNameId(src.getSkuAttNameId());
		tar.setAttValueId(src.getAttValueId());
		tar.setAttValueCustom(src.getAttValueCustom());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setSortValue(src.getSortValue());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SkuAttValueDTO> toDTO(List<SkuAttValuePO> srcs) {
		if (srcs == null)
			return null;
		List<SkuAttValueDTO> list = new ArrayList<SkuAttValueDTO>();
		for (SkuAttValuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SkuAttValuePO> toPO(List<SkuAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<SkuAttValuePO> list = new ArrayList<SkuAttValuePO>();
		for (SkuAttValueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	