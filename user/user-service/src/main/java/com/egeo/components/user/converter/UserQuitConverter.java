package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.UserQuitDTO;
import com.egeo.components.user.po.UserQuitPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author ghw
 * @date 2018-01-17 10:10:36
 */
public class UserQuitConverter {
	
	public static UserQuitDTO toDTO(UserQuitPO src) {
		if (src == null)
		return null;	
		UserQuitDTO tar = new UserQuitDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setCompanyId(src.getCompanyId());
		tar.setMemberCode(src.getMemberCode());
		tar.setName(src.getName());
		tar.setMail(src.getMail());
		tar.setQuitTime(src.getQuitTime());
		tar.setInvalidTime(src.getInvalidTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static UserQuitPO toPO(UserQuitDTO src) {
		if (src == null)
		return null;	
		UserQuitPO tar = new UserQuitPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setCompanyId(src.getCompanyId());
		tar.setMemberCode(src.getMemberCode());
		tar.setName(src.getName());
		tar.setMail(src.getMail());
		tar.setQuitTime(src.getQuitTime());
		tar.setInvalidTime(src.getInvalidTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<UserQuitDTO> toDTO(List<UserQuitPO> srcs) {
		if (srcs == null)
			return null;
		List<UserQuitDTO> list = new ArrayList<UserQuitDTO>();
		for (UserQuitPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UserQuitPO> toPO(List<UserQuitDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserQuitPO> list = new ArrayList<UserQuitPO>();
		for (UserQuitDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	