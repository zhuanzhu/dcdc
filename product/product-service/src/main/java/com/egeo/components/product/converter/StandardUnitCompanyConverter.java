package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitCompanyDTO;
import com.egeo.components.product.po.StandardUnitCompanyPO;
import com.egeo.components.product.vo.StandardUnitCompanyVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 11:18:55
 */
public class StandardUnitCompanyConverter {
	
	public static StandardUnitCompanyDTO toDTO(StandardUnitCompanyPO src) {
		if (src == null)
		return null;	
		StandardUnitCompanyDTO tar = new StandardUnitCompanyDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static StandardUnitCompanyPO toPO(StandardUnitCompanyDTO src) {
		if (src == null)
		return null;	
		StandardUnitCompanyPO tar = new StandardUnitCompanyPO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setCompanyId(src.getCompanyId());
		tar.setCreateTime(src.getCreateTime());
		tar.setCompanyType(src.getCompanyType());
		tar.setStandardUnitIds(src.getStandardUnitIds());
		return tar;
	}

	public static List<StandardUnitCompanyDTO> toDTO(List<StandardUnitCompanyPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCompanyDTO> list = new ArrayList<StandardUnitCompanyDTO>();
		for (StandardUnitCompanyPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCompanyPO> toPO(List<StandardUnitCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCompanyPO> list = new ArrayList<StandardUnitCompanyPO>();
		for (StandardUnitCompanyDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitCompanyDTO toDTO(StandardUnitCompanyVO src) {
		if (src == null)
		return null;	
		StandardUnitCompanyDTO tar = new StandardUnitCompanyDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static StandardUnitCompanyVO toVO(StandardUnitCompanyDTO src) {
		if (src == null)
		return null;	
		StandardUnitCompanyVO tar = new StandardUnitCompanyVO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setCompanyId(src.getCompanyId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setCompanyType(src.getCompanyType());
		return tar;
	}

	public static List<StandardUnitCompanyDTO> toDTOs(List<StandardUnitCompanyVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCompanyDTO> list = new ArrayList<StandardUnitCompanyDTO>();
		for (StandardUnitCompanyVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitCompanyVO> toVO(List<StandardUnitCompanyDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitCompanyVO> list = new ArrayList<StandardUnitCompanyVO>();
		for (StandardUnitCompanyDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	