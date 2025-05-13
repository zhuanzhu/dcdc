package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoPackageBoxDTO;
import com.egeo.components.order.po.SoPackageBoxPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-02-03 19:58:59
 */
public class SoPackageBoxConverter {
	
	public static SoPackageBoxDTO toDTO(SoPackageBoxPO src) {
		if (src == null)
		return null;	
		SoPackageBoxDTO tar = new SoPackageBoxDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setSoBoxCode(src.getSoBoxCode());
		tar.setSoPackageId(src.getSoPackageId());
		return tar;
	}

	public static SoPackageBoxPO toPO(SoPackageBoxDTO src) {
		if (src == null)
		return null;	
		SoPackageBoxPO tar = new SoPackageBoxPO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setSoBoxCode(src.getSoBoxCode());
		tar.setSoPackageId(src.getSoPackageId());
		return tar;
	}

	public static List<SoPackageBoxDTO> toDTO(List<SoPackageBoxPO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageBoxDTO> list = new ArrayList<SoPackageBoxDTO>();
		for (SoPackageBoxPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoPackageBoxPO> toPO(List<SoPackageBoxDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageBoxPO> list = new ArrayList<SoPackageBoxPO>();
		for (SoPackageBoxDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	