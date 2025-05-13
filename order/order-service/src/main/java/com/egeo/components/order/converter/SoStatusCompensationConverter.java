package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoStatusCompensationDTO;
import com.egeo.components.order.po.SoStatusCompensationPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoStatusCompensationConverter {
	
	public static SoStatusCompensationDTO toDTO(SoStatusCompensationPO src) {
		SoStatusCompensationDTO tar = new SoStatusCompensationDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setOrderStatus(src.getOrderStatus());
		tar.setOrderBusinessType(src.getOrderBusinessType());
		tar.setOrderBusinessSubType(src.getOrderBusinessSubType());
		tar.setOrderBusinessStatus(src.getOrderBusinessStatus());
		tar.setPhase(src.getPhase());
		tar.setCompensationStatus(src.getCompensationStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoStatusCompensationPO toPO(SoStatusCompensationDTO src) {
		SoStatusCompensationPO tar = new SoStatusCompensationPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setOrderStatus(src.getOrderStatus());
		tar.setOrderBusinessType(src.getOrderBusinessType());
		tar.setOrderBusinessSubType(src.getOrderBusinessSubType());
		tar.setOrderBusinessStatus(src.getOrderBusinessStatus());
		tar.setPhase(src.getPhase());
		tar.setCompensationStatus(src.getCompensationStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoStatusCompensationDTO> toDTO(List<SoStatusCompensationPO> srcs) {
		if (srcs == null)
			return null;
		List<SoStatusCompensationDTO> list = new ArrayList<SoStatusCompensationDTO>();
		for (SoStatusCompensationPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoStatusCompensationPO> toPO(List<SoStatusCompensationDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoStatusCompensationPO> list = new ArrayList<SoStatusCompensationPO>();
		for (SoStatusCompensationDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	