package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoPackageTempDTO;
import com.egeo.components.order.po.SoPackageTempPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-02-02 11:28:22
 */
public class SoPackageTempConverter {
	
	public static SoPackageTempDTO toDTO(SoPackageTempPO src) {
		if (src == null)
		return null;	
		SoPackageTempDTO tar = new SoPackageTempDTO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setProCityArea(src.getProCityArea());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setDeliveryName(src.getDeliveryName());
		tar.setDeliveryNameMobile(src.getDeliveryNameMobile());
		tar.setSignName(src.getSignName());
		tar.setSignDate(src.getSignDate());
		tar.setSignRemark(src.getSignRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SoPackageTempPO toPO(SoPackageTempDTO src) {
		if (src == null)
		return null;	
		SoPackageTempPO tar = new SoPackageTempPO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setGoodReceiverName(src.getGoodReceiverName());
		tar.setGoodReceiverMobile(src.getGoodReceiverMobile());
		tar.setProCityArea(src.getProCityArea());
		tar.setGoodReceiverAddress(src.getGoodReceiverAddress());
		tar.setDeliveryDate(src.getDeliveryDate());
		tar.setDeliveryName(src.getDeliveryName());
		tar.setDeliveryNameMobile(src.getDeliveryNameMobile());
		tar.setSignName(src.getSignName());
		tar.setSignDate(src.getSignDate());
		tar.setSignRemark(src.getSignRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SoPackageTempDTO> toDTO(List<SoPackageTempPO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageTempDTO> list = new ArrayList<SoPackageTempDTO>();
		for (SoPackageTempPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoPackageTempPO> toPO(List<SoPackageTempDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoPackageTempPO> list = new ArrayList<SoPackageTempPO>();
		for (SoPackageTempDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	