package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoProcessConfigDTO;
import com.egeo.components.order.po.SoProcessConfigPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoProcessConfigConverter {
	
	public static SoProcessConfigDTO toDTO(SoProcessConfigPO src) {
		SoProcessConfigDTO tar = new SoProcessConfigDTO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setTemplateName(src.getTemplateName());
		tar.setDiscription(src.getDiscription());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoProcessConfigPO toPO(SoProcessConfigDTO src) {
		SoProcessConfigPO tar = new SoProcessConfigPO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setTemplateName(src.getTemplateName());
		tar.setDiscription(src.getDiscription());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoProcessConfigDTO> toDTO(List<SoProcessConfigPO> srcs) {
		if (srcs == null)
			return null;
		List<SoProcessConfigDTO> list = new ArrayList<SoProcessConfigDTO>();
		for (SoProcessConfigPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoProcessConfigPO> toPO(List<SoProcessConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoProcessConfigPO> list = new ArrayList<SoProcessConfigPO>();
		for (SoProcessConfigDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	