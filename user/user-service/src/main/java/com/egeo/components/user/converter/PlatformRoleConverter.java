package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.PlatformRoleDTO;
import com.egeo.components.user.po.PlatformRolePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-18 18:50:29
 */
public class PlatformRoleConverter {
	
	public static PlatformRoleDTO toDTO(PlatformRolePO src) {
		PlatformRoleDTO tar = new PlatformRoleDTO();
		tar.setId(src.getId());
		tar.setPlatformId(src.getPlatformId());
		tar.setRoleId(src.getRoleId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static PlatformRolePO toPO(PlatformRoleDTO src) {
		PlatformRolePO tar = new PlatformRolePO();
		tar.setId(src.getId());
		tar.setPlatformId(src.getPlatformId());
		tar.setRoleId(src.getRoleId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<PlatformRoleDTO> toDTO(List<PlatformRolePO> srcs) {
		if (srcs == null)
			return null;
		List<PlatformRoleDTO> list = new ArrayList<PlatformRoleDTO>();
		for (PlatformRolePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PlatformRolePO> toPO(List<PlatformRoleDTO> srcs) {
		if (srcs == null)
			return null;
		List<PlatformRolePO> list = new ArrayList<PlatformRolePO>();
		for (PlatformRoleDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	