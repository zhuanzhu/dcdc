package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.UrlTypeDictDTO;
import com.egeo.components.user.po.UrlTypeDictPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-31 14:56:04
 */
public class UrlTypeDictConverter {
	
	public static UrlTypeDictDTO toDTO(UrlTypeDictPO src) {
		if (src == null)
		return null;	
		UrlTypeDictDTO tar = new UrlTypeDictDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static UrlTypeDictPO toPO(UrlTypeDictDTO src) {
		if (src == null)
		return null;	
		UrlTypeDictPO tar = new UrlTypeDictPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<UrlTypeDictDTO> toDTO(List<UrlTypeDictPO> srcs) {
		if (srcs == null)
			return null;
		List<UrlTypeDictDTO> list = new ArrayList<UrlTypeDictDTO>();
		for (UrlTypeDictPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UrlTypeDictPO> toPO(List<UrlTypeDictDTO> srcs) {
		if (srcs == null)
			return null;
		List<UrlTypeDictPO> list = new ArrayList<UrlTypeDictPO>();
		for (UrlTypeDictDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	