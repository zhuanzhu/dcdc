package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.ExpressMapConfigDTO;
import com.egeo.components.order.po.ExpressMapConfigPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-19 15:50:24
 */
public class ExpressMapConfigConverter {
	
	public static ExpressMapConfigDTO toDTO(ExpressMapConfigPO src) {
		ExpressMapConfigDTO tar = new ExpressMapConfigDTO();
		tar.setId(src.getId());
		tar.setDeliveryType(src.getDeliveryType());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setExpressCompanyId(src.getExpressCompanyId());
		tar.setExpressCompanyName(src.getExpressCompanyName());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ExpressMapConfigPO toPO(ExpressMapConfigDTO src) {
		ExpressMapConfigPO tar = new ExpressMapConfigPO();
		tar.setId(src.getId());
		tar.setDeliveryType(src.getDeliveryType());
		tar.setDeliveryCompanyName(src.getDeliveryCompanyName());
		tar.setExpressCompanyId(src.getExpressCompanyId());
		tar.setExpressCompanyName(src.getExpressCompanyName());
		tar.setDeliveryCompanyId(src.getDeliveryCompanyId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<ExpressMapConfigDTO> toDTO(List<ExpressMapConfigPO> srcs) {
		if (srcs == null)
			return null;
		List<ExpressMapConfigDTO> list = new ArrayList<ExpressMapConfigDTO>();
		for (ExpressMapConfigPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExpressMapConfigPO> toPO(List<ExpressMapConfigDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExpressMapConfigPO> list = new ArrayList<ExpressMapConfigPO>();
		for (ExpressMapConfigDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	