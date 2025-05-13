package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.UserInformationReadDTO;
import com.egeo.components.user.po.UserInformationReadPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-02-02 14:37:02
 */
public class UserInformationReadConverter {
	
	public static UserInformationReadDTO toDTO(UserInformationReadPO src) {
		if (src == null)
		return null;	
		UserInformationReadDTO tar = new UserInformationReadDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setUserInformationId(src.getUserInformationId());
		return tar;
	}

	public static UserInformationReadPO toPO(UserInformationReadDTO src) {
		if (src == null)
		return null;	
		UserInformationReadPO tar = new UserInformationReadPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setUserInformationId(src.getUserInformationId());
		return tar;
	}

	public static List<UserInformationReadDTO> toDTO(List<UserInformationReadPO> srcs) {
		if (srcs == null)
			return null;
		List<UserInformationReadDTO> list = new ArrayList<UserInformationReadDTO>();
		for (UserInformationReadPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UserInformationReadPO> toPO(List<UserInformationReadDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserInformationReadPO> list = new ArrayList<UserInformationReadPO>();
		for (UserInformationReadDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	