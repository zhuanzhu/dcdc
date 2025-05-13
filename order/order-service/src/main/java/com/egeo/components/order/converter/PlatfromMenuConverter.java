package com.egeo.components.order.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.order.dto.PlatfromMenuDTO;
import com.egeo.components.order.po.PlatfromMenuPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-12 12:57:52
 */
public class PlatfromMenuConverter {
	
	public static PlatfromMenuDTO toDTO(PlatfromMenuPO src) {
		PlatfromMenuDTO tar = new PlatfromMenuDTO();
		tar.setId(src.getId());
		tar.setMenuCode(src.getMenuCode());
		tar.setMenuName(src.getMenuName());
		tar.setTabShow(src.getTabShow());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static PlatfromMenuPO toPO(PlatfromMenuDTO src) {
		PlatfromMenuPO tar = new PlatfromMenuPO();
		tar.setId(src.getId());
		tar.setMenuCode(src.getMenuCode());
		tar.setMenuName(src.getMenuName());
		tar.setTabShow(src.getTabShow());
		tar.setPlatformId(src.getPlatformId());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<PlatfromMenuDTO> toDTO(List<PlatfromMenuPO> srcs) {
		if (srcs == null)
			return null;
		List<PlatfromMenuDTO> list = new ArrayList<PlatfromMenuDTO>();
		for (PlatfromMenuPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PlatfromMenuPO> toPO(List<PlatfromMenuDTO> srcs) {
		if (srcs == null)
			return null;
		List<PlatfromMenuPO> list = new ArrayList<PlatfromMenuPO>();
		for (PlatfromMenuDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	