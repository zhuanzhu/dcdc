package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.MenuDTO;
import com.egeo.components.user.po.MenuPO;
import com.egeo.components.user.vo.MenuVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-18 18:50:29
 */
public class MenuConverter {

	public static MenuDTO toDTO(MenuVO src) {
		MenuDTO tar = new MenuDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setName(src.getName());	
		tar.setSort(src.getSort());	
		tar.setHref(src.getHref());	
		tar.setIcon(src.getIcon());	
		tar.setIsShow(src.getIsShow());	
		tar.setSysCode(src.getSysCode());
		tar.setPermission(src.getPermission());	
		tar.setRemarks(src.getRemarks());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}
	public static MenuDTO toDTO(MenuPO src) {
		MenuDTO tar = new MenuDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setName(src.getName());
		tar.setSort(src.getSort());
		tar.setHref(src.getHref());
		tar.setIcon(src.getIcon());
		tar.setIsShow(src.getIsShow());
		tar.setSysCode(src.getSysCode());
		tar.setPermission(src.getPermission());
		tar.setRemarks(src.getRemarks());
		tar.setPid(src.getPid());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MenuPO toPO(MenuDTO src) {
		MenuPO tar = new MenuPO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setName(src.getName());
		tar.setSort(src.getSort());
		tar.setHref(src.getHref());
		tar.setIcon(src.getIcon());
		tar.setIsShow(src.getIsShow());
		tar.setSysCode(src.getSysCode());
		tar.setPermission(src.getPermission());
		tar.setRemarks(src.getRemarks());
		tar.setPid(src.getPid());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MenuDTO> toDTO(List<MenuPO> srcs) {
		if (srcs == null)
			return null;
		List<MenuDTO> list = new ArrayList<MenuDTO>();
		for (MenuPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MenuPO> toPO(List<MenuDTO> srcs) {
		if (srcs == null)
			return null;
		List<MenuPO> list = new ArrayList<MenuPO>();
		for (MenuDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	