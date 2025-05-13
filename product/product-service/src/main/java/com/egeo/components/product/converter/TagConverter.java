package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.TagDTO;
import com.egeo.components.product.po.TagPO;
import com.egeo.components.product.vo.TagVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-11 15:04:03
 */
public class TagConverter {

	public static TagDTO toDTO(TagVO src) {
		if (src == null)
		return null;	
		TagDTO tar = new TagDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setType(src.getType());	
		tar.setIsValid(src.getIsValid());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());
		tar.setTypeList(src.getTypeList());
		return tar;
	}

	public static TagVO toVO(TagDTO src) {
		if (src == null)
		return null;	
		TagVO tar = new TagVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setType(src.getType());	
		tar.setIsValid(src.getIsValid());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());
		tar.setTypeList(src.getTypeList());
		return tar;
	}

	public static List<TagDTO> toDTOs(List<TagVO> srcs) {
		if (srcs == null)
			return null;
		List<TagDTO> list = new ArrayList<TagDTO>();
		for (TagVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<TagVO> toVO(List<TagDTO> srcs) {
		if (srcs == null)
			return null;
		List<TagVO> list = new ArrayList<TagVO>();
		for (TagDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static TagDTO toDTO(TagPO src) {
		if (src == null)
		return null;	
		TagDTO tar = new TagDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setIsValid(src.getIsValid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static TagPO toPO(TagDTO src) {
		if (src == null)
		return null;	
		TagPO tar = new TagPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setType(src.getType());
		tar.setIsValid(src.getIsValid());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<TagDTO> toDTO(List<TagPO> srcs) {
		if (srcs == null)
			return null;
		List<TagDTO> list = new ArrayList<TagDTO>();
		for (TagPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<TagPO> toPO(List<TagDTO> srcs) {
		if (srcs == null)
			return null;
		List<TagPO> list = new ArrayList<TagPO>();
		for (TagDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	