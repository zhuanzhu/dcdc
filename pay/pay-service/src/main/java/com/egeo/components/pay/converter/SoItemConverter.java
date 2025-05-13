package com.egeo.components.pay.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.pay.vo.SoItemVO;
import com.egeo.components.order.dto.SoItemDTO;

/**
 * 订单项DTO-VO
 * 
 * @author graci
 *
 */
public class SoItemConverter {

	public static List<SoItemVO> toVO(List<SoItemDTO> src){
		List<SoItemVO> tar=new ArrayList<>();
		for(SoItemDTO dto:src) {
			tar.add(toVO(dto));
		}
		return tar;
	}
	
	public static SoItemVO toVO(SoItemDTO src) {
		if (src == null)
			return null;
		SoItemVO tar = new SoItemVO();
		tar.setId(src.getId());
		tar.setSoChildId(src.getSoChildId());
		tar.setUserId(src.getUserId());
		tar.setUnitExist(src.getUnitExist());
		tar.setPuId(src.getPuId());
		tar.setPuCount(src.getPuCount());
		tar.setCartType(src.getCartType());
		tar.setPromotionId(src.getPromotionId());
		tar.setPrice(src.getPrice());
		tar.setPromotionAver(src.getPromotionAver());
		tar.setFinalPromotionAver(src.getFinalPromotionAver());
		tar.setSoId(src.getSoId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setPuName(src.getPuName());
		tar.setPuPicUrl(src.getPuPicUrl());
		tar.setUnitExist(src.getUnitExist());
		return tar;
	}
}
