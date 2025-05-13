package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.MerchantProductAreaDTO;
import com.egeo.components.stock.po.MerchantProductAreaPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 09:13:19
 */
public class MerchantProductAreaConverter {
	
	public static MerchantProductAreaDTO toDTO(MerchantProductAreaPO src) {
		if (src == null)
			return null;	
		MerchantProductAreaDTO tar = new MerchantProductAreaDTO();
		tar.setId(src.getId());
		tar.setProvinceId(src.getProvinceId());
		tar.setCityId(src.getCityId());
		tar.setCountyId(src.getCountyId());
		tar.setAreaId(src.getAreaId());
		tar.setCoverLevel(src.getCoverLevel());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static MerchantProductAreaPO toPO(MerchantProductAreaDTO src) {
		if (src == null)
			return null;	
		MerchantProductAreaPO tar = new MerchantProductAreaPO();
		tar.setId(src.getId());
		tar.setProvinceId(src.getProvinceId());
		tar.setCityId(src.getCityId());
		tar.setCountyId(src.getCountyId());
		tar.setAreaId(src.getAreaId());
		tar.setCoverLevel(src.getCoverLevel());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<MerchantProductAreaDTO> toDTO(List<MerchantProductAreaPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductAreaDTO> list = new ArrayList<MerchantProductAreaDTO>();
		for (MerchantProductAreaPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductAreaPO> toPO(List<MerchantProductAreaDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductAreaPO> list = new ArrayList<MerchantProductAreaPO>();
		for (MerchantProductAreaDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	