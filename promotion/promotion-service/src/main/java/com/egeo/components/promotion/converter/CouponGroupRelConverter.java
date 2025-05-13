package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.CouponGroupRelDTO;
import com.egeo.components.promotion.po.CouponGroupRelPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-06-14 18:02:28
 */
public class CouponGroupRelConverter {
	
	public static CouponGroupRelDTO toDTO(CouponGroupRelPO src) {
		if (src == null)
		return null;	
		CouponGroupRelDTO tar = new CouponGroupRelDTO();
		tar.setId(src.getId());
		tar.setCouponGroupId(src.getCouponGroupId());
		tar.setCouponId(src.getCouponId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CouponGroupRelPO toPO(CouponGroupRelDTO src) {
		if (src == null)
		return null;	
		CouponGroupRelPO tar = new CouponGroupRelPO();
		tar.setId(src.getId());
		tar.setCouponGroupId(src.getCouponGroupId());
		tar.setCouponId(src.getCouponId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CouponGroupRelDTO> toDTO(List<CouponGroupRelPO> srcs) {
		if (srcs == null)
			return null;
		List<CouponGroupRelDTO> list = new ArrayList<CouponGroupRelDTO>();
		for (CouponGroupRelPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponGroupRelPO> toPO(List<CouponGroupRelDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponGroupRelPO> list = new ArrayList<CouponGroupRelPO>();
		for (CouponGroupRelDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	