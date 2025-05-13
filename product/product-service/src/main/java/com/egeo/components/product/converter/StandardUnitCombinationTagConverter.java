package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitCombinationTagDTO;
import com.egeo.components.product.po.StandardUnitCombinationTagPO;
import com.egeo.components.product.vo.StandardUnitCombinationTagVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-11 20:08:11
 */
public class StandardUnitCombinationTagConverter {
	
	public static StandardUnitCombinationTagDTO toDTO(StandardUnitCombinationTagPO src) {
		if (src == null)
		return null;	
		StandardUnitCombinationTagDTO tar = new StandardUnitCombinationTagDTO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setTagId(src.getTagId());
		return tar;
	}

	public static StandardUnitCombinationTagPO toPO(StandardUnitCombinationTagDTO src) {
		if (src == null)
		return null;	
		StandardUnitCombinationTagPO tar = new StandardUnitCombinationTagPO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());
		tar.setTagId(src.getTagId());
		return tar;
	}

	public static List<StandardUnitCombinationTagDTO> toDTO(List<StandardUnitCombinationTagPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationTagDTO> list = new ArrayList<StandardUnitCombinationTagDTO>();
		for (StandardUnitCombinationTagPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCombinationTagPO> toPO(List<StandardUnitCombinationTagDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationTagPO> list = new ArrayList<StandardUnitCombinationTagPO>();
		for (StandardUnitCombinationTagDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitCombinationTagDTO toDTO(StandardUnitCombinationTagVO src) {
		if (src == null)
		return null;	
		StandardUnitCombinationTagDTO tar = new StandardUnitCombinationTagDTO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());	
		tar.setTagId(src.getTagId());	
		return tar;
	}

	public static StandardUnitCombinationTagVO toVO(StandardUnitCombinationTagDTO src) {
		if (src == null)
		return null;	
		StandardUnitCombinationTagVO tar = new StandardUnitCombinationTagVO();
		tar.setId(src.getId());
		tar.setStandardUnitCombinationId(src.getStandardUnitCombinationId());	
		tar.setTagId(src.getTagId());	
		return tar;
	}

	public static List<StandardUnitCombinationTagDTO> toDTOs(List<StandardUnitCombinationTagVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationTagDTO> list = new ArrayList<StandardUnitCombinationTagDTO>();
		for (StandardUnitCombinationTagVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCombinationTagVO> toVO(List<StandardUnitCombinationTagDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCombinationTagVO> list = new ArrayList<StandardUnitCombinationTagVO>();
		for (StandardUnitCombinationTagDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	