package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitClientRecordDTO;
import com.egeo.components.product.po.StandardUnitClientRecordPO;
import com.egeo.components.product.vo.StandardUnitClientRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 11:18:56
 */
public class StandardUnitClientRecordConverter {
	
	public static StandardUnitClientRecordDTO toDTO(StandardUnitClientRecordPO src) {
		if (src == null)
		return null;	
		StandardUnitClientRecordDTO tar = new StandardUnitClientRecordDTO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setClientId(src.getClientId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static StandardUnitClientRecordPO toPO(StandardUnitClientRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitClientRecordPO tar = new StandardUnitClientRecordPO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setClientId(src.getClientId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<StandardUnitClientRecordDTO> toDTO(List<StandardUnitClientRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitClientRecordDTO> list = new ArrayList<StandardUnitClientRecordDTO>();
		for (StandardUnitClientRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitClientRecordPO> toPO(List<StandardUnitClientRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitClientRecordPO> list = new ArrayList<StandardUnitClientRecordPO>();
		for (StandardUnitClientRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitClientRecordDTO toDTO(StandardUnitClientRecordVO src) {
		if (src == null)
		return null;	
		StandardUnitClientRecordDTO tar = new StandardUnitClientRecordDTO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setClientId(src.getClientId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static StandardUnitClientRecordVO toVO(StandardUnitClientRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitClientRecordVO tar = new StandardUnitClientRecordVO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setClientId(src.getClientId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static List<StandardUnitClientRecordDTO> toDTOs(List<StandardUnitClientRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitClientRecordDTO> list = new ArrayList<StandardUnitClientRecordDTO>();
		for (StandardUnitClientRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitClientRecordVO> toVO(List<StandardUnitClientRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitClientRecordVO> list = new ArrayList<StandardUnitClientRecordVO>();
		for (StandardUnitClientRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	