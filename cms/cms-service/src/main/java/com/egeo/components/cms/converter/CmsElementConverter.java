package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsElementDTO;
import com.egeo.components.cms.po.CmsElementPO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-13 17:01:38
 */
public class CmsElementConverter {
	
	public static CmsElementDTO toDTO(CmsElementPO src) {
		if (src == null)
		return null;	
		CmsElementDTO tar = new CmsElementDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCode(src.getCode());
		tar.setGroupType(src.getGroupType());
		tar.setClientVersionAId(src.getClientVersionAId());
		tar.setClientVersionIId(src.getClientVersionIId());
		tar.setClientVersionARemark(src.getClientVersionARemark());
		tar.setClientVersionIRemark(src.getClientVersionIRemark());
		tar.setClientVersionACode(src.getClientVersionACode());
		tar.setClientVersionICode(src.getClientVersionICode());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSort(src.getSort());
		tar.setSampleImgUrl(src.getSampleImgUrl());
		return tar;
	}

	public static CmsElementPO toPO(CmsElementDTO src) {
		if (src == null)
		return null;	
		CmsElementPO tar = new CmsElementPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCode(src.getCode());
		tar.setGroupType(src.getGroupType());
		tar.setClientVersionAId(src.getClientVersionAId());
		tar.setClientVersionIId(src.getClientVersionIId());
		tar.setClientVersionARemark(src.getClientVersionARemark());
		tar.setClientVersionIRemark(src.getClientVersionIRemark());
		tar.setClientVersionACode(src.getClientVersionACode());
		tar.setClientVersionICode(src.getClientVersionICode());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setSort(src.getSort());
		tar.setSampleImgUrl(src.getSampleImgUrl());
		return tar;
	}

	public static List<CmsElementDTO> toDTO(List<CmsElementPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsElementDTO> list = new ArrayList<CmsElementDTO>();
		for (CmsElementPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsElementPO> toPO(List<CmsElementDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsElementPO> list = new ArrayList<CmsElementPO>();
		for (CmsElementDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	