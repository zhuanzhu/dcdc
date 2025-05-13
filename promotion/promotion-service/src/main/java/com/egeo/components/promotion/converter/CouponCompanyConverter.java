package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.CouponCompanyDTO;
import com.egeo.components.promotion.po.CouponCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-06-14 13:49:35
 */
public class CouponCompanyConverter {
	
	public static CouponCompanyDTO toDTO(CouponCompanyPO src) {
		if (src == null)
		return null;	
		CouponCompanyDTO tar = new CouponCompanyDTO();
		tar.setId(src.getId());
		tar.setCouponId(src.getCouponId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CouponCompanyPO toPO(CouponCompanyDTO src) {
		if (src == null)
		return null;	
		CouponCompanyPO tar = new CouponCompanyPO();
		tar.setId(src.getId());
		tar.setCouponId(src.getCouponId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CouponCompanyDTO> toDTO(List<CouponCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<CouponCompanyDTO> list = new ArrayList<CouponCompanyDTO>();
		for (CouponCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponCompanyPO> toPO(List<CouponCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponCompanyPO> list = new ArrayList<CouponCompanyPO>();
		for (CouponCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	