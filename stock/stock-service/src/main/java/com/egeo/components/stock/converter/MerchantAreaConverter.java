package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.MerchantAreaDTO;
import com.egeo.components.stock.po.MerchantAreaPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 11:44:55
 */
public class MerchantAreaConverter {
	
	public static MerchantAreaDTO toDTO(MerchantAreaPO src) {
		if (src == null)
			return null;	
		MerchantAreaDTO tar = new MerchantAreaDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setProvinceId(src.getProvinceId());
		tar.setCityId(src.getCityId());
		tar.setCountyId(src.getCountyId());
		tar.setAreaId(src.getAreaId());
		tar.setCoverLevel(src.getCoverLevel());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MerchantAreaPO toPO(MerchantAreaDTO src) {
		if (src == null)
			return null;	
		MerchantAreaPO tar = new MerchantAreaPO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setProvinceId(src.getProvinceId());
		tar.setCityId(src.getCityId());
		tar.setCountyId(src.getCountyId());
		tar.setAreaId(src.getAreaId());
		tar.setCoverLevel(src.getCoverLevel());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MerchantAreaDTO> toDTO(List<MerchantAreaPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantAreaDTO> list = new ArrayList<MerchantAreaDTO>();
		for (MerchantAreaPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantAreaPO> toPO(List<MerchantAreaDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantAreaPO> list = new ArrayList<MerchantAreaPO>();
		for (MerchantAreaDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	