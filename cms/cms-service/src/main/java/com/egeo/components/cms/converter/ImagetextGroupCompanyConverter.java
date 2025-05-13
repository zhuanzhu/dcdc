package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.ImagetextGroupCompanyDTO;
import com.egeo.components.cms.po.ImagetextGroupCompanyPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author jzh
 * @date 2018-04-04 13:24:48
 */
public class ImagetextGroupCompanyConverter {
	
	public static ImagetextGroupCompanyDTO toDTO(ImagetextGroupCompanyPO src) {
		if (src == null)
		return null;	
		ImagetextGroupCompanyDTO tar = new ImagetextGroupCompanyDTO();
		tar.setId(src.getId());
		tar.setGroupId(src.getGroupId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static ImagetextGroupCompanyPO toPO(ImagetextGroupCompanyDTO src) {
		if (src == null)
		return null;	
		ImagetextGroupCompanyPO tar = new ImagetextGroupCompanyPO();
		tar.setId(src.getId());
		tar.setGroupId(src.getGroupId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<ImagetextGroupCompanyDTO> toDTO(List<ImagetextGroupCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<ImagetextGroupCompanyDTO> list = new ArrayList<ImagetextGroupCompanyDTO>();
		for (ImagetextGroupCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ImagetextGroupCompanyPO> toPO(List<ImagetextGroupCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<ImagetextGroupCompanyPO> list = new ArrayList<ImagetextGroupCompanyPO>();
		for (ImagetextGroupCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	