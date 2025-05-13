package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.condition.UrlCondition;
import com.egeo.components.user.dto.UrlDTO;
import com.egeo.components.user.po.UrlPO;
import com.egeo.components.user.vo.UrlVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-18 18:50:29
 */
public class UrlConverter {

	
	/**
	 * @param src
	 * @return
	 */
	public static UrlDTO toDTO(UrlVO src) {
		UrlDTO tar = new UrlDTO();
		tar.setId(src.getId());
		tar.setUrl(src.getUrl());	
		tar.setName(src.getName());	
		tar.setDescription(src.getDescription());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCodeModuleId(src.getCodeModuleId());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		return tar;
	}
	public static UrlDTO toDTO(UrlPO src) {
		UrlDTO tar = new UrlDTO();
		tar.setId(src.getId());
		tar.setUrl(src.getUrl());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCodeModuleId(src.getCodeModuleId());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		return tar;
	}

	public static UrlPO toPO(UrlDTO src) {
		UrlPO tar = new UrlPO();
		tar.setId(src.getId());
		tar.setUrl(src.getUrl());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCodeModuleId(src.getCodeModuleId());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		return tar;
	}
	
	public static UrlDTO conditionToDTO(UrlCondition src) {
		UrlDTO tar = new UrlDTO();
		tar.setId(src.getId());
		tar.setUrl(src.getUrl());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCodeModuleId(src.getCodeModuleId());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		tar.setCodeModuleName(src.getCodeModuleName());
		return tar;
	}

	public static List<UrlDTO> toDTO(List<UrlPO> srcs) {
		if (srcs == null)
			return null;
		List<UrlDTO> list = new ArrayList<UrlDTO>();
		for (UrlPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UrlPO> toPO(List<UrlDTO> srcs) {
		if (srcs == null)
			return null;
		List<UrlPO> list = new ArrayList<UrlPO>();
		for (UrlDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	public static List<UrlDTO> conditionToDTO(List<UrlCondition> srcs) {
		if (srcs == null)
			return null;
		List<UrlDTO> list = new ArrayList<UrlDTO>();
		for (UrlCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
	
}
	