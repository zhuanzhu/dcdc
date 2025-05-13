package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.BannerInstDTO;
import com.egeo.components.cms.po.BannerInstPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:45
 */
public class BannerInstConverter {
	
	public static BannerInstDTO toDTO(BannerInstPO src) {
		if (src == null)
		return null;	
		BannerInstDTO tar = new BannerInstDTO();
		tar.setId(src.getId());
		tar.setBannerId(src.getBannerId());
		tar.setInstId(src.getInstId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static BannerInstPO toPO(BannerInstDTO src) {
		if (src == null)
		return null;	
		BannerInstPO tar = new BannerInstPO();
		tar.setId(src.getId());
		tar.setBannerId(src.getBannerId());
		tar.setInstId(src.getInstId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<BannerInstDTO> toDTO(List<BannerInstPO> srcs) {
		if (srcs == null)
			return null;
		List<BannerInstDTO> list = new ArrayList<BannerInstDTO>();
		for (BannerInstPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BannerInstPO> toPO(List<BannerInstDTO> srcs) {
		if (srcs == null)
			return null;
		List<BannerInstPO> list = new ArrayList<BannerInstPO>();
		for (BannerInstDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	