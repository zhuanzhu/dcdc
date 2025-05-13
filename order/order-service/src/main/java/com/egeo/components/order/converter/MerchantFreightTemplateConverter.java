package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.MerchantFreightTemplateDTO;
import com.egeo.components.order.po.MerchantFreightTemplatePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class MerchantFreightTemplateConverter {
	
	public static MerchantFreightTemplateDTO toDTO(MerchantFreightTemplatePO src) {
		MerchantFreightTemplateDTO tar = new MerchantFreightTemplateDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setFreightTemplateId(src.getFreightTemplateId());
		tar.setCreateMerchantId(src.getCreateMerchantId());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static MerchantFreightTemplatePO toPO(MerchantFreightTemplateDTO src) {
		MerchantFreightTemplatePO tar = new MerchantFreightTemplatePO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setFreightTemplateId(src.getFreightTemplateId());
		tar.setCreateMerchantId(src.getCreateMerchantId());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<MerchantFreightTemplateDTO> toDTO(List<MerchantFreightTemplatePO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantFreightTemplateDTO> list = new ArrayList<MerchantFreightTemplateDTO>();
		for (MerchantFreightTemplatePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantFreightTemplatePO> toPO(List<MerchantFreightTemplateDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantFreightTemplatePO> list = new ArrayList<MerchantFreightTemplatePO>();
		for (MerchantFreightTemplateDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	