package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.condition.UserLoginCondition;
import com.egeo.components.user.dto.UserLoginDTO;
import com.egeo.components.user.po.UserLoginPO;
import com.egeo.components.user.vo.UserLoginVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-18 18:50:29
 */
public class UserLoginConverter {

	public static UserLoginDTO toDTO(UserLoginVO src) {
		UserLoginDTO tar = new UserLoginDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setUsername(src.getUsername());	
		tar.setLoginResult(src.getLoginResult());	
		tar.setLoginTime(src.getLoginTime());	
		tar.setIp(src.getIp());	
		tar.setBrowser(src.getBrowser());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStoreId(src.getStoreId());
		tar.setKeyMessage(src.getKeyMessage());
		tar.setLoginType(src.getLoginType());
		tar.setClientType(src.getClientType());
		return tar;
	}

	public static UserLoginDTO toDTO(UserLoginPO src) {
		UserLoginDTO tar = new UserLoginDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setUsername(src.getUsername());
		tar.setLoginResult(src.getLoginResult());
		tar.setLoginTime(src.getLoginTime());
		tar.setIp(src.getIp());
		tar.setBrowser(src.getBrowser());
		tar.setCompanyId(src.getCompanyId());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		tar.setKeyMessage(src.getKeyMessage());
		tar.setLoginType(src.getLoginType());
		tar.setClientType(src.getClientType());
		return tar;
	}

	public static UserLoginPO toPO(UserLoginDTO src) {
		UserLoginPO tar = new UserLoginPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setUsername(src.getUsername());
		tar.setLoginResult(src.getLoginResult());
		tar.setLoginTime(src.getLoginTime());
		tar.setIp(src.getIp());
		tar.setBrowser(src.getBrowser());
		tar.setCompanyId(src.getCompanyId());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		tar.setKeyMessage(src.getKeyMessage());
		tar.setLoginType(src.getLoginType());
		tar.setClientType(src.getClientType());
		return tar;
	}

	public static List<UserLoginDTO> toDTO(List<UserLoginPO> srcs) {
		if (srcs == null)
			return null;
		List<UserLoginDTO> list = new ArrayList<UserLoginDTO>();
		for (UserLoginPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UserLoginPO> toPO(List<UserLoginDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserLoginPO> list = new ArrayList<UserLoginPO>();
		for (UserLoginDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	public static UserLoginCondition toCondition(UserLoginDTO src) {
		UserLoginCondition tar = new UserLoginCondition();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setUsername(src.getUsername());
		tar.setLoginResult(src.getLoginResult());
		tar.setLoginTime(src.getLoginTime());
		tar.setIp(src.getIp());
		tar.setBrowser(src.getBrowser());
		tar.setCompanyId(src.getCompanyId());
		tar.setPlatformId(src.getPlatformId());
		tar.setStoreId(src.getStoreId());
		tar.setKeyMessage(src.getKeyMessage());
		tar.setLoginType(src.getLoginType());
		tar.setClientType(src.getClientType());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		return tar;
	}
	
	public static List<UserLoginCondition> toCondition(List<UserLoginDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserLoginCondition> list = new ArrayList<UserLoginCondition>();
		for (UserLoginDTO src : srcs) {
			list.add(toCondition(src));
		}
		return list;
	}
}
	