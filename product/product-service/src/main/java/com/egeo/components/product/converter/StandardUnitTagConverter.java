package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitTagDTO;
import com.egeo.components.product.po.StandardUnitTagPO;
import com.egeo.components.product.vo.StandardUnitTagVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-11 15:04:03
 */
public class StandardUnitTagConverter {
	
	public static StandardUnitTagDTO toDTO(StandardUnitTagPO src) {
		if (src == null)
		return null;	
		StandardUnitTagDTO tar = new StandardUnitTagDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setTagId(src.getTagId());
		tar.setTagIds(src.getTagIds());
		return tar;
	}

	public static StandardUnitTagPO toPO(StandardUnitTagDTO src) {
		if (src == null)
		return null;	
		StandardUnitTagPO tar = new StandardUnitTagPO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setTagId(src.getTagId());
		tar.setTagIds(src.getTagIds());
		return tar;
	}

	public static List<StandardUnitTagDTO> toDTO(List<StandardUnitTagPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitTagDTO> list = new ArrayList<StandardUnitTagDTO>();
		for (StandardUnitTagPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitTagPO> toPO(List<StandardUnitTagDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitTagPO> list = new ArrayList<StandardUnitTagPO>();
		for (StandardUnitTagDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitTagDTO toDTO(StandardUnitTagVO src) {
		if (src == null)
		return null;	
		StandardUnitTagDTO tar = new StandardUnitTagDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setTagId(src.getTagId());	
		return tar;
	}

	public static StandardUnitTagVO toVO(StandardUnitTagDTO src) {
		if (src == null)
		return null;	
		StandardUnitTagVO tar = new StandardUnitTagVO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setTagId(src.getTagId());	
		return tar;
	}

	public static List<StandardUnitTagDTO> toDTOs(List<StandardUnitTagVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitTagDTO> list = new ArrayList<StandardUnitTagDTO>();
		for (StandardUnitTagVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitTagVO> toVO(List<StandardUnitTagDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitTagVO> list = new ArrayList<StandardUnitTagVO>();
		for (StandardUnitTagDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	