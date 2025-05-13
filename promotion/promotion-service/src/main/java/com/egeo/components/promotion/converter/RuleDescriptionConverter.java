package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.RuleDescriptionDTO;
import com.egeo.components.promotion.po.RuleDescriptionPO;
import com.egeo.components.promotion.vo.RuleDescriptionVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author feng
 * @date 2018-12-14 10:57:18
 */
public class RuleDescriptionConverter {

	
	public static RuleDescriptionDTO toDTO(RuleDescriptionVO src) {
		if (src == null)
		return null;	
		RuleDescriptionDTO tar = new RuleDescriptionDTO();
		tar.setId(src.getId());
		tar.setDescription(src.getDescription());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static RuleDescriptionVO toVO(RuleDescriptionDTO src) {
		if (src == null)
		return null;	
		RuleDescriptionVO tar = new RuleDescriptionVO();
		tar.setId(src.getId());
		tar.setDescription(src.getDescription());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<RuleDescriptionDTO> toDTOs(List<RuleDescriptionVO> srcs) {
		if (srcs == null)
			return null;
		List<RuleDescriptionDTO> list = new ArrayList<RuleDescriptionDTO>();
		for (RuleDescriptionVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<RuleDescriptionVO> toVO(List<RuleDescriptionDTO> srcs) {
		if (srcs == null)
			return null;
		List<RuleDescriptionVO> list = new ArrayList<RuleDescriptionVO>();
		for (RuleDescriptionDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static RuleDescriptionDTO toDTO(RuleDescriptionPO src) {
		if (src == null)
		return null;	
		RuleDescriptionDTO tar = new RuleDescriptionDTO();
		tar.setId(src.getId());
		tar.setDescription(src.getDescription());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static RuleDescriptionPO toPO(RuleDescriptionDTO src) {
		if (src == null)
		return null;	
		RuleDescriptionPO tar = new RuleDescriptionPO();
		tar.setId(src.getId());
		tar.setDescription(src.getDescription());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<RuleDescriptionDTO> toDTO(List<RuleDescriptionPO> srcs) {
		if (srcs == null)
			return null;
		List<RuleDescriptionDTO> list = new ArrayList<RuleDescriptionDTO>();
		for (RuleDescriptionPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<RuleDescriptionPO> toPO(List<RuleDescriptionDTO> srcs) {
		if (srcs == null)
			return null;
		List<RuleDescriptionPO> list = new ArrayList<RuleDescriptionPO>();
		for (RuleDescriptionDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	