package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoCancelConfigDTO;
import com.egeo.components.order.po.SoCancelConfigPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoCancelConfigConverter {
	
	public static SoCancelConfigDTO toDTO(SoCancelConfigPO src) {
		SoCancelConfigDTO tar = new SoCancelConfigDTO();
		tar.setId(src.getId());
		tar.setOrderPaymentType(src.getOrderPaymentType());
		tar.setOrderPromotionType(src.getOrderPromotionType());
		tar.setTimeOut(src.getTimeOut());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoCancelConfigPO toPO(SoCancelConfigDTO src) {
		SoCancelConfigPO tar = new SoCancelConfigPO();
		tar.setId(src.getId());
		tar.setOrderPaymentType(src.getOrderPaymentType());
		tar.setOrderPromotionType(src.getOrderPromotionType());
		tar.setTimeOut(src.getTimeOut());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoCancelConfigDTO> toDTO(List<SoCancelConfigPO> srcs) {
		if (srcs == null)
			return null;
		List<SoCancelConfigDTO> list = new ArrayList<SoCancelConfigDTO>();
		for (SoCancelConfigPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoCancelConfigPO> toPO(List<SoCancelConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoCancelConfigPO> list = new ArrayList<SoCancelConfigPO>();
		for (SoCancelConfigDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	