package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.UserRoleDTO;
import com.egeo.components.user.po.UserRolePO;
import com.egeo.components.user.vo.UserRoleVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-18 18:50:30
 */
public class UserRoleConverter {

	
	public static UserRoleDTO toDTO(UserRoleVO src) {
		UserRoleDTO tar = new UserRoleDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setRoleId(src.getRoleId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}
	public static UserRoleDTO toDTO(UserRolePO src) {
		UserRoleDTO tar = new UserRoleDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setRoleId(src.getRoleId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static UserRolePO toPO(UserRoleDTO src) {
		UserRolePO tar = new UserRolePO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setRoleId(src.getRoleId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<UserRoleDTO> toDTO(List<UserRolePO> srcs) {
		if (srcs == null)
			return null;
		List<UserRoleDTO> list = new ArrayList<UserRoleDTO>();
		for (UserRolePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<UserRolePO> toPO(List<UserRoleDTO> srcs) {
		if (srcs == null)
			return null;
		List<UserRolePO> list = new ArrayList<UserRolePO>();
		for (UserRoleDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	