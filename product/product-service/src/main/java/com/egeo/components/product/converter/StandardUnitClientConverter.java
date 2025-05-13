package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitClientDTO;
import com.egeo.components.product.po.StandardUnitClientPO;
import com.egeo.components.product.vo.StandardUnitClientVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-07 11:18:55
 */
public class StandardUnitClientConverter {
	
	public static StandardUnitClientDTO toDTO(StandardUnitClientPO src) {
		if (src == null)
		return null;	
		StandardUnitClientDTO tar = new StandardUnitClientDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setClientId(src.getClientId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static StandardUnitClientPO toPO(StandardUnitClientDTO src) {
		if (src == null)
		return null;	
		StandardUnitClientPO tar = new StandardUnitClientPO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setClientId(src.getClientId());
		tar.setCreateTime(src.getCreateTime());
		return tar;
	}

	public static List<StandardUnitClientDTO> toDTO(List<StandardUnitClientPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitClientDTO> list = new ArrayList<StandardUnitClientDTO>();
		for (StandardUnitClientPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitClientPO> toPO(List<StandardUnitClientDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitClientPO> list = new ArrayList<StandardUnitClientPO>();
		for (StandardUnitClientDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitClientDTO toDTO(StandardUnitClientVO src) {
		if (src == null)
		return null;	
		StandardUnitClientDTO tar = new StandardUnitClientDTO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setClientId(src.getClientId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static StandardUnitClientVO toVO(StandardUnitClientDTO src) {
		if (src == null)
		return null;	
		StandardUnitClientVO tar = new StandardUnitClientVO();
		tar.setId(src.getId());
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setClientId(src.getClientId());	
		tar.setCreateTime(src.getCreateTime());	
		return tar;
	}

	public static List<StandardUnitClientDTO> toDTOs(List<StandardUnitClientVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitClientDTO> list = new ArrayList<StandardUnitClientDTO>();
		for (StandardUnitClientVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitClientVO> toVO(List<StandardUnitClientDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitClientVO> list = new ArrayList<StandardUnitClientVO>();
		for (StandardUnitClientDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	