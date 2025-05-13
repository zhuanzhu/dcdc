package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.DistributionModeItemDTO;
import com.egeo.components.order.po.DistributionModeItemPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class DistributionModeItemConverter {
	
	public static DistributionModeItemDTO toDTO(DistributionModeItemPO src) {
		DistributionModeItemDTO tar = new DistributionModeItemDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setShippingCode(src.getShippingCode());
		tar.setDistributionMode(src.getDistributionMode());
		tar.setIsDefault(src.getIsDefault());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static DistributionModeItemPO toPO(DistributionModeItemDTO src) {
		DistributionModeItemPO tar = new DistributionModeItemPO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setShippingCode(src.getShippingCode());
		tar.setDistributionMode(src.getDistributionMode());
		tar.setIsDefault(src.getIsDefault());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<DistributionModeItemDTO> toDTO(List<DistributionModeItemPO> srcs) {
		if (srcs == null)
			return null;
		List<DistributionModeItemDTO> list = new ArrayList<DistributionModeItemDTO>();
		for (DistributionModeItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<DistributionModeItemPO> toPO(List<DistributionModeItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<DistributionModeItemPO> list = new ArrayList<DistributionModeItemPO>();
		for (DistributionModeItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	