package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.LimitRuleRecordDTO;
import com.egeo.components.order.po.LimitRuleRecordPO;
import com.egeo.components.order.vo.LimitRuleRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-06-13 17:46:25
 */
public class LimitRuleRecordConverter {

	public static LimitRuleRecordDTO toDTO(LimitRuleRecordVO src) {
		if (src == null)
		return null;	
		LimitRuleRecordDTO tar = new LimitRuleRecordDTO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());	
		tar.setLimitRuleName(src.getLimitRuleName());	
		tar.setLimitRuleSerialNumber(src.getLimitRuleSerialNumber());	
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());	
		tar.setProductUnitId(src.getProductUnitId());	
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserMobile(src.getCreateUserMobile());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setBuySum(src.getBuySum());	
		tar.setBuyMoneySum(src.getBuyMoneySum());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setLimitType(src.getLimitType());
		tar.setLimitType(src.getLimitType());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setCompanyId(src.getCompanyId());
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static LimitRuleRecordVO toVO(LimitRuleRecordDTO src) {
		if (src == null)
		return null;	
		LimitRuleRecordVO tar = new LimitRuleRecordVO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());	
		tar.setLimitRuleName(src.getLimitRuleName());	
		tar.setLimitRuleSerialNumber(src.getLimitRuleSerialNumber());	
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());	
		tar.setProductUnitId(src.getProductUnitId());	
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserMobile(src.getCreateUserMobile());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setBuySum(src.getBuySum());	
		tar.setBuyMoneySum(src.getBuyMoneySum());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setLimitRuleId(src.getLimitRuleId());
		tar.setLimitType(src.getLimitType());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setCompanyId(src.getCompanyId());
		tar.setStoreId(src.getStoreId());
		return tar;
	}

	public static List<LimitRuleRecordDTO> toDTOs(List<LimitRuleRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleRecordDTO> list = new ArrayList<LimitRuleRecordDTO>();
		for (LimitRuleRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LimitRuleRecordVO> toVO(List<LimitRuleRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleRecordVO> list = new ArrayList<LimitRuleRecordVO>();
		for (LimitRuleRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static LimitRuleRecordDTO toDTO(LimitRuleRecordPO src) {
		if (src == null)
		return null;	
		LimitRuleRecordDTO tar = new LimitRuleRecordDTO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());
		tar.setLimitRuleName(src.getLimitRuleName());
		tar.setLimitRuleSerialNumber(src.getLimitRuleSerialNumber());
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());
		tar.setProductUnitId(src.getProductUnitId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserMobile(src.getCreateUserMobile());
		tar.setOrderCode(src.getOrderCode());
		tar.setBuySum(src.getBuySum());
		tar.setBuyMoneySum(src.getBuyMoneySum());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setLimitType(src.getLimitType());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setCompanyId(src.getCompanyId());
		tar.setStoreId(src.getStoreId());
		tar.setOrderStatus(src.getOrderStatus());
		return tar;
	}

	public static LimitRuleRecordPO toPO(LimitRuleRecordDTO src) {
		if (src == null)
		return null;	
		LimitRuleRecordPO tar = new LimitRuleRecordPO();
		tar.setId(src.getId());
		tar.setLimitRuleId(src.getLimitRuleId());
		tar.setLimitRuleName(src.getLimitRuleName());
		tar.setLimitRuleSerialNumber(src.getLimitRuleSerialNumber());
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());
		tar.setProductUnitId(src.getProductUnitId());
		tar.setProductUnitSerialNumber(src.getProductUnitSerialNumber());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserMobile(src.getCreateUserMobile());
		tar.setOrderCode(src.getOrderCode());
		tar.setBuySum(src.getBuySum());
		tar.setBuyMoneySum(src.getBuyMoneySum());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setLimitType(src.getLimitType());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setCompanyId(src.getCompanyId());
		tar.setStoreId(src.getStoreId());
		tar.setOrderStatus(src.getOrderStatus());
		return tar;
	}

	public static List<LimitRuleRecordDTO> toDTO(List<LimitRuleRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleRecordDTO> list = new ArrayList<LimitRuleRecordDTO>();
		for (LimitRuleRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LimitRuleRecordPO> toPO(List<LimitRuleRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleRecordPO> list = new ArrayList<LimitRuleRecordPO>();
		for (LimitRuleRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	