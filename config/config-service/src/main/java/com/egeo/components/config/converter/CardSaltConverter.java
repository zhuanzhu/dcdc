package com.egeo.components.config.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.config.dto.CardSaltDTO;
import com.egeo.components.config.po.CardSaltPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-12-26 10:43:17
 */
public class CardSaltConverter {
	
	public static CardSaltDTO toDTO(CardSaltPO src) {
		if (src == null)
		return null;	
		CardSaltDTO tar = new CardSaltDTO();
		tar.setId(src.getId());
		tar.setUuid(src.getUuid());
		tar.setSaltValue(src.getSaltValue());
		return tar;
	}

	public static CardSaltPO toPO(CardSaltDTO src) {
		if (src == null)
		return null;	
		CardSaltPO tar = new CardSaltPO();
		tar.setId(src.getId());
		tar.setUuid(src.getUuid());
		tar.setSaltValue(src.getSaltValue());
		return tar;
	}

	public static List<CardSaltDTO> toDTO(List<CardSaltPO> srcs) {
		if (srcs == null)
			return null;
		List<CardSaltDTO> list = new ArrayList<CardSaltDTO>();
		for (CardSaltPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CardSaltPO> toPO(List<CardSaltDTO> srcs) {
		if (srcs == null)
			return null;
		List<CardSaltPO> list = new ArrayList<CardSaltPO>();
		for (CardSaltDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	