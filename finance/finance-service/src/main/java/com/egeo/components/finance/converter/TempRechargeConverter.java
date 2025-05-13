package com.egeo.components.finance.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.finance.dto.TempRechargeDTO;
import com.egeo.components.finance.po.TempRechargePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-11 18:22:01
 */
public class TempRechargeConverter {
	
	public static TempRechargeDTO toDTO(TempRechargePO src) {
		if (src == null)
		return null;	
		TempRechargeDTO tar = new TempRechargeDTO();
		tar.setId(src.getId());
		tar.setRownum(src.getRownum());
		tar.setUserName(src.getUserName());
		tar.setMemberCode(src.getMemberCode());
		tar.setEmail(src.getEmail());
		tar.setUserId(src.getUserId());
		tar.setSum(src.getSum());
		tar.setCompanyId(src.getCompanyId());
		tar.setCompanyName(src.getCompanyName());
		tar.setImpRes(src.getImpRes());
		tar.setSn(src.getSn());
		tar.setGeneratedTime(src.getGeneratedTime());
		tar.setTmplType(src.getTmplType());
		tar.setTmplName(src.getTmplName());
		tar.setAccountType(src.getAccountType());
		tar.setReasonId(src.getReasonId());
		tar.setBatchReason(src.getBatchReason());
		tar.setFlowReason(src.getFlowReason());
		tar.setRemark(src.getRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSalt(src.getSalt());
		tar.setFinBatch(src.getFinBatch());
		tar.setAccountId(src.getAccountId());
		tar.setStatus(src.getStatus());
		return tar;
	}

	public static TempRechargePO toPO(TempRechargeDTO src) {
		if (src == null)
		return null;	
		TempRechargePO tar = new TempRechargePO();
		tar.setId(src.getId());
		tar.setRownum(src.getRownum());
		tar.setUserName(src.getUserName());
		tar.setMemberCode(src.getMemberCode());
		tar.setEmail(src.getEmail());
		tar.setUserId(src.getUserId());
		tar.setSum(src.getSum());
		tar.setCompanyId(src.getCompanyId());
		tar.setCompanyName(src.getCompanyName());
		tar.setImpRes(src.getImpRes());
		tar.setSn(src.getSn());
		tar.setGeneratedTime(src.getGeneratedTime());
		tar.setTmplType(src.getTmplType());
		tar.setTmplName(src.getTmplName());
		tar.setAccountType(src.getAccountType());
		tar.setReasonId(src.getReasonId());
		tar.setBatchReason(src.getBatchReason());
		tar.setFlowReason(src.getFlowReason());
		tar.setRemark(src.getRemark());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setSalt(src.getSalt());
		tar.setFinBatch(src.getFinBatch());
		tar.setAccountId(src.getAccountId());
		tar.setStatus(src.getStatus());
		return tar;
	}

	public static List<TempRechargeDTO> toDTO(List<TempRechargePO> srcs) {
		if (srcs == null)
			return null;
		List<TempRechargeDTO> list = new ArrayList<TempRechargeDTO>();
		for (TempRechargePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<TempRechargePO> toPO(List<TempRechargeDTO> srcs) {
		if (srcs == null)
			return null;
		List<TempRechargePO> list = new ArrayList<TempRechargePO>();
		for (TempRechargeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	