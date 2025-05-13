package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.BannerCompanyDTO;
import com.egeo.components.cms.po.BannerCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:44
 */
public class BannerCompanyConverter {
	
	public static BannerCompanyDTO toDTO(BannerCompanyPO src) {
		if (src == null)
		return null;	
		BannerCompanyDTO tar = new BannerCompanyDTO();
		tar.setId(src.getId());
		tar.setBannerId(src.getBannerId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static BannerCompanyPO toPO(BannerCompanyDTO src) {
		if (src == null)
		return null;	
		BannerCompanyPO tar = new BannerCompanyPO();
		tar.setId(src.getId());
		tar.setBannerId(src.getBannerId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<BannerCompanyDTO> toDTO(List<BannerCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<BannerCompanyDTO> list = new ArrayList<BannerCompanyDTO>();
		for (BannerCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BannerCompanyPO> toPO(List<BannerCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<BannerCompanyPO> list = new ArrayList<BannerCompanyPO>();
		for (BannerCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	