package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CardThirdpartyAttValueDTO;
import com.egeo.components.product.po.CardThirdpartyAttValuePO;
import com.egeo.components.product.vo.CardThirdpartyAttValueVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-08-10 15:19:51
 */
public class CardThirdpartyAttValueConverter {
	
	
	public static CardThirdpartyAttValueDTO toDTO(CardThirdpartyAttValueVO src) {
		if (src == null)
		return null;	
		CardThirdpartyAttValueDTO tar = new CardThirdpartyAttValueDTO();
		tar.setId(src.getId());
		tar.setCardType(src.getCardType());	
		tar.setThirdpartyAttValueId(src.getThirdpartyAttValueId());	
		tar.setRemark(src.getRemark());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static CardThirdpartyAttValueVO toVO(CardThirdpartyAttValueDTO src) {
		if (src == null)
		return null;	
		CardThirdpartyAttValueVO tar = new CardThirdpartyAttValueVO();
		tar.setId(src.getId());
		tar.setCardType(src.getCardType());	
		tar.setThirdpartyAttValueId(src.getThirdpartyAttValueId());	
		tar.setRemark(src.getRemark());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<CardThirdpartyAttValueDTO> toDTOs(List<CardThirdpartyAttValueVO> srcs) {
		if (srcs == null)
			return null;
		List<CardThirdpartyAttValueDTO> list = new ArrayList<CardThirdpartyAttValueDTO>();
		for (CardThirdpartyAttValueVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CardThirdpartyAttValueVO> toVO(List<CardThirdpartyAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<CardThirdpartyAttValueVO> list = new ArrayList<CardThirdpartyAttValueVO>();
		for (CardThirdpartyAttValueDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CardThirdpartyAttValueDTO toDTO(CardThirdpartyAttValuePO src) {
		if (src == null)
		return null;	
		CardThirdpartyAttValueDTO tar = new CardThirdpartyAttValueDTO();
		tar.setId(src.getId());
		tar.setCardType(src.getCardType());
		tar.setThirdpartyAttValueId(src.getThirdpartyAttValueId());
		tar.setRemark(src.getRemark());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CardThirdpartyAttValuePO toPO(CardThirdpartyAttValueDTO src) {
		if (src == null)
		return null;	
		CardThirdpartyAttValuePO tar = new CardThirdpartyAttValuePO();
		tar.setId(src.getId());
		tar.setCardType(src.getCardType());
		tar.setThirdpartyAttValueId(src.getThirdpartyAttValueId());
		tar.setRemark(src.getRemark());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CardThirdpartyAttValueDTO> toDTO(List<CardThirdpartyAttValuePO> srcs) {
		if (srcs == null)
			return null;
		List<CardThirdpartyAttValueDTO> list = new ArrayList<CardThirdpartyAttValueDTO>();
		for (CardThirdpartyAttValuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CardThirdpartyAttValuePO> toPO(List<CardThirdpartyAttValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<CardThirdpartyAttValuePO> list = new ArrayList<CardThirdpartyAttValuePO>();
		for (CardThirdpartyAttValueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	