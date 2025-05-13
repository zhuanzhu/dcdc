package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.ImagetextDTO;
import com.egeo.components.cms.po.ImagetextPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:47
 */
public class ImagetextConverter {
	
	public static ImagetextDTO toDTO(ImagetextPO src) {
		if (src == null)
		return null;	
		ImagetextDTO tar = new ImagetextDTO();
		tar.setId(src.getId());
		tar.setImgUrl(src.getImgUrl());
		tar.setLinkableId(src.getLinkableId());
		tar.setSort(src.getSort());
		tar.setGroupId(src.getGroupId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setName(src.getName());
		return tar;
	}

	public static ImagetextPO toPO(ImagetextDTO src) {
		if (src == null)
		return null;	
		ImagetextPO tar = new ImagetextPO();
		tar.setId(src.getId());
		tar.setImgUrl(src.getImgUrl());
		tar.setLinkableId(src.getLinkableId());
		tar.setSort(src.getSort());
		tar.setGroupId(src.getGroupId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setName(src.getName());
		return tar;
	}

	public static List<ImagetextDTO> toDTO(List<ImagetextPO> srcs) {
		if (srcs == null)
			return null;
		List<ImagetextDTO> list = new ArrayList<ImagetextDTO>();
		for (ImagetextPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ImagetextPO> toPO(List<ImagetextDTO> srcs) {
		if (srcs == null)
			return null;
		List<ImagetextPO> list = new ArrayList<ImagetextPO>();
		for (ImagetextDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	