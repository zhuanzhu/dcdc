package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.UrlTypeDTO;
import com.egeo.components.user.po.UrlTypePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-31 14:35:28
 */
public class UrlTypeConverter {
	
	public static UrlTypeDTO toDTO(UrlTypePO src) {
		if (src == null)
		return null;	
		UrlTypeDTO tar = new UrlTypeDTO();
		tar.setId(src.getId());
		tar.setUrlId(src.getUrlId());
		tar.setUrlTypeDictId(src.getUrlTypeDictId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static UrlTypePO toPO(UrlTypeDTO src) {
		if (src == null)
		return null;	
		UrlTypePO tar = new UrlTypePO();
		tar.setId(src.getId());
		tar.setUrlId(src.getUrlId());
		tar.setUrlTypeDictId(src.getUrlTypeDictId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<UrlTypeDTO> toDTO(List<UrlTypePO> srcs) {
		if (srcs == null)
			return null;
		List<UrlTypeDTO> list = new ArrayList<UrlTypeDTO>();
		for (UrlTypePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UrlTypePO> toPO(List<UrlTypeDTO> srcs) {
		if (srcs == null)
			return null;
		List<UrlTypePO> list = new ArrayList<UrlTypePO>();
		for (UrlTypeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	