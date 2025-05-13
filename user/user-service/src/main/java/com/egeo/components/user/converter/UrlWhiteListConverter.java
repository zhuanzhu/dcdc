package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.condition.UrlWhiteListCondition;
import com.egeo.components.user.dto.UrlWhiteListDTO;
import com.egeo.components.user.po.UrlWhiteListPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-11-08 14:15:43
 */
public class UrlWhiteListConverter {
	
	public static UrlWhiteListDTO toDTO(UrlWhiteListPO src) {
		if (src == null)
		return null;	
		UrlWhiteListDTO tar = new UrlWhiteListDTO();
		tar.setId(src.getId());
		tar.setUrlId(src.getUrlId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setRemark(src.getRemark());
		return tar;
	}

	public static UrlWhiteListPO toPO(UrlWhiteListDTO src) {
		if (src == null)
		return null;	
		UrlWhiteListPO tar = new UrlWhiteListPO();
		tar.setId(src.getId());
		tar.setUrlId(src.getUrlId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setRemark(src.getRemark());
		return tar;
	}
	
	public static UrlWhiteListDTO conditionToDTO(UrlWhiteListCondition src) {
		if (src == null)
		return null;	
		UrlWhiteListDTO tar = new UrlWhiteListDTO();
		tar.setId(src.getId());
		tar.setUrlId(src.getUrlId());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUrl(src.getUrl());
		tar.setName(src.getName());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setPlatformName(src.getPlatformName());
		tar.setRemark(src.getRemark());
		return tar;
	}

	public static List<UrlWhiteListDTO> toDTO(List<UrlWhiteListPO> srcs) {
		if (srcs == null)
			return null;
		List<UrlWhiteListDTO> list = new ArrayList<UrlWhiteListDTO>();
		for (UrlWhiteListPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UrlWhiteListPO> toPO(List<UrlWhiteListDTO> srcs) {
		if (srcs == null)
			return null;
		List<UrlWhiteListPO> list = new ArrayList<UrlWhiteListPO>();
		for (UrlWhiteListDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	public static List<UrlWhiteListDTO> conditionToDTO(List<UrlWhiteListCondition> srcs) {
		if (srcs == null)
			return null;
		List<UrlWhiteListDTO> list = new ArrayList<UrlWhiteListDTO>();
		for (UrlWhiteListCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
	
}
	