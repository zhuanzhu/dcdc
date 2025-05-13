package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.CouponBatchCompanyDTO;
import com.egeo.components.promotion.po.CouponBatchCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-06-20 13:30:32
 */
public class CouponBatchCompanyConverter {
	
	public static CouponBatchCompanyDTO toDTO(CouponBatchCompanyPO src) {
		if (src == null)
		return null;	
		CouponBatchCompanyDTO tar = new CouponBatchCompanyDTO();
		tar.setId(src.getId());
		tar.setCouponBatchId(src.getCouponBatchId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CouponBatchCompanyPO toPO(CouponBatchCompanyDTO src) {
		if (src == null)
		return null;	
		CouponBatchCompanyPO tar = new CouponBatchCompanyPO();
		tar.setId(src.getId());
		tar.setCouponBatchId(src.getCouponBatchId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CouponBatchCompanyDTO> toDTO(List<CouponBatchCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<CouponBatchCompanyDTO> list = new ArrayList<CouponBatchCompanyDTO>();
		for (CouponBatchCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponBatchCompanyPO> toPO(List<CouponBatchCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponBatchCompanyPO> list = new ArrayList<CouponBatchCompanyPO>();
		for (CouponBatchCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	