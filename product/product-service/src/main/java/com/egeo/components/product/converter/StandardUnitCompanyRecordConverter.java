package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitCompanyRecordDTO;
import com.egeo.components.product.po.StandardUnitCompanyRecordPO;
import com.egeo.components.product.vo.StandardUnitCompanyRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 11:18:56
 */
public class StandardUnitCompanyRecordConverter {
	
	public static StandardUnitCompanyRecordDTO toDTO(StandardUnitCompanyRecordPO src) {
		if (src == null)
		return null;	
		StandardUnitCompanyRecordDTO tar = new StandardUnitCompanyRecordDTO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static StandardUnitCompanyRecordPO toPO(StandardUnitCompanyRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitCompanyRecordPO tar = new StandardUnitCompanyRecordPO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static List<StandardUnitCompanyRecordDTO> toDTO(List<StandardUnitCompanyRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCompanyRecordDTO> list = new ArrayList<StandardUnitCompanyRecordDTO>();
		for (StandardUnitCompanyRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCompanyRecordPO> toPO(List<StandardUnitCompanyRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCompanyRecordPO> list = new ArrayList<StandardUnitCompanyRecordPO>();
		for (StandardUnitCompanyRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitCompanyRecordDTO toDTO(StandardUnitCompanyRecordVO src) {
		if (src == null)
		return null;	
		StandardUnitCompanyRecordDTO tar = new StandardUnitCompanyRecordDTO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static StandardUnitCompanyRecordVO toVO(StandardUnitCompanyRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitCompanyRecordVO tar = new StandardUnitCompanyRecordVO();
		tar.setId(src.getId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static List<StandardUnitCompanyRecordDTO> toDTOs(List<StandardUnitCompanyRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCompanyRecordDTO> list = new ArrayList<StandardUnitCompanyRecordDTO>();
		for (StandardUnitCompanyRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCompanyRecordVO> toVO(List<StandardUnitCompanyRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCompanyRecordVO> list = new ArrayList<StandardUnitCompanyRecordVO>();
		for (StandardUnitCompanyRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	