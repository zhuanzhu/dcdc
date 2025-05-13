package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.LimitRuleDTO;
import com.egeo.components.order.po.LimitRulePO;
import com.egeo.components.order.vo.LimitRuleVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-06-13 15:54:11
 */
public class LimitRuleConverter {

	public static LimitRuleDTO toDTO(LimitRuleVO src) {
		if (src == null)
		return null;	
		LimitRuleDTO tar = new LimitRuleDTO();
		tar.setId(src.getId());
		tar.setSerialNumber(src.getSerialNumber());	
		tar.setType(src.getType());	
		tar.setName(src.getName());	
		tar.setIsStart(src.getIsStart());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());	
		tar.setIsLimit(src.getIsLimit());	
		tar.setSuLimitNum(src.getSuLimitNum());	
		tar.setLimitTimeType(src.getLimitTimeType());	
		tar.setLimitOriginTime(src.getLimitOriginTime());	
		tar.setLimitStopTime(src.getLimitStopTime());	
		tar.setPeriodType(src.getPeriodType());	
		tar.setUserLimitNum(src.getUserLimitNum());	
		tar.setUserMoneySum(src.getUserMoneySum());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateUserid(src.getUpdateUserid());	
		tar.setUpdateUsername(src.getUpdateUsername());	
		tar.setUpdateUserip(src.getUpdateUserip());	
		tar.setUpdateUsermac(src.getUpdateUsermac());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setLimitTarget(src.getLimitTarget());
		tar.setSuCombId(src.getSuCombId());
		tar.setSuLimitAmount(src.getSuLimitAmount());
		tar.setCompanyType(src.getCompanyType());
		tar.setLimitUnit(src.getLimitUnit());
		tar.setIsUserLimit(src.getIsUserLimit());
		tar.setIsCompanyLimit(src.getIsCompanyLimit());
		tar.setCompanyLimitNum(src.getCompanyLimitNum());
		tar.setCompanyMoneySum(src.getCompanyMoneySum());
		tar.setIsStoreLimit(src.getIsStoreLimit());
		tar.setStoreLimitNum(src.getStoreLimitNum());
		tar.setStoreMoneySum(src.getStoreMoneySum());
		return tar;
	}

	public static LimitRuleVO toVO(LimitRuleDTO src) {
		if (src == null)
		return null;	
		LimitRuleVO tar = new LimitRuleVO();
		tar.setId(src.getId());
		tar.setSerialNumber(src.getSerialNumber());	
		tar.setType(src.getType());	
		tar.setName(src.getName());	
		tar.setIsStart(src.getIsStart());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());	
		tar.setIsLimit(src.getIsLimit());	
		tar.setSuLimitNum(src.getSuLimitNum());	
		tar.setLimitTimeType(src.getLimitTimeType());	
		tar.setLimitOriginTime(src.getLimitOriginTime());	
		tar.setLimitStopTime(src.getLimitStopTime());	
		tar.setPeriodType(src.getPeriodType());	
		tar.setUserLimitNum(src.getUserLimitNum());	
		tar.setUserMoneySum(src.getUserMoneySum());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateUserid(src.getUpdateUserid());	
		tar.setUpdateUsername(src.getUpdateUsername());	
		tar.setUpdateUserip(src.getUpdateUserip());	
		tar.setUpdateUsermac(src.getUpdateUsermac());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());		
		tar.setLimitTarget(src.getLimitTarget());
		tar.setSuCombId(src.getSuCombId());
		tar.setSuLimitAmount(src.getSuLimitAmount());
		tar.setCompanyType(src.getCompanyType());
		tar.setLimitUnit(src.getLimitUnit());
		tar.setIsUserLimit(src.getIsUserLimit());
		tar.setIsCompanyLimit(src.getIsCompanyLimit());
		tar.setCompanyLimitNum(src.getCompanyLimitNum());
		tar.setCompanyMoneySum(src.getCompanyMoneySum());
		tar.setIsStoreLimit(src.getIsStoreLimit());
		tar.setStoreLimitNum(src.getStoreLimitNum());
		tar.setStoreMoneySum(src.getStoreMoneySum());
		return tar;
	}

	public static List<LimitRuleDTO> toDTOs(List<LimitRuleVO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleDTO> list = new ArrayList<LimitRuleDTO>();
		for (LimitRuleVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LimitRuleVO> toVO(List<LimitRuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleVO> list = new ArrayList<LimitRuleVO>();
		for (LimitRuleDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static LimitRuleDTO toDTO(LimitRulePO src) {
		if (src == null)
		return null;	
		LimitRuleDTO tar = new LimitRuleDTO();
		tar.setId(src.getId());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setIsStart(src.getIsStart());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());
		tar.setIsLimit(src.getIsLimit());
		tar.setSuLimitNum(src.getSuLimitNum());
		tar.setLimitTimeType(src.getLimitTimeType());
		tar.setLimitOriginTime(src.getLimitOriginTime());
		tar.setLimitStopTime(src.getLimitStopTime());
		tar.setPeriodType(src.getPeriodType());
		tar.setUserLimitNum(src.getUserLimitNum());
		tar.setUserMoneySum(src.getUserMoneySum());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());	
		tar.setLimitTarget(src.getLimitTarget());
		tar.setSuCombId(src.getSuCombId());
		tar.setSuLimitAmount(src.getSuLimitAmount());
		tar.setCompanyType(src.getCompanyType());
		tar.setLimitUnit(src.getLimitUnit());
		tar.setIsUserLimit(src.getIsUserLimit());
		tar.setIsCompanyLimit(src.getIsCompanyLimit());
		tar.setCompanyLimitNum(src.getCompanyLimitNum());
		tar.setCompanyMoneySum(src.getCompanyMoneySum());
		tar.setIsStoreLimit(src.getIsStoreLimit());
		tar.setStoreLimitNum(src.getStoreLimitNum());
		tar.setStoreMoneySum(src.getStoreMoneySum());
		return tar;
	}

	public static LimitRulePO toPO(LimitRuleDTO src) {
		if (src == null)
		return null;	
		LimitRulePO tar = new LimitRulePO();
		tar.setId(src.getId());
		tar.setSerialNumber(src.getSerialNumber());
		tar.setType(src.getType());
		tar.setName(src.getName());
		tar.setIsStart(src.getIsStart());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setStandardUnitSerialNumber(src.getStandardUnitSerialNumber());
		tar.setIsLimit(src.getIsLimit());
		tar.setSuLimitNum(src.getSuLimitNum());
		tar.setLimitTimeType(src.getLimitTimeType());
		tar.setLimitOriginTime(src.getLimitOriginTime());
		tar.setLimitStopTime(src.getLimitStopTime());
		tar.setPeriodType(src.getPeriodType());
		tar.setUserLimitNum(src.getUserLimitNum());
		tar.setUserMoneySum(src.getUserMoneySum());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());	
		tar.setLimitTarget(src.getLimitTarget());
		tar.setSuCombId(src.getSuCombId());
		tar.setSuLimitAmount(src.getSuLimitAmount());
		tar.setCompanyType(src.getCompanyType());
		tar.setLimitUnit(src.getLimitUnit());
		tar.setIsUserLimit(src.getIsUserLimit());
		tar.setIsCompanyLimit(src.getIsCompanyLimit());
		tar.setCompanyLimitNum(src.getCompanyLimitNum());
		tar.setCompanyMoneySum(src.getCompanyMoneySum());
		tar.setIsStoreLimit(src.getIsStoreLimit());
		tar.setStoreLimitNum(src.getStoreLimitNum());
		tar.setStoreMoneySum(src.getStoreMoneySum());
		return tar;
	}

	public static List<LimitRuleDTO> toDTO(List<LimitRulePO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRuleDTO> list = new ArrayList<LimitRuleDTO>();
		for (LimitRulePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LimitRulePO> toPO(List<LimitRuleDTO> srcs) {
		if (srcs == null)
			return null;
		List<LimitRulePO> list = new ArrayList<LimitRulePO>();
		for (LimitRuleDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	