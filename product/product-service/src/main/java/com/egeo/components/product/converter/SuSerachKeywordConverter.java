package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SuSerachKeywordDTO;
import com.egeo.components.product.po.SuSerachKeywordPO;
import com.egeo.components.product.vo.SuSerachKeywordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-06-29 18:16:48
 */
public class SuSerachKeywordConverter {

	
	public static SuSerachKeywordDTO toDTO(SuSerachKeywordVO src) {
		if (src == null)
		return null;	
		SuSerachKeywordDTO tar = new SuSerachKeywordDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setKeyword(src.getKeyword());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static SuSerachKeywordVO toVO(SuSerachKeywordDTO src) {
		if (src == null)
		return null;	
		SuSerachKeywordVO tar = new SuSerachKeywordVO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setKeyword(src.getKeyword());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<SuSerachKeywordDTO> toDTOs(List<SuSerachKeywordVO> srcs) {
		if (srcs == null)
			return null;
		List<SuSerachKeywordDTO> list = new ArrayList<SuSerachKeywordDTO>();
		for (SuSerachKeywordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SuSerachKeywordVO> toVO(List<SuSerachKeywordDTO> srcs) {
		if (srcs == null)
			return null;
		List<SuSerachKeywordVO> list = new ArrayList<SuSerachKeywordVO>();
		for (SuSerachKeywordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SuSerachKeywordDTO toDTO(SuSerachKeywordPO src) {
		if (src == null)
		return null;	
		SuSerachKeywordDTO tar = new SuSerachKeywordDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setKeyword(src.getKeyword());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SuSerachKeywordPO toPO(SuSerachKeywordDTO src) {
		if (src == null)
		return null;	
		SuSerachKeywordPO tar = new SuSerachKeywordPO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setKeyword(src.getKeyword());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SuSerachKeywordDTO> toDTO(List<SuSerachKeywordPO> srcs) {
		if (srcs == null)
			return null;
		List<SuSerachKeywordDTO> list = new ArrayList<SuSerachKeywordDTO>();
		for (SuSerachKeywordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SuSerachKeywordPO> toPO(List<SuSerachKeywordDTO> srcs) {
		if (srcs == null)
			return null;
		List<SuSerachKeywordPO> list = new ArrayList<SuSerachKeywordPO>();
		for (SuSerachKeywordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	