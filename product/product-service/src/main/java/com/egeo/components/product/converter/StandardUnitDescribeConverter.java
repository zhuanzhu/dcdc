package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitDescribeDTO;
import com.egeo.components.product.po.StandardUnitDescribePO;
import com.egeo.components.product.vo.StandardUnitDescribeVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 11:18:56
 */
public class StandardUnitDescribeConverter {
	
	public static StandardUnitDescribeDTO toDTO(StandardUnitDescribePO src) {
		if (src == null)
		return null;	
		StandardUnitDescribeDTO tar = new StandardUnitDescribeDTO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStandardUnitId(src.getStandardUnitId());
		return tar;
	}

	public static StandardUnitDescribePO toPO(StandardUnitDescribeDTO src) {
		if (src == null)
		return null;	
		StandardUnitDescribePO tar = new StandardUnitDescribePO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setStandardUnitId(src.getStandardUnitId());
		return tar;
	}

	public static List<StandardUnitDescribeDTO> toDTO(List<StandardUnitDescribePO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitDescribeDTO> list = new ArrayList<StandardUnitDescribeDTO>();
		for (StandardUnitDescribePO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitDescribePO> toPO(List<StandardUnitDescribeDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitDescribePO> list = new ArrayList<StandardUnitDescribePO>();
		for (StandardUnitDescribeDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitDescribeDTO toDTO(StandardUnitDescribeVO src) {
		if (src == null)
		return null;	
		StandardUnitDescribeDTO tar = new StandardUnitDescribeDTO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		return tar;
	}

	public static StandardUnitDescribeVO toVO(StandardUnitDescribeDTO src) {
		if (src == null)
		return null;	
		StandardUnitDescribeVO tar = new StandardUnitDescribeVO();
		tar.setId(src.getId());
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		return tar;
	}

	public static List<StandardUnitDescribeDTO> toDTOs(List<StandardUnitDescribeVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitDescribeDTO> list = new ArrayList<StandardUnitDescribeDTO>();
		for (StandardUnitDescribeVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitDescribeVO> toVO(List<StandardUnitDescribeDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitDescribeVO> list = new ArrayList<StandardUnitDescribeVO>();
		for (StandardUnitDescribeDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	