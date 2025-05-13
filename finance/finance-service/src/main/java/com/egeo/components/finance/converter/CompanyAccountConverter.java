package com.egeo.components.finance.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.finance.dto.CompanyAccountDTO;
import com.egeo.components.finance.po.CompanyAccountPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-06 15:24:26
 */
public class CompanyAccountConverter {
	
	public static CompanyAccountDTO toDTO(CompanyAccountPO src) {
		if (src == null)
		return null;	
		CompanyAccountDTO tar = new CompanyAccountDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setBalance(src.getBalance());
		tar.setType(src.getType());
		tar.setCompanyId(src.getCompanyId());
		tar.setDisabled(src.getDisabled());
		tar.setLastRechargeTime(src.getLastRechargeTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCiphertext(src.getCiphertext());
		tar.setUuid(src.getUuid());
		tar.setPlatformId(src.getPlatformId());
		tar.setBeforeDisableBalance(src.getBeforeDisableBalance());
		return tar;
	}

	public static CompanyAccountPO toPO(CompanyAccountDTO src) {
		if (src == null)
		return null;	
		CompanyAccountPO tar = new CompanyAccountPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setBalance(src.getBalance());
		tar.setType(src.getType());
		tar.setCompanyId(src.getCompanyId());
		tar.setDisabled(src.getDisabled());
		tar.setLastRechargeTime(src.getLastRechargeTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCiphertext(src.getCiphertext());
		tar.setUuid(src.getUuid());
		tar.setPlatformId(src.getPlatformId());
		tar.setBeforeDisableBalance(src.getBeforeDisableBalance());
		return tar;
	}

	public static List<CompanyAccountDTO> toDTO(List<CompanyAccountPO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyAccountDTO> list = new ArrayList<CompanyAccountDTO>();
		for (CompanyAccountPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CompanyAccountPO> toPO(List<CompanyAccountDTO> srcs) {
		if (srcs == null)
			return null;
		List<CompanyAccountPO> list = new ArrayList<CompanyAccountPO>();
		for (CompanyAccountDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	