package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PromotionScopeDTO;
import com.egeo.components.promotion.po.PromotionScopePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 16:42:11
 */
public class PromotionScopeConverter {
	
	public static PromotionScopeDTO toDTO(PromotionScopePO src) {
		PromotionScopeDTO tar = new PromotionScopeDTO();
		tar.setId(src.getId());
		tar.setMpId(src.getMpId());
		tar.setPromotionId(src.getPromotionId());
		tar.setScopeGroup(src.getScopeGroup());
		tar.setScopeType(src.getScopeType());
		tar.setCategoryid(src.getCategoryid());
		tar.setBrandid(src.getBrandid());
		tar.setHandlerStatus(src.getHandlerStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static PromotionScopePO toPO(PromotionScopeDTO src) {
		PromotionScopePO tar = new PromotionScopePO();
		tar.setId(src.getId());
		tar.setMpId(src.getMpId());
		tar.setPromotionId(src.getPromotionId());
		tar.setScopeGroup(src.getScopeGroup());
		tar.setScopeType(src.getScopeType());
		tar.setCategoryid(src.getCategoryid());
		tar.setBrandid(src.getBrandid());
		tar.setHandlerStatus(src.getHandlerStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<PromotionScopeDTO> toDTO(List<PromotionScopePO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionScopeDTO> list = new ArrayList<PromotionScopeDTO>();
		for (PromotionScopePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PromotionScopePO> toPO(List<PromotionScopeDTO> srcs) {
		if (srcs == null)
			return null;
		List<PromotionScopePO> list = new ArrayList<PromotionScopePO>();
		for (PromotionScopeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	