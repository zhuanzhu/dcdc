package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.SurSerachKeywordDTO;
import com.egeo.components.product.po.SurSerachKeywordPO;
import com.egeo.components.product.vo.SurSerachKeywordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-06-29 18:16:48
 */
public class SurSerachKeywordConverter {

	
	public static SurSerachKeywordDTO toDTO(SurSerachKeywordVO src) {
		if (src == null)
		return null;	
		SurSerachKeywordDTO tar = new SurSerachKeywordDTO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setKeyword(src.getKeyword());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static SurSerachKeywordVO toVO(SurSerachKeywordDTO src) {
		if (src == null)
		return null;	
		SurSerachKeywordVO tar = new SurSerachKeywordVO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setKeyword(src.getKeyword());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<SurSerachKeywordDTO> toDTOs(List<SurSerachKeywordVO> srcs) {
		if (srcs == null)
			return null;
		List<SurSerachKeywordDTO> list = new ArrayList<SurSerachKeywordDTO>();
		for (SurSerachKeywordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SurSerachKeywordVO> toVO(List<SurSerachKeywordDTO> srcs) {
		if (srcs == null)
			return null;
		List<SurSerachKeywordVO> list = new ArrayList<SurSerachKeywordVO>();
		for (SurSerachKeywordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static SurSerachKeywordDTO toDTO(SurSerachKeywordPO src) {
		if (src == null)
		return null;	
		SurSerachKeywordDTO tar = new SurSerachKeywordDTO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setKeyword(src.getKeyword());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static SurSerachKeywordPO toPO(SurSerachKeywordDTO src) {
		if (src == null)
		return null;	
		SurSerachKeywordPO tar = new SurSerachKeywordPO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setKeyword(src.getKeyword());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<SurSerachKeywordDTO> toDTO(List<SurSerachKeywordPO> srcs) {
		if (srcs == null)
			return null;
		List<SurSerachKeywordDTO> list = new ArrayList<SurSerachKeywordDTO>();
		for (SurSerachKeywordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<SurSerachKeywordPO> toPO(List<SurSerachKeywordDTO> srcs) {
		if (srcs == null)
			return null;
		List<SurSerachKeywordPO> list = new ArrayList<SurSerachKeywordPO>();
		for (SurSerachKeywordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	