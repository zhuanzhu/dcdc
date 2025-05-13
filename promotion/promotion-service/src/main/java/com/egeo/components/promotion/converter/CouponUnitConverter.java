package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.promotion.condition.CouponUnitCondition;
import com.egeo.components.promotion.dto.CouponUnitDTO;
import com.egeo.components.promotion.po.CouponUnitPO;
import com.egeo.components.promotion.vo.CouponUnitVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-06-19 14:06:21
 */
public class CouponUnitConverter {

	
	public static CouponUnitDTO toDTO(CouponUnitVO src) {
		if (src == null)
		return null;	
		CouponUnitDTO tar = new CouponUnitDTO();
		tar.setId(src.getId());
		tar.setCouponUnitCode(src.getCouponUnitCode());	
		tar.setCouponId(src.getCouponId());	
		tar.setCouponBatchId(src.getCouponBatchId());	
		tar.setOrderId(src.getOrderId());
		tar.setUserId(src.getUserId());	
		tar.setUsedTime(src.getUsedTime() != null ? new Date(src.getUsedTime()) : null);	
		tar.setEffectStartTime(src.getEffectStartTime() != null ? new Date(src.getEffectStartTime()) : null);	
		tar.setEffectEndTime(src.getEffectEndTime() != null ? new Date(src.getEffectEndTime()) : null);	
		tar.setUsedCount(src.getUsedCount());	
		tar.setCouponUnitStatus(src.getCouponUnitStatus());	
		tar.setCreateTime(src.getCreateTime() != null ? new Date(src.getCreateTime()) : null);	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCouponBatchCode(src.getCouponBatchCode());
		tar.setCouponCode(src.getCouponCode());
		tar.setCouponType(src.getCouponType());
		tar.setPlatformId(src.getPlatformId());
		tar.setReceivedTime(src.getReceivedTime());
		tar.setBatchIndex(src.getBatchIndex());
		tar.setCouponBatchName(src.getCouponBatchName());
		tar.setIsRead(src.getIsRead());
		return tar;
	}

	public static CouponUnitVO toVO(CouponUnitDTO src) {
		if (src == null)
		return null;	
		CouponUnitVO tar = new CouponUnitVO();
		tar.setId(src.getId());
		tar.setCouponUnitCode(src.getCouponUnitCode());	
		tar.setCouponId(src.getCouponId());	
		tar.setCouponBatchId(src.getCouponBatchId());	
		tar.setOrderId(src.getOrderId());
		tar.setUserId(src.getUserId());	
		tar.setUsedTime(src.getUsedTime() != null ? src.getUsedTime().getTime() : null);	
		tar.setEffectStartTime(src.getEffectStartTime() != null ? src.getEffectStartTime().getTime() : null);	
		tar.setEffectEndTime(src.getEffectEndTime() != null ? src.getEffectEndTime().getTime() : null);	
		tar.setUsedCount(src.getUsedCount());	
		tar.setCouponUnitStatus(src.getCouponUnitStatus());	
		tar.setCreateTime(src.getCreateTime() != null ? src.getCreateTime().getTime() : null);	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCouponBatchCode(src.getCouponBatchCode());
		tar.setCouponCode(src.getCouponCode());
		tar.setCouponType(src.getCouponType());
		tar.setPlatformId(src.getPlatformId());
		tar.setReceivedTime(src.getReceivedTime());
		tar.setBatchIndex(src.getBatchIndex());
		tar.setCouponBatchName(src.getCouponBatchName());
		tar.setIsRead(src.getIsRead());
		return tar;
	}

	public static List<CouponUnitDTO> toDTOs(List<CouponUnitVO> srcs) {
		if (srcs == null)
			return null;
		List<CouponUnitDTO> list = new ArrayList<CouponUnitDTO>();
		for (CouponUnitVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponUnitVO> toVO(List<CouponUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponUnitVO> list = new ArrayList<CouponUnitVO>();
		for (CouponUnitDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CouponUnitDTO toDTO(CouponUnitPO src) {
		if (src == null)
			return null;
		CouponUnitDTO tar = new CouponUnitDTO();
		tar.setId(src.getId());
		tar.setCouponUnitCode(src.getCouponUnitCode());
		tar.setCouponId(src.getCouponId());
		tar.setCouponBatchId(src.getCouponBatchId());
		tar.setOrderId(src.getOrderId());
		tar.setUserId(src.getUserId());
		tar.setUsedTime(src.getUsedTime());
		tar.setUsedCount(src.getUsedCount());
		tar.setEffectStartTime(src.getEffectStartTime());
		tar.setEffectEndTime(src.getEffectEndTime());
		tar.setCouponUnitStatus(src.getCouponUnitStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setReceivedTime(src.getReceivedTime());
		tar.setBatchIndex(src.getBatchIndex());
		tar.setCouponBatchName(src.getCouponBatchName());
		tar.setIsRead(src.getIsRead());
		return tar;
	}

	public static CouponUnitPO toPO(CouponUnitDTO src) {
		if (src == null)
			return null;
		CouponUnitPO tar = new CouponUnitPO();
		tar.setId(src.getId());
		tar.setCouponUnitCode(src.getCouponUnitCode());
		tar.setCouponId(src.getCouponId());
		tar.setCouponBatchId(src.getCouponBatchId());
		tar.setOrderId(src.getOrderId());
		tar.setUserId(src.getUserId());
		tar.setUsedTime(src.getUsedTime());
		tar.setUsedCount(src.getUsedCount());
		tar.setEffectStartTime(src.getEffectStartTime());
		tar.setEffectEndTime(src.getEffectEndTime());
		tar.setCouponUnitStatus(src.getCouponUnitStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setReceivedTime(src.getReceivedTime());
		tar.setBatchIndex(src.getBatchIndex());
		tar.setCouponBatchName(src.getCouponBatchName());
		tar.setIsRead(src.getIsRead());
		return tar;
	}

	public static List<CouponUnitDTO> toDTO(List<CouponUnitPO> srcs) {
		if (srcs == null)
			return null;
		List<CouponUnitDTO> list = new ArrayList<CouponUnitDTO>();
		for (CouponUnitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponUnitPO> toPO(List<CouponUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponUnitPO> list = new ArrayList<CouponUnitPO>();
		for (CouponUnitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	public static List<CouponUnitDTO> conditionToDTO(List<CouponUnitCondition> srcs) {
		if (srcs == null)
			return null;
		List<CouponUnitDTO> list = new ArrayList<CouponUnitDTO>();
		for (CouponUnitCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}

	public static CouponUnitDTO conditionToDTO(CouponUnitCondition src) {
		CouponUnitDTO tar = toDTO(src);
		if (src != null) {
			// condition
			tar.setCouponBatchCode(src.getCouponBatchCode());
			tar.setCouponBatchId(src.getCouponBatchId());
			tar.setCouponCode(src.getCouponCode());
			tar.setCouponType(src.getCouponType());
			tar.setTitle(src.getTitle());
			tar.setGrantType(src.getGrantType());
			tar.setPlatformId(src.getPlatformId());
			tar.setBatchIndex(src.getBatchIndex());
			tar.setCouponBatchName(src.getCouponBatchName());

			// 客户端拓展信息
			tar.setDiscountAmount(src.getDiscountAmount());
			tar.setTriggerAmount(src.getTriggerAmount());
			tar.setIconUrl(src.getIconUrl());
			tar.setDetail(src.getDetail());
			tar.setCouponBatchEffectStartTime(src.getCouponBatchEffectStartTime());
			tar.setCouponBatchEffectEndTime(src.getCouponBatchEffectEndTime());
			tar.setGrantCount(src.getGrantCount());
			tar.setIsRepeat(src.getIsRepeat());
			tar.setCouponRelType(src.getCouponRelType());
			tar.setCouponRelId(src.getCouponRelId());
			tar.setGoodsType(src.getGoodsType());
			tar.setGoodsId(src.getGoodsId());
			tar.setJumpType(src.getJumpType());
			tar.setCompanyId(src.getCompanyId());
			tar.setEffectDays(src.getEffectDays());
			tar.setCouponId(src.getCouponId());
			tar.setGetType(src.getGetType());
			tar.setReceivedTime(src.getReceivedTime());
			tar.setCouponBatchReceiveEndTime(src.getCouponBatchReceiveEndTime());
			tar.setCouponBatchReceiveStartTime(src.getCouponBatchReceiveStartTime());
			tar.setIsRead(src.getIsRead());
		}
		return tar;
	}

	public static List<CouponUnitCondition> dtoToCondition(List<CouponUnitDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponUnitCondition> list = new ArrayList<CouponUnitCondition>();
		for (CouponUnitDTO src : srcs) {
			list.add(dtoToCondition(src));
		}
		return list;
	}

	public static CouponUnitCondition dtoToCondition(CouponUnitDTO src) {
		if (src == null)
			return null;
		CouponUnitCondition tar = new CouponUnitCondition();
		// po
		tar.setId(src.getId());
		tar.setCouponUnitCode(src.getCouponUnitCode());
		tar.setCouponId(src.getCouponId());
		tar.setCouponBatchId(src.getCouponBatchId());
		tar.setOrderId(src.getOrderId());
		tar.setUserId(src.getUserId());
		tar.setUsedTime(src.getUsedTime());
		tar.setUsedCount(src.getUsedCount());
		tar.setEffectStartTime(src.getEffectStartTime());
		tar.setEffectEndTime(src.getEffectEndTime());
		tar.setCouponUnitStatus(src.getCouponUnitStatus());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setBatchIndex(src.getBatchIndex());
		tar.setCouponBatchName(src.getCouponBatchName());



		// condition
		tar.setCouponBatchCode(src.getCouponBatchCode());
		tar.setCouponCode(src.getCouponCode());
		tar.setCouponType(src.getCouponType());
		tar.setTitle(src.getTitle());
		tar.setGrantType(src.getGrantType());
		tar.setReceivedTime(src.getReceivedTime());
		
		// 客户端拓展信息
		tar.setDiscountAmount(src.getDiscountAmount());
		tar.setTriggerAmount(src.getTriggerAmount());
		tar.setIconUrl(src.getIconUrl());
		tar.setDetail(src.getDetail());
		tar.setCouponBatchEffectStartTime(src.getCouponBatchEffectStartTime());
		tar.setCouponBatchEffectEndTime(src.getCouponBatchEffectEndTime());
		tar.setGrantCount(src.getGrantCount());
		tar.setIsRepeat(src.getIsRepeat());
		tar.setCouponRelType(src.getCouponRelType());
		tar.setCouponRelId(src.getCouponRelId());
		tar.setGoodsType(src.getGoodsType());
		tar.setGoodsId(src.getGoodsId());
		tar.setJumpType(src.getJumpType());
		tar.setCompanyId(src.getCompanyId());
		tar.setEffectDays(src.getEffectDays());
		tar.setStoreId(src.getStoreId());
		tar.setCouponBatchReceiveEndTime(src.getCouponBatchReceiveEndTime());
		tar.setCouponBatchReceiveStartTime(src.getCouponBatchReceiveStartTime());
		tar.setCouponBatchName(src.getCouponBatchName());
		tar.setIsRead(src.getIsRead());
		return tar;
	}
}
