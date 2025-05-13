package com.egeo.components.user.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.user.dto.VersionsChildDTO;
import com.egeo.components.user.po.VersionsChildPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-02-04 18:35:18
 */
public class VersionsChildConverter {
	
	public static VersionsChildDTO toDTO(VersionsChildPO src) {
		if (src == null)
		return null;	
		VersionsChildDTO tar = new VersionsChildDTO();
		tar.setId(src.getId());
		tar.setVersionsId(src.getVersionsId());
		tar.setChannelId(src.getChannelId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static VersionsChildPO toPO(VersionsChildDTO src) {
		if (src == null)
		return null;	
		VersionsChildPO tar = new VersionsChildPO();
		tar.setId(src.getId());
		tar.setVersionsId(src.getVersionsId());
		tar.setChannelId(src.getChannelId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<VersionsChildDTO> toDTO(List<VersionsChildPO> srcs) {
		if (srcs == null)
			return null;
		List<VersionsChildDTO> list = new ArrayList<VersionsChildDTO>();
		for (VersionsChildPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<VersionsChildPO> toPO(List<VersionsChildDTO> srcs) {
		if (srcs == null)
			return null;
		List<VersionsChildPO> list = new ArrayList<VersionsChildPO>();
		for (VersionsChildDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	