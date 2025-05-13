package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PointsRuleDTO;
import com.egeo.components.promotion.po.PointsRulePO;
import com.egeo.components.promotion.vo.PointsRuleVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-11-09 15:50:11
 */
public class PointsRuleConverter {

	
	public static PointsRuleDTO toDTO(PointsRuleVO src) {
		PointsRuleDTO tar = new PointsRuleDTO();
		tar.setId(src.getId());
		tar.setSingleProdHighestGain(src.getSingleProdHighestGain());	
		tar.setGainType(src.getGainType());	
		tar.setOrderMoneyRate(src.getOrderMoneyRate());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static PointsRuleVO toVO(PointsRuleDTO src) {
		PointsRuleVO tar = new PointsRuleVO();
		tar.setId(src.getId());
		tar.setSingleProdHighestGain(src.getSingleProdHighestGain());	
		tar.setGainType(src.getGainType());	
		tar.setOrderMoneyRate(src.getOrderMoneyRate());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<PointsRuleDTO> toDTOs(List<PointsRuleVO> srcs) {
		if (srcs == null)
			return null;
		List<PointsRuleDTO> list = new ArrayList<PointsRuleDTO>();
		for (PointsRuleVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PointsRuleVO> toVO(List<PointsRuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<PointsRuleVO> list = new ArrayList<PointsRuleVO>();
		for (PointsRuleDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static PointsRuleDTO toDTO(PointsRulePO src) {
		PointsRuleDTO tar = new PointsRuleDTO();
		tar.setId(src.getId());
		tar.setSingleProdHighestGain(src.getSingleProdHighestGain());
		tar.setGainType(src.getGainType());
		tar.setOrderMoneyRate(src.getOrderMoneyRate());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static PointsRulePO toPO(PointsRuleDTO src) {
		PointsRulePO tar = new PointsRulePO();
		tar.setId(src.getId());
		tar.setSingleProdHighestGain(src.getSingleProdHighestGain());
		tar.setGainType(src.getGainType());
		tar.setOrderMoneyRate(src.getOrderMoneyRate());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<PointsRuleDTO> toDTO(List<PointsRulePO> srcs) {
		if (srcs == null)
			return null;
		List<PointsRuleDTO> list = new ArrayList<PointsRuleDTO>();
		for (PointsRulePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PointsRulePO> toPO(List<PointsRuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<PointsRulePO> list = new ArrayList<PointsRulePO>();
		for (PointsRuleDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	