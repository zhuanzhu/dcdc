package com.egeo.components.finance.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.finance.dto.UserAccountDTO;
import com.egeo.components.finance.po.UserAccountPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jiang
 * @date 2018-01-09 16:34:52
 */
public class UserAccountConverter {
	
	public static UserAccountDTO toDTO(UserAccountPO src) {
		if (src == null)
		return null;	
		UserAccountDTO tar = new UserAccountDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setBalance(src.getBalance());
		tar.setDisabled(src.getDisabled());
		tar.setLastRechargeTime(src.getLastRechargeTime());
		tar.setUuid(src.getUuid());
		tar.setCiphertext(src.getCiphertext());
		tar.setUserId(src.getUserId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setBeforeDisableBalance(src.getBeforeDisableBalance());
		return tar;
	}

	public static UserAccountPO toPO(UserAccountDTO src) {
		if (src == null)
		return null;	
		UserAccountPO tar = new UserAccountPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setBalance(src.getBalance());
		tar.setDisabled(src.getDisabled());
		tar.setLastRechargeTime(src.getLastRechargeTime());
		tar.setUuid(src.getUuid());
		tar.setCiphertext(src.getCiphertext());
		tar.setUserId(src.getUserId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setBeforeDisableBalance(src.getBeforeDisableBalance());
		return tar;
	}

	public static List<UserAccountDTO> toDTO(List<UserAccountPO> srcs) {
		if (srcs == null)
			return null;
		List<UserAccountDTO> list = new ArrayList<UserAccountDTO>();
		for (UserAccountPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UserAccountPO> toPO(List<UserAccountDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserAccountPO> list = new ArrayList<UserAccountPO>();
		for (UserAccountDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	