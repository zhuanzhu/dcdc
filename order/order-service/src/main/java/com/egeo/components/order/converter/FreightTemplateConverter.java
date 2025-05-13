package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.FreightTemplateDTO;
import com.egeo.components.order.po.FreightTemplatePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class FreightTemplateConverter {
	
	public static FreightTemplateDTO toDTO(FreightTemplatePO src) {
		FreightTemplateDTO tar = new FreightTemplateDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setChargeWay(src.getChargeWay());
		tar.setIsDft(src.getIsDft());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static FreightTemplatePO toPO(FreightTemplateDTO src) {
		FreightTemplatePO tar = new FreightTemplatePO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setChargeWay(src.getChargeWay());
		tar.setIsDft(src.getIsDft());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<FreightTemplateDTO> toDTO(List<FreightTemplatePO> srcs) {
		if (srcs == null)
			return null;
		List<FreightTemplateDTO> list = new ArrayList<FreightTemplateDTO>();
		for (FreightTemplatePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FreightTemplatePO> toPO(List<FreightTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<FreightTemplatePO> list = new ArrayList<FreightTemplatePO>();
		for (FreightTemplateDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	