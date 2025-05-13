package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoSignConfigDTO;
import com.egeo.components.order.po.SoSignConfigPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoSignConfigConverter {
	
	public static SoSignConfigDTO toDTO(SoSignConfigPO src) {
		SoSignConfigDTO tar = new SoSignConfigDTO();
		tar.setId(src.getId());
		tar.setOrderStatus(src.getOrderStatus());
		tar.setOrderPaymentStatus(src.getOrderPaymentStatus());
		tar.setOrderPaymentType(src.getOrderPaymentType());
		tar.setTimeOut(src.getTimeOut());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoSignConfigPO toPO(SoSignConfigDTO src) {
		SoSignConfigPO tar = new SoSignConfigPO();
		tar.setId(src.getId());
		tar.setOrderStatus(src.getOrderStatus());
		tar.setOrderPaymentStatus(src.getOrderPaymentStatus());
		tar.setOrderPaymentType(src.getOrderPaymentType());
		tar.setTimeOut(src.getTimeOut());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoSignConfigDTO> toDTO(List<SoSignConfigPO> srcs) {
		if (srcs == null)
			return null;
		List<SoSignConfigDTO> list = new ArrayList<SoSignConfigDTO>();
		for (SoSignConfigPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoSignConfigPO> toPO(List<SoSignConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoSignConfigPO> list = new ArrayList<SoSignConfigPO>();
		for (SoSignConfigDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	