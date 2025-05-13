package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionSkuDTO;
import com.egeo.components.promotion.po.PromotionSkuPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 16:42:11
 */
public class PromotionSkuConverter {
	
	public static PromotionSkuDTO toDTO(PromotionSkuPO src) {
		PromotionSkuDTO tar = new PromotionSkuDTO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setSynStatus(src.getSynStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setMpId(src.getMpId());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static PromotionSkuPO toPO(PromotionSkuDTO src) {
		PromotionSkuPO tar = new PromotionSkuPO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setSynStatus(src.getSynStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setMpId(src.getMpId());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<PromotionSkuDTO> toDTO(List<PromotionSkuPO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionSkuDTO> list = new ArrayList<PromotionSkuDTO>();
		for (PromotionSkuPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionSkuPO> toPO(List<PromotionSkuDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionSkuPO> list = new ArrayList<PromotionSkuPO>();
		for (PromotionSkuDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	