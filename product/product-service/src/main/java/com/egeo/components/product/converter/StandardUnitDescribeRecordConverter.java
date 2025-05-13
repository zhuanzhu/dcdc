package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitDescribeRecordDTO;
import com.egeo.components.product.po.StandardUnitDescribeRecordPO;
import com.egeo.components.product.vo.StandardUnitDescribeRecordVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 11:18:56
 */
public class StandardUnitDescribeRecordConverter {
	
	public static StandardUnitDescribeRecordDTO toDTO(StandardUnitDescribeRecordPO src) {
		if (src == null)
		return null;	
		StandardUnitDescribeRecordDTO tar = new StandardUnitDescribeRecordDTO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		return tar;
	}

	public static StandardUnitDescribeRecordPO toPO(StandardUnitDescribeRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitDescribeRecordPO tar = new StandardUnitDescribeRecordPO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		return tar;
	}

	public static List<StandardUnitDescribeRecordDTO> toDTO(List<StandardUnitDescribeRecordPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitDescribeRecordDTO> list = new ArrayList<StandardUnitDescribeRecordDTO>();
		for (StandardUnitDescribeRecordPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitDescribeRecordPO> toPO(List<StandardUnitDescribeRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitDescribeRecordPO> list = new ArrayList<StandardUnitDescribeRecordPO>();
		for (StandardUnitDescribeRecordDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitDescribeRecordDTO toDTO(StandardUnitDescribeRecordVO src) {
		if (src == null)
		return null;	
		StandardUnitDescribeRecordDTO tar = new StandardUnitDescribeRecordDTO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		return tar;
	}

	public static StandardUnitDescribeRecordVO toVO(StandardUnitDescribeRecordDTO src) {
		if (src == null)
		return null;	
		StandardUnitDescribeRecordVO tar = new StandardUnitDescribeRecordVO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		return tar;
	}

	public static List<StandardUnitDescribeRecordDTO> toDTOs(List<StandardUnitDescribeRecordVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitDescribeRecordDTO> list = new ArrayList<StandardUnitDescribeRecordDTO>();
		for (StandardUnitDescribeRecordVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitDescribeRecordVO> toVO(List<StandardUnitDescribeRecordDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitDescribeRecordVO> list = new ArrayList<StandardUnitDescribeRecordVO>();
		for (StandardUnitDescribeRecordDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	