package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.RoleDTO;
import com.egeo.components.user.po.RolePO;
import com.egeo.components.user.vo.RoleVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-18 18:50:29
 */
public class RoleConverter {

	
	public static RoleDTO toDTO(RoleVO src) {
		RoleDTO tar = new RoleDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setMaxcreateTime(src.getMaxcreateTime());
		tar.setMincreateTime(src.getMincreateTime());
		tar.setEnname(src.getEnname());	
		tar.setRoleType(src.getRoleType());	
		tar.setIsSys(src.getIsSys());	
		tar.setSysCode(src.getSysCode());
		tar.setUseable(src.getUseable());	
		tar.setRemarks(src.getRemarks());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		return tar;
	}

	public static RoleDTO toDTO(RolePO src) {
		RoleDTO tar = new RoleDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setEnname(src.getEnname());
		tar.setRoleType(src.getRoleType());
		tar.setIsSys(src.getIsSys());
		tar.setSysCode(src.getSysCode());
		tar.setUseable(src.getUseable());
		tar.setRemarks(src.getRemarks());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		return tar;
	}

	public static RolePO toPO(RoleDTO src) {
		RolePO tar = new RolePO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setEnname(src.getEnname());
		tar.setRoleType(src.getRoleType());
		tar.setIsSys(src.getIsSys());
		tar.setSysCode(src.getSysCode());
		tar.setUseable(src.getUseable());
		tar.setRemarks(src.getRemarks());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUpdateUserId(src.getUpdateUserId());
		tar.setUpdateUserName(src.getUpdateUserName());
		return tar;
	}

	public static List<RoleDTO> toDTO(List<RolePO> srcs) {
		if (srcs == null)
			return null;
		List<RoleDTO> list = new ArrayList<RoleDTO>();
		for (RolePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<RolePO> toPO(List<RoleDTO> srcs) {
		if (srcs == null)
			return null;
		List<RolePO> list = new ArrayList<RolePO>();
		for (RoleDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	