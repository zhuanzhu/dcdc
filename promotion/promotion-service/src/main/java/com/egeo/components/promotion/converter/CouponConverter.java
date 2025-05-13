package com.egeo.components.promotion.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.CouponDTO;
import com.egeo.components.promotion.po.CouponPO;
import com.egeo.components.promotion.vo.CouponVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-06-14 10:56:21
 */
public class CouponConverter {

	public static CouponDTO toDTO(CouponVO src) {
		if (src == null)
		return null;	
		CouponDTO tar = new CouponDTO();
		tar.setId(src.getId());
		tar.setCouponCode(src.getCouponCode());	
		tar.setTitle(src.getTitle());	
		tar.setCouponType(src.getCouponType());	
		tar.setDiscountAmount(src.getDiscountAmount() != null ? new BigDecimal(src.getDiscountAmount()) : null);	
		tar.setTriggerAmount(src.getTriggerAmount() != null ? new BigDecimal(src.getTriggerAmount()) : null);	
		tar.setGoodsType(src.getGoodsType());	
		tar.setGoodsId(src.getGoodsId());	
		tar.setUsageCount(src.getUsageCount());	
		tar.setJumpType(src.getJumpType());	
		tar.setIconUrl(src.getIconUrl());	
		tar.setDetail(src.getDetail());	
		tar.setUpdateUser(src.getUpdateUser());	
		tar.setIsDelete(src.getIsDelete());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCompanyList(src.getCompanyList());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreList(src.getStoreList());
		tar.setStoreName(src.getStoreName());
		return tar;
	}

	public static CouponVO toVO(CouponDTO src) {
		if (src == null)
		return null;	
		CouponVO tar = new CouponVO();
		tar.setId(src.getId());
		tar.setCouponCode(src.getCouponCode());	
		tar.setTitle(src.getTitle());	
		tar.setCouponType(src.getCouponType());	
		tar.setDiscountAmount(src.getDiscountAmount() != null ? src.getDiscountAmount().intValue() : null);	
		tar.setTriggerAmount(src.getTriggerAmount() != null ? src.getTriggerAmount().intValue() : null);	
		tar.setGoodsType(src.getGoodsType());	
		tar.setGoodsId(src.getGoodsId());	
		tar.setUsageCount(src.getUsageCount());	
		tar.setJumpType(src.getJumpType());	
		tar.setIconUrl(src.getIconUrl());	
		tar.setDetail(src.getDetail());	
		tar.setUpdateUser(src.getUpdateUser());	
		tar.setIsDelete(src.getIsDelete());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCompanyList(src.getCompanyList());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CouponDTO> toDTOs(List<CouponVO> srcs) {
		if (srcs == null)
			return null;
		List<CouponDTO> list = new ArrayList<CouponDTO>();
		for (CouponVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponVO> toVO(List<CouponDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponVO> list = new ArrayList<CouponVO>();
		for (CouponDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CouponDTO toDTO(CouponPO src) {
		if (src == null)
		return null;	
		CouponDTO tar = new CouponDTO();
		tar.setId(src.getId());
		tar.setCouponCode(src.getCouponCode());
		tar.setTitle(src.getTitle());
		tar.setCouponType(src.getCouponType());
		tar.setDiscountAmount(src.getDiscountAmount());
		tar.setTriggerAmount(src.getTriggerAmount());
		tar.setGoodsType(src.getGoodsType());
		tar.setGoodsId(src.getGoodsId());
		tar.setUsageCount(src.getUsageCount());
		tar.setJumpType(src.getJumpType());
		tar.setIconUrl(src.getIconUrl());
		tar.setDetail(src.getDetail());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setIsDelete(src.getIsDelete());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCompanyList(src.getCompanyList());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreName(src.getStoreName());
		return tar;
	}

	public static CouponPO toPO(CouponDTO src) {
		if (src == null)
		return null;	
		CouponPO tar = new CouponPO();
		tar.setId(src.getId());
		tar.setCouponCode(src.getCouponCode());
		tar.setTitle(src.getTitle());
		tar.setCouponType(src.getCouponType());
		tar.setDiscountAmount(src.getDiscountAmount());
		tar.setTriggerAmount(src.getTriggerAmount());
		tar.setGoodsType(src.getGoodsType());
		tar.setGoodsId(src.getGoodsId());
		tar.setUsageCount(src.getUsageCount());
		tar.setJumpType(src.getJumpType());
		tar.setIconUrl(src.getIconUrl());
		tar.setDetail(src.getDetail());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setIsDelete(src.getIsDelete());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCompanyList(src.getCompanyList());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreList(src.getStoreList());
		tar.setStoreName(src.getStoreName());
		return tar;
	}

	public static List<CouponDTO> toDTO(List<CouponPO> srcs) {
		if (srcs == null)
			return null;
		List<CouponDTO> list = new ArrayList<CouponDTO>();
		for (CouponPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponPO> toPO(List<CouponDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponPO> list = new ArrayList<CouponPO>();
		for (CouponDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	