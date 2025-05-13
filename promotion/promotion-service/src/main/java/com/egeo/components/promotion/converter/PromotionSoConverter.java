package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionSoDTO;
import com.egeo.components.promotion.po.PromotionSoPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-11 19:27:25
 */
public class PromotionSoConverter {
	
	public static PromotionSoDTO toDTO(PromotionSoPO src) {
		PromotionSoDTO tar = new PromotionSoDTO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setOrderCode(src.getOrderCode());
		tar.setCustomerId(src.getCustomerId());
		tar.setSoSalesAmount(src.getSoSalesAmount());
		tar.setSoDiscountAmount(src.getSoDiscountAmount());
		tar.setStatus(src.getStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PromotionSoPO toPO(PromotionSoDTO src) {
		PromotionSoPO tar = new PromotionSoPO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setOrderCode(src.getOrderCode());
		tar.setCustomerId(src.getCustomerId());
		tar.setSoSalesAmount(src.getSoSalesAmount());
		tar.setSoDiscountAmount(src.getSoDiscountAmount());
		tar.setStatus(src.getStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PromotionSoDTO> toDTO(List<PromotionSoPO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionSoDTO> list = new ArrayList<PromotionSoDTO>();
		for (PromotionSoPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionSoPO> toPO(List<PromotionSoDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionSoPO> list = new ArrayList<PromotionSoPO>();
		for (PromotionSoDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	