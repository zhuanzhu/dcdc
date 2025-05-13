package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.SuListDTO;
import com.egeo.components.cms.po.SuListPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:50
 */
public class SuListConverter {
	
	public static SuListDTO toDTO(SuListPO src) {
		if (src == null)
		return null;	
		SuListDTO tar = new SuListDTO();
		tar.setId(src.getId());
		tar.setTitleName(src.getTitleName());
		tar.setTitleColor(src.getTitleColor());
		tar.setRelationType(src.getRelationType());
		tar.setSortType(src.getSortType());
		tar.setInstId(src.getInstId());
		tar.setMaxShow(src.getMaxShow());
		tar.setShowType(src.getShowType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSucId(src.getSucId());
		tar.setLinkableId(src.getLinkableId());
		tar.setBannerUrl(src.getBannerUrl());
		return tar;
	}

	public static SuListPO toPO(SuListDTO src) {
		if (src == null)
		return null;	
		SuListPO tar = new SuListPO();
		tar.setId(src.getId());
		tar.setTitleName(src.getTitleName());
		tar.setTitleColor(src.getTitleColor());
		tar.setRelationType(src.getRelationType());
		tar.setSortType(src.getSortType());
		tar.setInstId(src.getInstId());
		tar.setMaxShow(src.getMaxShow());
		tar.setShowType(src.getShowType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSucId(src.getSucId());
		tar.setLinkableId(src.getLinkableId());
		tar.setBannerUrl(src.getBannerUrl());
		return tar;
	}

	public static List<SuListDTO> toDTO(List<SuListPO> srcs) {
		if (srcs == null)
			return null;
		List<SuListDTO> list = new ArrayList<SuListDTO>();
		for (SuListPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SuListPO> toPO(List<SuListDTO> srcs) {
		if (srcs == null)
			return null;
		List<SuListPO> list = new ArrayList<SuListPO>();
		for (SuListDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	