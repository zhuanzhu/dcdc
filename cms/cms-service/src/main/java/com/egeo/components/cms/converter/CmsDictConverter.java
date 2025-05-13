package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsDictDTO;
import com.egeo.components.cms.po.CmsDictPO;
import com.egeo.components.cms.vo.CmsDictVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author wyy
 * @date 2018-08-04 13:56:50
 */
public class CmsDictConverter {

	
	public static CmsDictDTO toDTO(CmsDictVO src) {
		if (src == null)
		return null;	
		CmsDictDTO tar = new CmsDictDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setCustomId(src.getCustomId());	
		tar.setName(src.getName());	
		tar.setDescription(src.getDescription());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static CmsDictVO toVO(CmsDictDTO src) {
		if (src == null)
		return null;	
		CmsDictVO tar = new CmsDictVO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());	
		tar.setCustomId(src.getCustomId());	
		tar.setName(src.getName());	
		tar.setDescription(src.getDescription());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}


	public static List<CmsDictVO> toVO(List<CmsDictDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsDictVO> list = new ArrayList<CmsDictVO>();
		for (CmsDictDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CmsDictDTO toDTO(CmsDictPO src) {
		if (src == null)
		return null;	
		CmsDictDTO tar = new CmsDictDTO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCustomId(src.getCustomId());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CmsDictPO toPO(CmsDictDTO src) {
		if (src == null)
		return null;	
		CmsDictPO tar = new CmsDictPO();
		tar.setId(src.getId());
		tar.setParentId(src.getParentId());
		tar.setCustomId(src.getCustomId());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CmsDictDTO> toDTO(List<CmsDictPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsDictDTO> list = new ArrayList<CmsDictDTO>();
		for (CmsDictPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsDictPO> toPO(List<CmsDictDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsDictPO> list = new ArrayList<CmsDictPO>();
		for (CmsDictDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	