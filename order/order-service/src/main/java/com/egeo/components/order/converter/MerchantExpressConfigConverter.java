package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.MerchantExpressConfigDTO;
import com.egeo.components.order.po.MerchantExpressConfigPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:24
 */
public class MerchantExpressConfigConverter {
	
	public static MerchantExpressConfigDTO toDTO(MerchantExpressConfigPO src) {
		MerchantExpressConfigDTO tar = new MerchantExpressConfigDTO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setType(src.getType());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setPhone(src.getPhone());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MerchantExpressConfigPO toPO(MerchantExpressConfigDTO src) {
		MerchantExpressConfigPO tar = new MerchantExpressConfigPO();
		tar.setId(src.getId());
		tar.setMerchantId(src.getMerchantId());
		tar.setType(src.getType());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setPhone(src.getPhone());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantExpressConfigDTO> toDTO(List<MerchantExpressConfigPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantExpressConfigDTO> list = new ArrayList<MerchantExpressConfigDTO>();
		for (MerchantExpressConfigPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantExpressConfigPO> toPO(List<MerchantExpressConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantExpressConfigPO> list = new ArrayList<MerchantExpressConfigPO>();
		for (MerchantExpressConfigDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	