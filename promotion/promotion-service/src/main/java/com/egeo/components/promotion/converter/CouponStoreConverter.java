package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.CouponStoreDTO;
import com.egeo.components.promotion.po.CouponStorePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xia
 * @date 2018-09-11 20:07:35
 */
public class CouponStoreConverter {
	
	public static CouponStoreDTO toDTO(CouponStorePO src) {
		if (src == null)
		return null;	
		CouponStoreDTO tar = new CouponStoreDTO();
		tar.setId(src.getId());
		tar.setCouponId(src.getCouponId());
		tar.setStoreId(src.getStoreId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static CouponStorePO toPO(CouponStoreDTO src) {
		if (src == null)
		return null;	
		CouponStorePO tar = new CouponStorePO();
		tar.setId(src.getId());
		tar.setCouponId(src.getCouponId());
		tar.setStoreId(src.getStoreId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<CouponStoreDTO> toDTO(List<CouponStorePO> srcs) {
		if (srcs == null)
			return null;
		List<CouponStoreDTO> list = new ArrayList<CouponStoreDTO>();
		for (CouponStorePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponStorePO> toPO(List<CouponStoreDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponStorePO> list = new ArrayList<CouponStorePO>();
		for (CouponStoreDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	