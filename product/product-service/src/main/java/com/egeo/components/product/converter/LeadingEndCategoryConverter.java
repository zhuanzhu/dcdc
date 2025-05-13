package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.LeadingEndCategoryDTO;
import com.egeo.components.product.po.LeadingEndCategoryPO;
import com.egeo.components.product.vo.LeadingEndCategoryVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-26 09:37:06
 */
public class LeadingEndCategoryConverter {

	
	public static LeadingEndCategoryDTO toDTO(LeadingEndCategoryVO src) {
		if (src == null)
		return null;	
		LeadingEndCategoryDTO tar = new LeadingEndCategoryDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setDescription(src.getDescription());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static LeadingEndCategoryVO toVO(LeadingEndCategoryDTO src) {
		if (src == null)
		return null;	
		LeadingEndCategoryVO tar = new LeadingEndCategoryVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setDescription(src.getDescription());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<LeadingEndCategoryDTO> toDTOs(List<LeadingEndCategoryVO> srcs) {
		if (srcs == null)
			return null;
		List<LeadingEndCategoryDTO> list = new ArrayList<LeadingEndCategoryDTO>();
		for (LeadingEndCategoryVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LeadingEndCategoryVO> toVO(List<LeadingEndCategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<LeadingEndCategoryVO> list = new ArrayList<LeadingEndCategoryVO>();
		for (LeadingEndCategoryDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static LeadingEndCategoryDTO toDTO(LeadingEndCategoryPO src) {
		if (src == null)
		return null;	
		LeadingEndCategoryDTO tar = new LeadingEndCategoryDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static LeadingEndCategoryPO toPO(LeadingEndCategoryDTO src) {
		if (src == null)
		return null;	
		LeadingEndCategoryPO tar = new LeadingEndCategoryPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setDescription(src.getDescription());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<LeadingEndCategoryDTO> toDTO(List<LeadingEndCategoryPO> srcs) {
		if (srcs == null)
			return null;
		List<LeadingEndCategoryDTO> list = new ArrayList<LeadingEndCategoryDTO>();
		for (LeadingEndCategoryPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<LeadingEndCategoryPO> toPO(List<LeadingEndCategoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<LeadingEndCategoryPO> list = new ArrayList<LeadingEndCategoryPO>();
		for (LeadingEndCategoryDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	