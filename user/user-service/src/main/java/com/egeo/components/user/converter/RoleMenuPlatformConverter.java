package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.RoleMenuPlatformDTO;
import com.egeo.components.user.po.RoleMenuPlatformPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-05-15 15:45:25
 */
public class RoleMenuPlatformConverter {
	
	public static RoleMenuPlatformDTO toDTO(RoleMenuPlatformPO src) {
		
		RoleMenuPlatformDTO tar = new RoleMenuPlatformDTO();
		tar.setId(src.getId());
		tar.setPlatformId(src.getPlatformId());
		tar.setRoleId(src.getRoleId());
		tar.setMenuId(src.getMenuId());
		return tar;
	}

	public static RoleMenuPlatformPO toPO(RoleMenuPlatformDTO src) {
		RoleMenuPlatformPO tar = new RoleMenuPlatformPO();
		tar.setId(src.getId());
		tar.setPlatformId(src.getPlatformId());
		tar.setRoleId(src.getRoleId());
		tar.setMenuId(src.getMenuId());
		return tar;
	}

	public static List<RoleMenuPlatformDTO> toDTO(List<RoleMenuPlatformPO> srcs) {
		if (srcs == null)
			return null;
		List<RoleMenuPlatformDTO> list = new ArrayList<RoleMenuPlatformDTO>();
		for (RoleMenuPlatformPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<RoleMenuPlatformPO> toPO(List<RoleMenuPlatformDTO> srcs) {
		if (srcs == null)
			return null;
		List<RoleMenuPlatformPO> list = new ArrayList<RoleMenuPlatformPO>();
		for (RoleMenuPlatformDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	