package com.egeo.components.cms.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.cms.dto.CmsLocalParamDTO;
import com.egeo.components.cms.po.CmsLocalParamPO;
import com.egeo.components.cms.vo.CmsLocalParamVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author mingqiang
 * @date 2019-01-10 11:11:52
 */
public class CmsLocalParamConverter {

	
	public static CmsLocalParamDTO toDTO(CmsLocalParamVO src) {
		if (src == null)
		return null;	
		CmsLocalParamDTO tar = new CmsLocalParamDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCode(src.getCode());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static CmsLocalParamVO toVO(CmsLocalParamDTO src) {
		if (src == null)
		return null;	
		CmsLocalParamVO tar = new CmsLocalParamVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setCode(src.getCode());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}


	public static List<CmsLocalParamVO> toVO(List<CmsLocalParamDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsLocalParamVO> list = new ArrayList<CmsLocalParamVO>();
		for (CmsLocalParamDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CmsLocalParamDTO toDTO(CmsLocalParamPO src) {
		if (src == null)
		return null;	
		CmsLocalParamDTO tar = new CmsLocalParamDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCode(src.getCode());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static CmsLocalParamPO toPO(CmsLocalParamDTO src) {
		if (src == null)
		return null;	
		CmsLocalParamPO tar = new CmsLocalParamPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setCode(src.getCode());
		tar.setPlatformId(src.getPlatformId());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<CmsLocalParamDTO> toDTO(List<CmsLocalParamPO> srcs) {
		if (srcs == null)
			return null;
		List<CmsLocalParamDTO> list = new ArrayList<CmsLocalParamDTO>();
		for (CmsLocalParamPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CmsLocalParamPO> toPO(List<CmsLocalParamDTO> srcs) {
		if (srcs == null)
			return null;
		List<CmsLocalParamPO> list = new ArrayList<CmsLocalParamPO>();
		for (CmsLocalParamDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	