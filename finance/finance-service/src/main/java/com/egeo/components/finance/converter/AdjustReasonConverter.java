package com.egeo.components.finance.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.finance.dto.AdjustReasonDTO;
import com.egeo.components.finance.po.AdjustReasonPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-06 15:24:26
 */
public class AdjustReasonConverter {
	
	public static AdjustReasonDTO toDTO(AdjustReasonPO src) {
		if (src == null)
		return null;	
		AdjustReasonDTO tar = new AdjustReasonDTO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setDisabled(src.getDisabled());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static AdjustReasonPO toPO(AdjustReasonDTO src) {
		if (src == null)
		return null;	
		AdjustReasonPO tar = new AdjustReasonPO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setDisabled(src.getDisabled());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<AdjustReasonDTO> toDTO(List<AdjustReasonPO> srcs) {
		if (srcs == null)
			return null;
		List<AdjustReasonDTO> list = new ArrayList<AdjustReasonDTO>();
		for (AdjustReasonPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AdjustReasonPO> toPO(List<AdjustReasonDTO> srcs) {
		if (srcs == null)
			return null;
		List<AdjustReasonPO> list = new ArrayList<AdjustReasonPO>();
		for (AdjustReasonDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	