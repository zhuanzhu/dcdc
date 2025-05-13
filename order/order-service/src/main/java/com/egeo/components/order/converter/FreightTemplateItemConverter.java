package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.FreightTemplateItemDTO;
import com.egeo.components.order.po.FreightTemplateItemPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class FreightTemplateItemConverter {
	
	public static FreightTemplateItemDTO toDTO(FreightTemplateItemPO src) {
		FreightTemplateItemDTO tar = new FreightTemplateItemDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setFreightTemplateId(src.getFreightTemplateId());
		tar.setDistributionCode(src.getDistributionCode());
		tar.setType(src.getType());
		tar.setChargeWay(src.getChargeWay());
		tar.setFreightRule(src.getFreightRule());
		tar.setDistributionRegion(src.getDistributionRegion());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static FreightTemplateItemPO toPO(FreightTemplateItemDTO src) {
		FreightTemplateItemPO tar = new FreightTemplateItemPO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setFreightTemplateId(src.getFreightTemplateId());
		tar.setDistributionCode(src.getDistributionCode());
		tar.setType(src.getType());
		tar.setChargeWay(src.getChargeWay());
		tar.setFreightRule(src.getFreightRule());
		tar.setDistributionRegion(src.getDistributionRegion());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<FreightTemplateItemDTO> toDTO(List<FreightTemplateItemPO> srcs) {
		if (srcs == null)
			return null;
		List<FreightTemplateItemDTO> list = new ArrayList<FreightTemplateItemDTO>();
		for (FreightTemplateItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FreightTemplateItemPO> toPO(List<FreightTemplateItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<FreightTemplateItemPO> list = new ArrayList<FreightTemplateItemPO>();
		for (FreightTemplateItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	