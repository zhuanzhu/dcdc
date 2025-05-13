package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitTagRecordDTO;
import com.egeo.components.product.po.StandardUnitTagRecordPO;
import com.egeo.components.product.vo.StandardUnitTagRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-05-11 15:04:04
 */
public class StandardUnitTagRecordConverter {
	
	public static StandardUnitTagRecordDTO toDTO(StandardUnitTagRecordPO src) {
		if (src == null)
		return null;	
		StandardUnitTagRecordDTO tar = new StandardUnitTagRecordDTO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setTagId(src.getTagId());
		return tar;
	}

	public static StandardUnitTagRecordPO toPO(StandardUnitTagRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitTagRecordPO tar = new StandardUnitTagRecordPO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setTagId(src.getTagId());
		return tar;
	}

	public static List<StandardUnitTagRecordDTO> toDTO(List<StandardUnitTagRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitTagRecordDTO> list = new ArrayList<StandardUnitTagRecordDTO>();
		for (StandardUnitTagRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitTagRecordPO> toPO(List<StandardUnitTagRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitTagRecordPO> list = new ArrayList<StandardUnitTagRecordPO>();
		for (StandardUnitTagRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitTagRecordDTO toDTO(StandardUnitTagRecordVO src) {
		if (src == null)
		return null;	
		StandardUnitTagRecordDTO tar = new StandardUnitTagRecordDTO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setTagId(src.getTagId());	
		return tar;
	}

	public static StandardUnitTagRecordVO toVO(StandardUnitTagRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitTagRecordVO tar = new StandardUnitTagRecordVO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setTagId(src.getTagId());	
		return tar;
	}

	public static List<StandardUnitTagRecordDTO> toDTOs(List<StandardUnitTagRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitTagRecordDTO> list = new ArrayList<StandardUnitTagRecordDTO>();
		for (StandardUnitTagRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitTagRecordVO> toVO(List<StandardUnitTagRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitTagRecordVO> list = new ArrayList<StandardUnitTagRecordVO>();
		for (StandardUnitTagRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	