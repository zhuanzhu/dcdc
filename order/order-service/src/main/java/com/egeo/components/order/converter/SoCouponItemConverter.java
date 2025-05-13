package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoCouponItemDTO;
import com.egeo.components.order.po.SoCouponItemPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-24 17:02:14
 */
public class SoCouponItemConverter {
	
	public static SoCouponItemDTO toDTO(SoCouponItemPO src) {
		SoCouponItemDTO tar = new SoCouponItemDTO();
		tar.setId(src.getId());
		tar.setSoCouponId(src.getSoCouponId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setCouponCode(src.getCouponCode());
		tar.setMpId(src.getMpId());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setCouponAmount(src.getCouponAmount());
		tar.setStatus(src.getStatus());
		tar.setCouponId(src.getCouponId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static SoCouponItemPO toPO(SoCouponItemDTO src) {
		SoCouponItemPO tar = new SoCouponItemPO();
		tar.setId(src.getId());
		tar.setSoCouponId(src.getSoCouponId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setCouponCode(src.getCouponCode());
		tar.setMpId(src.getMpId());
		tar.setProductItemNum(src.getProductItemNum());
		tar.setCouponAmount(src.getCouponAmount());
		tar.setStatus(src.getStatus());
		tar.setCouponId(src.getCouponId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSkuId(src.getSkuId());
		return tar;
	}

	public static List<SoCouponItemDTO> toDTO(List<SoCouponItemPO> srcs) {
		if (srcs == null)
			return null;
		List<SoCouponItemDTO> list = new ArrayList<SoCouponItemDTO>();
		for (SoCouponItemPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoCouponItemPO> toPO(List<SoCouponItemDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoCouponItemPO> list = new ArrayList<SoCouponItemPO>();
		for (SoCouponItemDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	