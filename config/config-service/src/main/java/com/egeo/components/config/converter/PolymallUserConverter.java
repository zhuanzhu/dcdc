package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.PolymallUserDTO;
import com.egeo.components.config.po.PolymallUserPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-03 18:29:13
 */
public class PolymallUserConverter {
	
	public static PolymallUserDTO toDTO(PolymallUserPO src) {
		if (src == null)
		return null;	
		PolymallUserDTO tar = new PolymallUserDTO();
		tar.setId(src.getId());
		tar.setToken(src.getToken());
		tar.setMerchantId(src.getMerchantId());
		return tar;
	}

	public static PolymallUserPO toPO(PolymallUserDTO src) {
		if (src == null)
		return null;	
		PolymallUserPO tar = new PolymallUserPO();
		tar.setId(src.getId());
		tar.setToken(src.getToken());
		tar.setMerchantId(src.getMerchantId());
		return tar;
	}

	public static List<PolymallUserDTO> toDTO(List<PolymallUserPO> srcs) {
		if (srcs == null)
			return null;
		List<PolymallUserDTO> list = new ArrayList<PolymallUserDTO>();
		for (PolymallUserPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PolymallUserPO> toPO(List<PolymallUserDTO> srcs) {
		if (srcs == null)
			return null;
		List<PolymallUserPO> list = new ArrayList<PolymallUserPO>();
		for (PolymallUserDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	