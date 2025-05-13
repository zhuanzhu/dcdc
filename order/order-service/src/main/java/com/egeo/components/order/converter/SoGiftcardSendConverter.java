package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoGiftcardSendDTO;
import com.egeo.components.order.po.SoGiftcardSendPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoGiftcardSendConverter {
	
	public static SoGiftcardSendDTO toDTO(SoGiftcardSendPO src) {
		SoGiftcardSendDTO tar = new SoGiftcardSendDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setGiftcardId(src.getGiftcardId());
		tar.setGiftcardNo(src.getGiftcardNo());
		tar.setGiftcardPass(src.getGiftcardPass());
		tar.setCardAmount(src.getCardAmount());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoGiftcardSendPO toPO(SoGiftcardSendDTO src) {
		SoGiftcardSendPO tar = new SoGiftcardSendPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setGiftcardId(src.getGiftcardId());
		tar.setGiftcardNo(src.getGiftcardNo());
		tar.setGiftcardPass(src.getGiftcardPass());
		tar.setCardAmount(src.getCardAmount());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoGiftcardSendDTO> toDTO(List<SoGiftcardSendPO> srcs) {
		if (srcs == null)
			return null;
		List<SoGiftcardSendDTO> list = new ArrayList<SoGiftcardSendDTO>();
		for (SoGiftcardSendPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoGiftcardSendPO> toPO(List<SoGiftcardSendDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoGiftcardSendPO> list = new ArrayList<SoGiftcardSendPO>();
		for (SoGiftcardSendDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	