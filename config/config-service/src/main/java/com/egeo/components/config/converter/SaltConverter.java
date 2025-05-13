package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.SaltDTO;
import com.egeo.components.config.po.SaltPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-12-26 10:40:54
 */
public class SaltConverter {
	
	public static SaltDTO toDTO(SaltPO src) {
		if (src == null)
		return null;	
		SaltDTO tar = new SaltDTO();
		tar.setId(src.getId());
		tar.setUuid(src.getUuid());
		tar.setSaltValue(src.getSaltValue());
		return tar;
	}

	public static SaltPO toPO(SaltDTO src) {
		if (src == null)
		return null;	
		SaltPO tar = new SaltPO();
		tar.setId(src.getId());
		tar.setUuid(src.getUuid());
		tar.setSaltValue(src.getSaltValue());
		return tar;
	}

	public static List<SaltDTO> toDTO(List<SaltPO> srcs) {
		if (srcs == null)
			return null;
		List<SaltDTO> list = new ArrayList<SaltDTO>();
		for (SaltPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SaltPO> toPO(List<SaltDTO> srcs) {
		if (srcs == null)
			return null;
		List<SaltPO> list = new ArrayList<SaltPO>();
		for (SaltDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	