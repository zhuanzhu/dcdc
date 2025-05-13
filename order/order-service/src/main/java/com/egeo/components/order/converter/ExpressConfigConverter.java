package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.ExpressConfigDTO;
import com.egeo.components.order.po.ExpressConfigPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:24
 */
public class ExpressConfigConverter {
	
	public static ExpressConfigDTO toDTO(ExpressConfigPO src) {
		ExpressConfigDTO tar = new ExpressConfigDTO();
		tar.setId(src.getId());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ExpressConfigPO toPO(ExpressConfigDTO src) {
		ExpressConfigPO tar = new ExpressConfigPO();
		tar.setId(src.getId());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<ExpressConfigDTO> toDTO(List<ExpressConfigPO> srcs) {
		if (srcs == null)
			return null;
		List<ExpressConfigDTO> list = new ArrayList<ExpressConfigDTO>();
		for (ExpressConfigPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExpressConfigPO> toPO(List<ExpressConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExpressConfigPO> list = new ArrayList<ExpressConfigPO>();
		for (ExpressConfigDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	