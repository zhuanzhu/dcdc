package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.ECardTempDTO;
import com.egeo.components.promotion.po.ECardTempPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-11-16 03:28:54
 */
public class ECardTempConverter {
	
	public static ECardTempDTO toDTO(ECardTempPO src) {
		if (src == null)
		return null;	
		ECardTempDTO tar = new ECardTempDTO();
		tar.setId(src.getId());
		tar.setCardNumber(src.getCardNumber());
		tar.setCardThick(src.getCardThick());
		tar.setUserId(src.getUserId());
		tar.setSkuId(src.getSkuId());
		tar.setEndTime(src.getEndTime());
		tar.setShortUrl(src.getShortUrl());
		tar.setType(src.getType());
		tar.setSkuName(src.getSkuName());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());
		tar.setOrderCode(src.getOrderCode());
		return tar;
	}

	public static ECardTempPO toPO(ECardTempDTO src) {
		if (src == null)
		return null;	
		ECardTempPO tar = new ECardTempPO();
		tar.setId(src.getId());
		tar.setCardNumber(src.getCardNumber());
		tar.setCardThick(src.getCardThick());
		tar.setUserId(src.getUserId());
		tar.setSkuId(src.getSkuId());
		tar.setEndTime(src.getEndTime());
		tar.setShortUrl(src.getShortUrl());
		tar.setType(src.getType());
		tar.setSkuName(src.getSkuName());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());
		tar.setOrderCode(src.getOrderCode());
		return tar;
	}

	public static List<ECardTempDTO> toDTO(List<ECardTempPO> srcs) {
		if (srcs == null)
			return null;
		List<ECardTempDTO> list = new ArrayList<ECardTempDTO>();
		for (ECardTempPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ECardTempPO> toPO(List<ECardTempDTO> srcs) {
		if (srcs == null)
			return null;
		List<ECardTempPO> list = new ArrayList<ECardTempPO>();
		for (ECardTempDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	