package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoUnitDTO;
import com.egeo.components.order.po.SoUnitPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-29 09:55:24
 */
public class SoUnitConverter {
	
	public static SoUnitDTO toDTO(SoUnitPO src) {
		if (src == null)
		return null;	
		SoUnitDTO tar = new SoUnitDTO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setPuId(src.getPuId());
		tar.setUnitId(src.getUnitId());
		tar.setUnitNo(src.getUnitNo());
		tar.setUnitCipher(src.getUnitCipher());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SoUnitPO toPO(SoUnitDTO src) {
		if (src == null)
		return null;	
		SoUnitPO tar = new SoUnitPO();
		tar.setId(src.getId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setPuId(src.getPuId());
		tar.setUnitId(src.getUnitId());
		tar.setUnitNo(src.getUnitNo());
		tar.setUnitCipher(src.getUnitCipher());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SoUnitDTO> toDTO(List<SoUnitPO> srcs) {
		if (srcs == null)
			return null;
		List<SoUnitDTO> list = new ArrayList<SoUnitDTO>();
		for (SoUnitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoUnitPO> toPO(List<SoUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoUnitPO> list = new ArrayList<SoUnitPO>();
		for (SoUnitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	