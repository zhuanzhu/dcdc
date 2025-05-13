package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.NavigationLabelDTO;
import com.egeo.components.cms.po.NavigationLabelPO;
import com.egeo.components.cms.vo.NavigationLabelVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-08-02 10:33:40
 */
public class NavigationLabelConverter {

	
	public static NavigationLabelDTO toDTO(NavigationLabelVO src) {
		if (src == null)
		return null;	
		NavigationLabelDTO tar = new NavigationLabelDTO();
		tar.setId(src.getId());
		tar.setNavigationBarId(src.getNavigationBarId());	
		tar.setLinkableButtonId(src.getLinkableButtonId());	
		tar.setNavigationLabelName(src.getNavigationLabelName());	
		tar.setNavigationLabelRemark(src.getNavigationLabelRemark());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static NavigationLabelVO toVO(NavigationLabelDTO src) {
		if (src == null)
		return null;	
		NavigationLabelVO tar = new NavigationLabelVO();
		tar.setId(src.getId());
		tar.setNavigationBarId(src.getNavigationBarId());	
		tar.setLinkableButtonId(src.getLinkableButtonId());	
		tar.setNavigationLabelName(src.getNavigationLabelName());	
		tar.setNavigationLabelRemark(src.getNavigationLabelRemark());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	

	public static List<NavigationLabelVO> toVO(List<NavigationLabelDTO> srcs) {
		if (srcs == null)
			return null;
		List<NavigationLabelVO> list = new ArrayList<NavigationLabelVO>();
		for (NavigationLabelDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static NavigationLabelDTO toDTO(NavigationLabelPO src) {
		if (src == null)
		return null;	
		NavigationLabelDTO tar = new NavigationLabelDTO();
		tar.setId(src.getId());
		tar.setNavigationBarId(src.getNavigationBarId());
		tar.setLinkableButtonId(src.getLinkableButtonId());
		tar.setNavigationLabelName(src.getNavigationLabelName());
		tar.setNavigationLabelRemark(src.getNavigationLabelRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static NavigationLabelPO toPO(NavigationLabelDTO src) {
		if (src == null)
		return null;	
		NavigationLabelPO tar = new NavigationLabelPO();
		tar.setId(src.getId());
		tar.setNavigationBarId(src.getNavigationBarId());
		tar.setLinkableButtonId(src.getLinkableButtonId());
		tar.setNavigationLabelName(src.getNavigationLabelName());
		tar.setNavigationLabelRemark(src.getNavigationLabelRemark());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<NavigationLabelDTO> toDTO(List<NavigationLabelPO> srcs) {
		if (srcs == null)
			return null;
		List<NavigationLabelDTO> list = new ArrayList<NavigationLabelDTO>();
		for (NavigationLabelPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<NavigationLabelPO> toPO(List<NavigationLabelDTO> srcs) {
		if (srcs == null)
			return null;
		List<NavigationLabelPO> list = new ArrayList<NavigationLabelPO>();
		for (NavigationLabelDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	