package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.LocalParamDTO;
import com.egeo.components.cms.po.LocalParamPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-18 18:40:40
 */
public class LocalParamConverter {
	
	public static LocalParamDTO toDTO(LocalParamPO src) {
		if (src == null)
		return null;	
		LocalParamDTO tar = new LocalParamDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static LocalParamPO toPO(LocalParamDTO src) {
		if (src == null)
		return null;	
		LocalParamPO tar = new LocalParamPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<LocalParamDTO> toDTO(List<LocalParamPO> srcs) {
		if (srcs == null)
			return null;
		List<LocalParamDTO> list = new ArrayList<LocalParamDTO>();
		for (LocalParamPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LocalParamPO> toPO(List<LocalParamDTO> srcs) {
		if (srcs == null)
			return null;
		List<LocalParamPO> list = new ArrayList<LocalParamPO>();
		for (LocalParamDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	