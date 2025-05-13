package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.WarehouseAreaDTO;
import com.egeo.components.stock.po.WarehouseAreaPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 11:44:55
 */
public class WarehouseAreaConverter {
	
	public static WarehouseAreaDTO toDTO(WarehouseAreaPO src) {
		if (src == null)
			return null;	
		WarehouseAreaDTO tar = new WarehouseAreaDTO();
		tar.setId(src.getId());
		tar.setWarehouseId(src.getWarehouseId());
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

	public static WarehouseAreaPO toPO(WarehouseAreaDTO src) {
		if (src == null)
			return null;	
		WarehouseAreaPO tar = new WarehouseAreaPO();
		tar.setId(src.getId());
		tar.setWarehouseId(src.getWarehouseId());
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

	public static List<WarehouseAreaDTO> toDTO(List<WarehouseAreaPO> srcs) {
		if (srcs == null)
			return null;
		List<WarehouseAreaDTO> list = new ArrayList<WarehouseAreaDTO>();
		for (WarehouseAreaPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<WarehouseAreaPO> toPO(List<WarehouseAreaDTO> srcs) {
		if (srcs == null)
			return null;
		List<WarehouseAreaPO> list = new ArrayList<WarehouseAreaPO>();
		for (WarehouseAreaDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	