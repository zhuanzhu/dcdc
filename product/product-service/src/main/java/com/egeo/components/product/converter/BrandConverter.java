package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.BrandDTO;
import com.egeo.components.product.po.BrandPO;
import com.egeo.components.product.vo.BrandVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author xiaping
 * @date 2017-08-14 13:46:25
 */
public class BrandConverter {

	public static BrandDTO toDTO(BrandVO src) {
		BrandDTO tar = new BrandDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setChineseName(src.getChineseName());	
		tar.setEnglishName(src.getEnglishName());	
		tar.setAlias(src.getAlias());	
		tar.setLogUrl(src.getLogUrl());	
		tar.setOwnedcompanyChineseName(src.getOwnedcompanyChineseName());	
		tar.setOwnedcompanyEnglishName(src.getOwnedcompanyEnglishName());	
		tar.setIntroduction(src.getIntroduction());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static BrandVO toVO(BrandDTO src) {
		BrandVO tar = new BrandVO();
		tar.setId(src.getId());
		tar.setName(src.getName());		
		tar.setChineseName(src.getChineseName());		
		tar.setEnglishName(src.getEnglishName());		
		tar.setAlias(src.getAlias());		
		tar.setLogUrl(src.getLogUrl());		
		tar.setOwnedcompanyChineseName(src.getOwnedcompanyChineseName());		
		tar.setOwnedcompanyEnglishName(src.getOwnedcompanyEnglishName());		
		tar.setIntroduction(src.getIntroduction());		
		tar.setPlatformId(src.getPlatformId());		
		tar.setCreateTime(src.getCreateTime());		
		tar.setUpdateTime(src.getUpdateTime());		
		return tar;
	}

	public static List<BrandDTO> toDTOs(List<BrandVO> srcs) {
		if (srcs == null)
			return null;
		List<BrandDTO> list = new ArrayList<BrandDTO>();
		for (BrandVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BrandVO> toVO(List<BrandDTO> srcs) {
		if (srcs == null)
			return null;
		List<BrandVO> list = new ArrayList<BrandVO>();
		for (BrandDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static BrandDTO toDTO(BrandPO src) {
		BrandDTO tar = new BrandDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setChineseName(src.getChineseName());
		tar.setEnglishName(src.getEnglishName());
		tar.setAlias(src.getAlias());
		tar.setLogUrl(src.getLogUrl());
		tar.setOwnedcompanyChineseName(src.getOwnedcompanyChineseName());
		tar.setOwnedcompanyEnglishName(src.getOwnedcompanyEnglishName());
		tar.setIntroduction(src.getIntroduction());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static BrandPO toPO(BrandDTO src) {
		BrandPO tar = new BrandPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setChineseName(src.getChineseName());
		tar.setEnglishName(src.getEnglishName());
		tar.setAlias(src.getAlias());
		tar.setLogUrl(src.getLogUrl());
		tar.setOwnedcompanyChineseName(src.getOwnedcompanyChineseName());
		tar.setOwnedcompanyEnglishName(src.getOwnedcompanyEnglishName());
		tar.setIntroduction(src.getIntroduction());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<BrandDTO> toDTO(List<BrandPO> srcs) {
		if (srcs == null)
			return null;
		List<BrandDTO> list = new ArrayList<BrandDTO>();
		for (BrandPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<BrandPO> toPO(List<BrandDTO> srcs) {
		if (srcs == null)
			return null;
		List<BrandPO> list = new ArrayList<BrandPO>();
		for (BrandDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	