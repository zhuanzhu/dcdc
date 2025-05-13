package com.egeo.components.order.converter;

import com.egeo.components.order.dto.SoRefundItemDTO;
import com.egeo.components.order.po.SoRefundItemPO;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class SoRefundItemConverter {
	
	public static SoRefundItemDTO toDTO(SoRefundItemPO src) {
		SoRefundItemDTO tar = new SoRefundItemDTO();
		tar.setId(src.getId());
		tar.setRefundId(src.getRefundId());
		tar.setRefundCode(src.getRefundCode());
		tar.setSkuId(src.getSkuId());
		tar.setSkuName(src.getSkuName());
		tar.setRefundNum(src.getRefundNum());
		tar.setPrice(src.getPrice());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		tar.setSoItemId(src.getSoItemId());
		tar.setSource(src.getSource());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SoRefundItemPO toPO(SoRefundItemDTO src) {
		SoRefundItemPO tar = new SoRefundItemPO();
		tar.setId(src.getId());
		tar.setRefundId(src.getRefundId());
		tar.setRefundCode(src.getRefundCode());
		tar.setSkuId(src.getSkuId());
		tar.setSkuName(src.getSkuName());
		tar.setRefundNum(src.getRefundNum());
		tar.setPrice(src.getPrice());
		tar.setRefundAmount(src.getRefundAmount());
		tar.setRefundDeliveryFee(src.getRefundDeliveryFee());
		tar.setSoItemId(src.getSoItemId());
		tar.setSource(src.getSource());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SoRefundItemDTO> toDTO(List<SoRefundItemPO> srcs) {
		if (srcs == null)
			return null;
		List<SoRefundItemDTO> list = new ArrayList<SoRefundItemDTO>();
		for (SoRefundItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoRefundItemPO> toPO(List<SoRefundItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoRefundItemPO> list = new ArrayList<SoRefundItemPO>();
		for (SoRefundItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	