package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoItemGiftcardDTO;
import com.egeo.components.order.po.SoItemGiftcardPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoItemGiftcardConverter {
	
	public static SoItemGiftcardDTO toDTO(SoItemGiftcardPO src) {
		SoItemGiftcardDTO tar = new SoItemGiftcardDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setGiftcardId(src.getGiftcardId());
		tar.setMpId(src.getMpId());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setGiftcardAmount(src.getGiftcardAmount());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static SoItemGiftcardPO toPO(SoItemGiftcardDTO src) {
		SoItemGiftcardPO tar = new SoItemGiftcardPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setGiftcardId(src.getGiftcardId());
		tar.setMpId(src.getMpId());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setGiftcardAmount(src.getGiftcardAmount());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<SoItemGiftcardDTO> toDTO(List<SoItemGiftcardPO> srcs) {
		if (srcs == null)
			return null;
		List<SoItemGiftcardDTO> list = new ArrayList<SoItemGiftcardDTO>();
		for (SoItemGiftcardPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoItemGiftcardPO> toPO(List<SoItemGiftcardDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoItemGiftcardPO> list = new ArrayList<SoItemGiftcardPO>();
		for (SoItemGiftcardDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	