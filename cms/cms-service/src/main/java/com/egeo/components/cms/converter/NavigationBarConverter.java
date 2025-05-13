package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.NavigationBarDTO;
import com.egeo.components.cms.po.NavigationBarPO;
import com.egeo.components.cms.vo.NavigationBarVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-08-01 17:43:31
 */
public class NavigationBarConverter {

	
	public static NavigationBarDTO toDTO(NavigationBarVO src) {
		if (src == null)
		return null;	
		NavigationBarDTO tar = new NavigationBarDTO();
		tar.setId(src.getId());
		tar.setNavigationBarType(src.getNavigationBarType());	
		tar.setNavigationBarName(src.getNavigationBarName());	
		tar.setNavigationBarStatus(src.getNavigationBarStatus());	
		tar.setUpdateUser(src.getUpdateUser());
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setNavigationLabelIdList(src.getNavigationLabelIdList());
		tar.setCompanyIdList(src.getCompanyIdList());
		tar.setPlatformId(src.getPlatformId());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static NavigationBarVO toVO(NavigationBarDTO src) {
		if (src == null)
		return null;	
		NavigationBarVO tar = new NavigationBarVO();
		tar.setId(src.getId());
		tar.setNavigationBarType(src.getNavigationBarType());	
		tar.setNavigationBarName(src.getNavigationBarName());	
		tar.setNavigationBarStatus(src.getNavigationBarStatus());	
		tar.setUpdateUser(src.getUpdateUser());
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setNavigationLabelIdList(src.getNavigationLabelIdList());
		tar.setCompanyIdList(src.getCompanyIdList());
		tar.setPlatformId(src.getPlatformId());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	

	public static List<NavigationBarVO> toVO(List<NavigationBarDTO> srcs) {
		if (srcs == null)
			return null;
		List<NavigationBarVO> list = new ArrayList<NavigationBarVO>();
		for (NavigationBarDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static NavigationBarDTO toDTO(NavigationBarPO src) {
		if (src == null)
		return null;	
		NavigationBarDTO tar = new NavigationBarDTO();
		tar.setId(src.getId());
		tar.setNavigationBarType(src.getNavigationBarType());
		tar.setNavigationBarName(src.getNavigationBarName());
		tar.setNavigationBarStatus(src.getNavigationBarStatus());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setNavigationLabelIdList(src.getNavigationLabelIdList());
		tar.setCompanyIdList(src.getCompanyIdList());
		tar.setPlatformId(src.getPlatformId());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static NavigationBarPO toPO(NavigationBarDTO src) {
		if (src == null)
		return null;	
		NavigationBarPO tar = new NavigationBarPO();
		tar.setId(src.getId());
		tar.setNavigationBarType(src.getNavigationBarType());
		tar.setNavigationBarName(src.getNavigationBarName());
		tar.setNavigationBarStatus(src.getNavigationBarStatus());
		tar.setUpdateUser(src.getUpdateUser());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setNavigationLabelIdList(src.getNavigationLabelIdList());
		tar.setCompanyIdList(src.getCompanyIdList());
		tar.setPlatformId(src.getPlatformId());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static List<NavigationBarDTO> toDTO(List<NavigationBarPO> srcs) {
		if (srcs == null)
			return null;
		List<NavigationBarDTO> list = new ArrayList<NavigationBarDTO>();
		for (NavigationBarPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<NavigationBarPO> toPO(List<NavigationBarDTO> srcs) {
		if (srcs == null)
			return null;
		List<NavigationBarPO> list = new ArrayList<NavigationBarPO>();
		for (NavigationBarDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	