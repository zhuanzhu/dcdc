package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.BannerDTO;
import com.egeo.components.cms.dto.BannerPickDTO;
import com.egeo.components.cms.po.BannerPO;
import com.egeo.components.cms.po.BannerPickPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:44
 */
public class BannerConverter {
	
	public static BannerDTO toDTO(BannerPO src) {
		if (src == null)
		return null;	
		BannerDTO tar = new BannerDTO();
		tar.setId(src.getId());
		tar.setImgUrl(src.getImgUrl());
		tar.setName(src.getName());
		tar.setSort(src.getSort());
		tar.setLinkableId(src.getLinkableId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setEnabled(src.getEnabled());
		tar.setRemark(src.getRemark());
		tar.setBelongPage(src.getBelongPage());
		tar.setPlatformId(src.getPlatformId());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static BannerPO toPO(BannerDTO src) {
		if (src == null)
		return null;	
		BannerPO tar = new BannerPO();
		tar.setId(src.getId());
		tar.setImgUrl(src.getImgUrl());
		tar.setName(src.getName());
		tar.setSort(src.getSort());
		tar.setLinkableId(src.getLinkableId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setEnabled(src.getEnabled());
		tar.setRemark(src.getRemark());
		tar.setBelongPage(src.getBelongPage());
		tar.setPlatformId(src.getPlatformId());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}
	public static BannerPickPO toPO(BannerPickDTO src) {
		if (src == null)
			return null;	
		BannerPickPO tar = new BannerPickPO();
		tar.setName(src.getName());
		tar.setCompanyName(src.getCompanyName());
		tar.setLinkType(src.getLinkType());
		tar.setCompanyIds(src.getCompanyIds());
		return tar;
	}

	public static List<BannerDTO> toDTO(List<BannerPO> srcs) {
		if (srcs == null)
			return null;
		List<BannerDTO> list = new ArrayList<BannerDTO>();
		for (BannerPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BannerPO> toPO(List<BannerDTO> srcs) {
		if (srcs == null)
			return null;
		List<BannerPO> list = new ArrayList<BannerPO>();
		for (BannerDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	