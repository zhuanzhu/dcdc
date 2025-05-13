package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionScopeRecordDTO;
import com.egeo.components.promotion.po.PromotionScopeRecordPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 16:42:11
 */
public class PromotionScopeRecordConverter {
	
	public static PromotionScopeRecordDTO toDTO(PromotionScopeRecordPO src) {
		PromotionScopeRecordDTO tar = new PromotionScopeRecordDTO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setScopeGroupId(src.getScopeGroupId());
		tar.setScopeType(src.getScopeType());
		tar.setPromPrice(src.getPromPrice());
		tar.setIsMutex(src.getIsMutex());
		tar.setStatus(src.getStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setMpId(src.getMpId());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static PromotionScopeRecordPO toPO(PromotionScopeRecordDTO src) {
		PromotionScopeRecordPO tar = new PromotionScopeRecordPO();
		tar.setId(src.getId());
		tar.setPromotionId(src.getPromotionId());
		tar.setScopeGroupId(src.getScopeGroupId());
		tar.setScopeType(src.getScopeType());
		tar.setPromPrice(src.getPromPrice());
		tar.setIsMutex(src.getIsMutex());
		tar.setStatus(src.getStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setMpId(src.getMpId());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<PromotionScopeRecordDTO> toDTO(List<PromotionScopeRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionScopeRecordDTO> list = new ArrayList<PromotionScopeRecordDTO>();
		for (PromotionScopeRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionScopeRecordPO> toPO(List<PromotionScopeRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionScopeRecordPO> list = new ArrayList<PromotionScopeRecordPO>();
		for (PromotionScopeRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	