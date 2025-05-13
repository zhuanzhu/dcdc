package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egeo.components.promotion.condition.CouponBatchCondition;
import com.egeo.components.promotion.dto.CouponBatchDTO;
import com.egeo.components.promotion.po.CouponBatchPO;
import com.egeo.components.promotion.vo.CouponBatchVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-06-19 14:19:36
 */
public class CouponBatchConverter {


	public static CouponBatchDTO toDTO(CouponBatchVO src) {
		if (src == null)
			return null;
		CouponBatchDTO tar = new CouponBatchDTO();
		tar.setId(src.getId());
		tar.setCouponBatchCode(src.getCouponBatchCode());
		tar.setCouponRelType(src.getCouponRelType());
		tar.setCouponRelId(src.getCouponRelId());
		tar.setGrantType(src.getGrantType());
		tar.setChooseWay(src.getChooseWay());
		tar.setCompanyId(src.getCompanyId());
		tar.setGrantCount(src.getGrantCount());
		tar.setIsRepeat(src.getIsRepeat());
		tar.setIsDisplay(src.getIsDisplay());
		tar.setReceiveStartTime(src.getReceiveStartTime() != null && src.getReceiveStartTime() != -1
				? new Date(src.getReceiveStartTime()) : null);
		tar.setReceiveEndTime(src.getReceiveEndTime() != null && src.getReceiveEndTime() != -1
				? new Date(src.getReceiveEndTime()) : null);
		tar.setEffectStartTime(src.getEffectStartTime() != null ? new Date(src.getEffectStartTime()) : null);
		tar.setEffectEndTime(src.getEffectEndTime() != null ? new Date(src.getEffectEndTime()) : null);
		tar.setEffectDays(src.getEffectDays());
		tar.setIsEffect(src.getIsEffect());
		tar.setCreator(src.getCreator());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setEmpList(src.getEmpList());
		tar.setCompList(src.getCompList());
		tar.setTitle(src.getTitle());
		tar.setPlatformId(src.getPlatformId());
		tar.setGetType(src.getGetType());
		tar.setLinkableButtonPageList(src.getLinkableButtonPageList());
		tar.setCouponBatchName(src.getCouponBatchName());
		return tar;
	}

	public static CouponBatchVO toVO(CouponBatchDTO src) {
		if (src == null)
			return null;
		CouponBatchVO tar = new CouponBatchVO();
		tar.setId(src.getId());
		tar.setCouponBatchCode(src.getCouponBatchCode());
		tar.setCouponRelType(src.getCouponRelType());
		tar.setCouponRelId(src.getCouponRelId());
		tar.setGrantType(src.getGrantType());
		tar.setChooseWay(src.getChooseWay());
		tar.setCompanyId(src.getCompanyId());
		tar.setGrantCount(src.getGrantCount());
		tar.setIsRepeat(src.getIsRepeat());
		tar.setIsDisplay(src.getIsDisplay());
		tar.setReceiveStartTime(src.getReceiveStartTime() != null ? src.getReceiveStartTime().getTime() : null);
		tar.setReceiveEndTime(src.getReceiveEndTime() != null ? src.getReceiveEndTime().getTime() : null);
		tar.setEffectStartTime(src.getEffectStartTime() != null ? src.getEffectStartTime().getTime() : null);
		tar.setEffectEndTime(src.getEffectEndTime() != null ? src.getEffectEndTime().getTime() : null);
		tar.setEffectDays(src.getEffectDays());
		tar.setIsEffect(src.getIsEffect());
		tar.setCreator(src.getCreator());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setEmpList(src.getEmpList());
		tar.setCompList(src.getCompList());
		tar.setTitle(src.getTitle());
		tar.setPlatformId(src.getPlatformId());
		tar.setGetType(src.getGetType());
		tar.setLinkableButtonPageList(src.getLinkableButtonPageList());
		tar.setCouponBatchName(src.getCouponBatchName());
		return tar;
	}

	public static List<CouponBatchDTO> toDTOs(List<CouponBatchVO> srcs) {
		if (srcs == null)
			return null;
		List<CouponBatchDTO> list = new ArrayList<CouponBatchDTO>();
		for (CouponBatchVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponBatchVO> toVO(List<CouponBatchDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponBatchVO> list = new ArrayList<CouponBatchVO>();
		for (CouponBatchDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CouponBatchDTO toDTO(CouponBatchPO src) {
		if (src == null)
			return null;
		CouponBatchDTO tar = new CouponBatchDTO();
		tar.setId(src.getId());
		tar.setCouponBatchCode(src.getCouponBatchCode());
		tar.setCouponRelType(src.getCouponRelType());
		tar.setCouponRelId(src.getCouponRelId());
		tar.setGrantType(src.getGrantType());
		tar.setChooseWay(src.getChooseWay());
		tar.setCompanyId(src.getCompanyId());
		tar.setGrantCount(src.getGrantCount());
		tar.setIsRepeat(src.getIsRepeat());
		tar.setIsDisplay(src.getIsDisplay());
		tar.setReceiveStartTime(src.getReceiveStartTime());
		tar.setReceiveEndTime(src.getReceiveEndTime());
		tar.setEffectStartTime(src.getEffectStartTime());
		tar.setEffectEndTime(src.getEffectEndTime());
		tar.setEffectDays(src.getEffectDays());
		tar.setIsEffect(src.getIsEffect());
		tar.setCreator(src.getCreator());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setGetType(src.getGetType());
		tar.setLinkableId(src.getLinkableId());
		tar.setCouponBatchName(src.getCouponBatchName());
		return tar;
	}

	public static CouponBatchPO toPO(CouponBatchDTO src) {
		if (src == null)
			return null;
		CouponBatchPO tar = new CouponBatchPO();
		tar.setId(src.getId());
		tar.setCouponBatchCode(src.getCouponBatchCode());
		tar.setCouponRelType(src.getCouponRelType());
		tar.setCouponRelId(src.getCouponRelId());
		tar.setGrantType(src.getGrantType());
		tar.setChooseWay(src.getChooseWay());
		tar.setCompanyId(src.getCompanyId());
		tar.setGrantCount(src.getGrantCount());
		tar.setIsRepeat(src.getIsRepeat());
		tar.setIsDisplay(src.getIsDisplay());
		tar.setReceiveStartTime(src.getReceiveStartTime());
		tar.setReceiveEndTime(src.getReceiveEndTime());
		tar.setEffectStartTime(src.getEffectStartTime());
		tar.setEffectEndTime(src.getEffectEndTime());
		tar.setEffectDays(src.getEffectDays());
		tar.setIsEffect(src.getIsEffect());
		tar.setCreator(src.getCreator());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setGetType(src.getGetType());
		tar.setLinkableId(src.getLinkableId());
		tar.setCouponBatchName(src.getCouponBatchName());
		return tar;
	}

	public static List<CouponBatchDTO> toDTO(List<CouponBatchPO> srcs) {
		if (srcs == null)
			return null;
		List<CouponBatchDTO> list = new ArrayList<CouponBatchDTO>();
		for (CouponBatchPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CouponBatchPO> toPO(List<CouponBatchDTO> srcs) {
		if (srcs == null)
			return null;
		List<CouponBatchPO> list = new ArrayList<CouponBatchPO>();
		for (CouponBatchDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	private static CouponBatchDTO conditionToDTO(CouponBatchCondition src) {
		CouponBatchDTO tar = toDTO(src);
		if (src != null) {
			tar.setTitle(src.getTitle());
			tar.setCouponId(src.getCouponId());
			tar.setCouponType(src.getCouponType());
			tar.setDetail(src.getDetail());
			tar.setTriggerAmount(src.getTriggerAmount());
			tar.setDiscountAmount(src.getDiscountAmount());
		}
		return tar;
	}

	public static List<CouponBatchDTO> conditionToDTO(List<CouponBatchCondition> srcs) {
		if (srcs == null)
			return null;
		List<CouponBatchDTO> list = new ArrayList<CouponBatchDTO>();
		for (CouponBatchCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}

}
