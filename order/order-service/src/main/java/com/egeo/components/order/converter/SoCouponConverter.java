package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.SoCouponDTO;
import com.egeo.components.order.po.SoCouponPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:53
 */
public class SoCouponConverter {
	
	public static SoCouponDTO toDTO(SoCouponPO src) {
		SoCouponDTO tar = new SoCouponDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setCouponCode(src.getCouponCode());
		tar.setCouponShareType(src.getCouponShareType());
		tar.setCouponAmount(src.getCouponAmount());
		tar.setCouponNum(src.getCouponNum());
		tar.setPlatformId(src.getPlatformId());
		tar.setCouponId(src.getCouponId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static SoCouponPO toPO(SoCouponDTO src) {
		SoCouponPO tar = new SoCouponPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setParentOrderCode(src.getParentOrderCode());
		tar.setCouponCode(src.getCouponCode());
		tar.setCouponShareType(src.getCouponShareType());
		tar.setCouponAmount(src.getCouponAmount());
		tar.setCouponNum(src.getCouponNum());
		tar.setPlatformId(src.getPlatformId());
		tar.setCouponId(src.getCouponId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<SoCouponDTO> toDTO(List<SoCouponPO> srcs) {
		if (srcs == null)
			return null;
		List<SoCouponDTO> list = new ArrayList<SoCouponDTO>();
		for (SoCouponPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SoCouponPO> toPO(List<SoCouponDTO> srcs) {
		if (srcs == null)
			return null;
		List<SoCouponPO> list = new ArrayList<SoCouponPO>();
		for (SoCouponDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	