package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardProductUnitDescriptionDTO;
import com.egeo.components.product.po.StandardProductUnitDescriptionPO;
import com.egeo.components.product.vo.StandardProductUnitDescriptionVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-05 18:51:02
 */
public class StandardProductUnitDescriptionConverter {
	
	public static StandardProductUnitDescriptionDTO toDTO(StandardProductUnitDescriptionVO src) {
		if (src == null)
		return null;	
		StandardProductUnitDescriptionDTO tar = new StandardProductUnitDescriptionDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardProductUnitDescriptionVO toVO(StandardProductUnitDescriptionDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitDescriptionVO tar = new StandardProductUnitDescriptionVO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());	
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardProductUnitDescriptionDTO> toDTOs(List<StandardProductUnitDescriptionVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitDescriptionDTO> list = new ArrayList<StandardProductUnitDescriptionDTO>();
		for (StandardProductUnitDescriptionVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitDescriptionVO> toVO(List<StandardProductUnitDescriptionDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitDescriptionVO> list = new ArrayList<StandardProductUnitDescriptionVO>();
		for (StandardProductUnitDescriptionDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static StandardProductUnitDescriptionDTO toDTO(StandardProductUnitDescriptionPO src) {
		if (src == null)
		return null;	
		StandardProductUnitDescriptionDTO tar = new StandardProductUnitDescriptionDTO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StandardProductUnitDescriptionPO toPO(StandardProductUnitDescriptionDTO src) {
		if (src == null)
		return null;	
		StandardProductUnitDescriptionPO tar = new StandardProductUnitDescriptionPO();
		tar.setId(src.getId());
		tar.setStandardProductUnitId(src.getStandardProductUnitId());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StandardProductUnitDescriptionDTO> toDTO(List<StandardProductUnitDescriptionPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitDescriptionDTO> list = new ArrayList<StandardProductUnitDescriptionDTO>();
		for (StandardProductUnitDescriptionPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardProductUnitDescriptionPO> toPO(List<StandardProductUnitDescriptionDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardProductUnitDescriptionPO> list = new ArrayList<StandardProductUnitDescriptionPO>();
		for (StandardProductUnitDescriptionDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	