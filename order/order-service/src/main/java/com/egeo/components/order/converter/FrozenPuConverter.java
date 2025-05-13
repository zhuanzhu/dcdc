package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.FrozenPuDTO;
import com.egeo.components.order.po.FrozenPuPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-29 09:55:24
 */
public class FrozenPuConverter {
	
	public static FrozenPuDTO toDTO(FrozenPuPO src) {
		if (src == null)
		return null;	
		FrozenPuDTO tar = new FrozenPuDTO();
		tar.setId(src.getId());
		tar.setSkuId(src.getSkuId());
		tar.setPuId(src.getPuId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setFrozenCount(src.getFrozenCount());
		tar.setFreezeStatus(src.getFreezeStatus());
		tar.setUnfreezeReason(src.getUnfreezeReason());
		tar.setUnfreezeTime(src.getUnfreezeTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static FrozenPuPO toPO(FrozenPuDTO src) {
		if (src == null)
		return null;	
		FrozenPuPO tar = new FrozenPuPO();
		tar.setId(src.getId());
		tar.setSkuId(src.getSkuId());
		tar.setPuId(src.getPuId());
		tar.setSoId(src.getSoId());
		tar.setSoChildId(src.getSoChildId());
		tar.setFrozenCount(src.getFrozenCount());
		tar.setFreezeStatus(src.getFreezeStatus());
		tar.setUnfreezeReason(src.getUnfreezeReason());
		tar.setUnfreezeTime(src.getUnfreezeTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<FrozenPuDTO> toDTO(List<FrozenPuPO> srcs) {
		if (srcs == null)
			return null;
		List<FrozenPuDTO> list = new ArrayList<FrozenPuDTO>();
		for (FrozenPuPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FrozenPuPO> toPO(List<FrozenPuDTO> srcs) {
		if (srcs == null)
			return null;
		List<FrozenPuPO> list = new ArrayList<FrozenPuPO>();
		for (FrozenPuDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	