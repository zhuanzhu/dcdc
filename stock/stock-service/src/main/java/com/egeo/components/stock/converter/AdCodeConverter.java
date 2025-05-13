package com.egeo.components.stock.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.stock.dto.AdCodeDTO;
import com.egeo.components.stock.po.AdCodePO;
import com.egeo.components.stock.vo.AdCodeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-23 11:20:03
 */
public class AdCodeConverter {

	
	public static AdCodeDTO toDTO(AdCodeVO src) {
		AdCodeDTO tar = new AdCodeDTO();
		tar.setId(src.getId());
		tar.setPageType(src.getPageType());	
		tar.setCode(src.getCode());	
		tar.setName(src.getName());	
		tar.setParentCode(src.getParentCode());	
		tar.setLevel(src.getLevel());	
		tar.setSort(src.getSort());	
		tar.setPlatform(src.getPlatform());	
		tar.setWidth(src.getWidth());	
		tar.setHeight(src.getHeight());	
		tar.setSourceSizeLimit(src.getSourceSizeLimit());	
		tar.setCreateBy(src.getCreateBy());	
		tar.setUpdateBy(src.getUpdateBy());	
		tar.setVersion(src.getVersion());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static AdCodeVO toVO(AdCodeDTO src) {
		AdCodeVO tar = new AdCodeVO();
		tar.setId(src.getId());
		tar.setPageType(src.getPageType());	
		tar.setCode(src.getCode());	
		tar.setName(src.getName());	
		tar.setParentCode(src.getParentCode());	
		tar.setLevel(src.getLevel());	
		tar.setSort(src.getSort());	
		tar.setPlatform(src.getPlatform());	
		tar.setWidth(src.getWidth());	
		tar.setHeight(src.getHeight());	
		tar.setSourceSizeLimit(src.getSourceSizeLimit());	
		tar.setCreateBy(src.getCreateBy());	
		tar.setUpdateBy(src.getUpdateBy());	
		tar.setVersion(src.getVersion());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static List<AdCodeDTO> toDTOs(List<AdCodeVO> srcs) {
		if (srcs == null)
			return null;
		List<AdCodeDTO> list = new ArrayList<AdCodeDTO>();
		for (AdCodeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AdCodeVO> toVO(List<AdCodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<AdCodeVO> list = new ArrayList<AdCodeVO>();
		for (AdCodeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static AdCodeDTO toDTO(AdCodePO src) {
		if (src == null)
			return null;	
		AdCodeDTO tar = new AdCodeDTO();
		tar.setId(src.getId());
		tar.setPageType(src.getPageType());
		tar.setCode(src.getCode());
		tar.setName(src.getName());
		tar.setParentCode(src.getParentCode());
		tar.setLevel(src.getLevel());
		tar.setSort(src.getSort());
		tar.setPlatform(src.getPlatform());
		tar.setWidth(src.getWidth());
		tar.setHeight(src.getHeight());
		tar.setSourceSizeLimit(src.getSourceSizeLimit());
		tar.setCreateBy(src.getCreateBy());
		tar.setUpdateBy(src.getUpdateBy());
		tar.setVersion(src.getVersion());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static AdCodePO toPO(AdCodeDTO src) {
		if (src == null)
			return null;	
		AdCodePO tar = new AdCodePO();
		tar.setId(src.getId());
		tar.setPageType(src.getPageType());
		tar.setCode(src.getCode());
		tar.setName(src.getName());
		tar.setParentCode(src.getParentCode());
		tar.setLevel(src.getLevel());
		tar.setSort(src.getSort());
		tar.setPlatform(src.getPlatform());
		tar.setWidth(src.getWidth());
		tar.setHeight(src.getHeight());
		tar.setSourceSizeLimit(src.getSourceSizeLimit());
		tar.setCreateBy(src.getCreateBy());
		tar.setUpdateBy(src.getUpdateBy());
		tar.setVersion(src.getVersion());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<AdCodeDTO> toDTO(List<AdCodePO> srcs) {
		if (srcs == null)
			return null;
		List<AdCodeDTO> list = new ArrayList<AdCodeDTO>();
		for (AdCodePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<AdCodePO> toPO(List<AdCodeDTO> srcs) {
		if (srcs == null)
			return null;
		List<AdCodePO> list = new ArrayList<AdCodePO>();
		for (AdCodeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	