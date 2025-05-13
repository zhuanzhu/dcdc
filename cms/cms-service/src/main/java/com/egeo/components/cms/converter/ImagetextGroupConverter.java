package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.ImagetextGroupDTO;
import com.egeo.components.cms.po.ImagetextGroupPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:48
 */
public class ImagetextGroupConverter {
	
	public static ImagetextGroupDTO toDTO(ImagetextGroupPO src) {
		if (src == null)
		return null;	
		ImagetextGroupDTO tar = new ImagetextGroupDTO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setTitle(src.getTitle());
		tar.setGroupType(src.getGroupType());
		return tar;
	}

	public static ImagetextGroupPO toPO(ImagetextGroupDTO src) {
		if (src == null)
		return null;	
		ImagetextGroupPO tar = new ImagetextGroupPO();
		tar.setId(src.getId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setTitle(src.getTitle());
		tar.setGroupType(src.getGroupType());
		return tar;
	}

	public static List<ImagetextGroupDTO> toDTO(List<ImagetextGroupPO> srcs) {
		if (srcs == null)
			return null;
		List<ImagetextGroupDTO> list = new ArrayList<ImagetextGroupDTO>();
		for (ImagetextGroupPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ImagetextGroupPO> toPO(List<ImagetextGroupDTO> srcs) {
		if (srcs == null)
			return null;
		List<ImagetextGroupPO> list = new ArrayList<ImagetextGroupPO>();
		for (ImagetextGroupDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	