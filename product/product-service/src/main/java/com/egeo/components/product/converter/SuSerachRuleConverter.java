package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SuSerachRuleDTO;
import com.egeo.components.product.po.SuSerachRulePO;
import com.egeo.components.product.vo.SuSerachRuleVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-07-25 14:32:19
 */
public class SuSerachRuleConverter {

	public static SuSerachRuleDTO toDTO(SuSerachRuleVO src) {
		if (src == null)
		return null;	
		SuSerachRuleDTO tar = new SuSerachRuleDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setStandardUnitName(src.getStandardUnitName());	
		tar.setStandardUnitKeyword(src.getStandardUnitKeyword());	
		tar.setStandardUnitTag(src.getStandardUnitTag());	
		tar.setStandardUnitCategory(src.getStandardUnitCategory());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());

		return tar;
	}

	public static SuSerachRuleVO toVO(SuSerachRuleDTO src) {
		if (src == null)
		return null;	
		SuSerachRuleVO tar = new SuSerachRuleVO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setStandardUnitName(src.getStandardUnitName());	
		tar.setStandardUnitKeyword(src.getStandardUnitKeyword());	
		tar.setStandardUnitTag(src.getStandardUnitTag());	
		tar.setStandardUnitCategory(src.getStandardUnitCategory());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());

		return tar;
	}

	public static List<SuSerachRuleDTO> toDTOs(List<SuSerachRuleVO> srcs) {
		if (srcs == null)
			return null;
		List<SuSerachRuleDTO> list = new ArrayList<SuSerachRuleDTO>();
		for (SuSerachRuleVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SuSerachRuleVO> toVO(List<SuSerachRuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<SuSerachRuleVO> list = new ArrayList<SuSerachRuleVO>();
		for (SuSerachRuleDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SuSerachRuleDTO toDTO(SuSerachRulePO src) {
		if (src == null)
		return null;	
		SuSerachRuleDTO tar = new SuSerachRuleDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setStandardUnitName(src.getStandardUnitName());
		tar.setStandardUnitKeyword(src.getStandardUnitKeyword());
		tar.setStandardUnitTag(src.getStandardUnitTag());
		tar.setStandardUnitCategory(src.getStandardUnitCategory());
		tar.setClientId(src.getClientId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCompanyType(src.getCompanyType());
		tar.setMerchantId(src.getMerchantId());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());
		return tar;
	}

	public static SuSerachRulePO toPO(SuSerachRuleDTO src) {
		if (src == null)
		return null;	
		SuSerachRulePO tar = new SuSerachRulePO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setStandardUnitName(src.getStandardUnitName());
		tar.setStandardUnitKeyword(src.getStandardUnitKeyword());
		tar.setStandardUnitTag(src.getStandardUnitTag());
		tar.setStandardUnitCategory(src.getStandardUnitCategory());
		tar.setClientId(src.getClientId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCompanyType(src.getCompanyType());
		tar.setMerchantId(src.getMerchantId());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setCategoryTreeNodeId(src.getCategoryTreeNodeId());

		return tar;
	}

	public static List<SuSerachRuleDTO> toDTO(List<SuSerachRulePO> srcs) {
		if (srcs == null)
			return null;
		List<SuSerachRuleDTO> list = new ArrayList<SuSerachRuleDTO>();
		for (SuSerachRulePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SuSerachRulePO> toPO(List<SuSerachRuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<SuSerachRulePO> list = new ArrayList<SuSerachRulePO>();
		for (SuSerachRuleDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	