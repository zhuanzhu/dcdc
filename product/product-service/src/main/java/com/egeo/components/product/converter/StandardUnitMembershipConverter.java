package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitMembershipDTO;
import com.egeo.components.product.po.StandardUnitMembershipPO;
import com.egeo.components.product.vo.StandardUnitMembershipVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-11 14:59:31
 */
public class StandardUnitMembershipConverter {
	
	public static StandardUnitMembershipDTO toDTO(StandardUnitMembershipPO src) {
		if (src == null)
		return null;	
		StandardUnitMembershipDTO tar = new StandardUnitMembershipDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StandardUnitMembershipPO toPO(StandardUnitMembershipDTO src) {
		if (src == null)
		return null;	
		StandardUnitMembershipPO tar = new StandardUnitMembershipPO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StandardUnitMembershipDTO> toDTO(List<StandardUnitMembershipPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitMembershipDTO> list = new ArrayList<StandardUnitMembershipDTO>();
		for (StandardUnitMembershipPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitMembershipPO> toPO(List<StandardUnitMembershipDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitMembershipPO> list = new ArrayList<StandardUnitMembershipPO>();
		for (StandardUnitMembershipDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitMembershipDTO toDTO(StandardUnitMembershipVO src) {
		if (src == null)
		return null;	
		StandardUnitMembershipDTO tar = new StandardUnitMembershipDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardUnitMembershipVO toVO(StandardUnitMembershipDTO src) {
		if (src == null)
		return null;	
		StandardUnitMembershipVO tar = new StandardUnitMembershipVO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardUnitMembershipDTO> toDTOs(List<StandardUnitMembershipVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitMembershipDTO> list = new ArrayList<StandardUnitMembershipDTO>();
		for (StandardUnitMembershipVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitMembershipVO> toVO(List<StandardUnitMembershipDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitMembershipVO> list = new ArrayList<StandardUnitMembershipVO>();
		for (StandardUnitMembershipDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	