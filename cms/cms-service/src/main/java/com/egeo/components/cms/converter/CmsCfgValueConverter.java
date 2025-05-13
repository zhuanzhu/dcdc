package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsCfgValueDTO;
import com.egeo.components.cms.po.CmsCfgValuePO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author tan
 * @date 2018-12-13 17:01:38
 */
public class CmsCfgValueConverter {
	
	public static CmsCfgValueDTO toDTO(CmsCfgValuePO src) {
		if (src == null)
		return null;	
		CmsCfgValueDTO tar = new CmsCfgValueDTO();
		tar.setId(src.getId());
		tar.setCode(src.getCode());
		tar.setName(src.getName());
		tar.setCfgKeyId(src.getCfgKeyId());
		tar.setSort(src.getSort());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CmsCfgValuePO toPO(CmsCfgValueDTO src) {
		if (src == null)
		return null;	
		CmsCfgValuePO tar = new CmsCfgValuePO();
		tar.setId(src.getId());
		tar.setCode(src.getCode());
		tar.setName(src.getName());
		tar.setCfgKeyId(src.getCfgKeyId());
		tar.setSort(src.getSort());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CmsCfgValueDTO> toDTO(List<CmsCfgValuePO> srcs) {
		if (srcs == null)
			return null;
		List<CmsCfgValueDTO> list = new ArrayList<CmsCfgValueDTO>();
		for (CmsCfgValuePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsCfgValuePO> toPO(List<CmsCfgValueDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsCfgValuePO> list = new ArrayList<CmsCfgValuePO>();
		for (CmsCfgValueDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	