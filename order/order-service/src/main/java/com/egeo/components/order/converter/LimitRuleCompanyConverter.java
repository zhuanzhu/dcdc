package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.LimitRuleCompanyDTO;
import com.egeo.components.order.po.LimitRuleCompanyPO;
import com.egeo.components.order.vo.LimitRuleCompanyVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-06-13 10:49:48
 */
public class LimitRuleCompanyConverter {

	public static LimitRuleCompanyDTO toDTO(LimitRuleCompanyVO src) {
		if (src == null)
		return null;	
		LimitRuleCompanyDTO tar = new LimitRuleCompanyDTO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());	
		tar.setCompanyId(src.getCompanyId());	
		return tar;
	}

	public static LimitRuleCompanyVO toVO(LimitRuleCompanyDTO src) {
		if (src == null)
		return null;	
		LimitRuleCompanyVO tar = new LimitRuleCompanyVO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());	
		tar.setCompanyId(src.getCompanyId());	
		return tar;
	}

	public static List<LimitRuleCompanyDTO> toDTOs(List<LimitRuleCompanyVO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleCompanyDTO> list = new ArrayList<LimitRuleCompanyDTO>();
		for (LimitRuleCompanyVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LimitRuleCompanyVO> toVO(List<LimitRuleCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleCompanyVO> list = new ArrayList<LimitRuleCompanyVO>();
		for (LimitRuleCompanyDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static LimitRuleCompanyDTO toDTO(LimitRuleCompanyPO src) {
		if (src == null)
		return null;	
		LimitRuleCompanyDTO tar = new LimitRuleCompanyDTO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	public static LimitRuleCompanyPO toPO(LimitRuleCompanyDTO src) {
		if (src == null)
		return null;	
		LimitRuleCompanyPO tar = new LimitRuleCompanyPO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());
		tar.setCompanyId(src.getCompanyId());
		return tar;
	}

	public static List<LimitRuleCompanyDTO> toDTO(List<LimitRuleCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleCompanyDTO> list = new ArrayList<LimitRuleCompanyDTO>();
		for (LimitRuleCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LimitRuleCompanyPO> toPO(List<LimitRuleCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleCompanyPO> list = new ArrayList<LimitRuleCompanyPO>();
		for (LimitRuleCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	