package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoReturnConfigDTO;
import com.egeo.components.order.po.SoReturnConfigPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoReturnConfigConverter {
	
	public static SoReturnConfigDTO toDTO(SoReturnConfigPO src) {
		SoReturnConfigDTO tar = new SoReturnConfigDTO();
		tar.setId(src.getId());
		tar.setReturnDays(src.getReturnDays());
		tar.setReplacementDays(src.getReplacementDays());
		tar.setPlatformId(src.getPlatformId());
		tar.setReplacementType(src.getReplacementType());
		tar.setReturnType(src.getReturnType());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoReturnConfigPO toPO(SoReturnConfigDTO src) {
		SoReturnConfigPO tar = new SoReturnConfigPO();
		tar.setId(src.getId());
		tar.setReturnDays(src.getReturnDays());
		tar.setReplacementDays(src.getReplacementDays());
		tar.setPlatformId(src.getPlatformId());
		tar.setReplacementType(src.getReplacementType());
		tar.setReturnType(src.getReturnType());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoReturnConfigDTO> toDTO(List<SoReturnConfigPO> srcs) {
		if (srcs == null)
			return null;
		List<SoReturnConfigDTO> list = new ArrayList<SoReturnConfigDTO>();
		for (SoReturnConfigPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoReturnConfigPO> toPO(List<SoReturnConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoReturnConfigPO> list = new ArrayList<SoReturnConfigPO>();
		for (SoReturnConfigDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	