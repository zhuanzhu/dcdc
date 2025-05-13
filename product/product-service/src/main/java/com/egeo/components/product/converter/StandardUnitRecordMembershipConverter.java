package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.StandardUnitRecordMembershipDTO;
import com.egeo.components.product.po.StandardUnitRecordMembershipPO;
import com.egeo.components.product.vo.StandardUnitRecordMembershipVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-09-11 14:59:32
 */
public class StandardUnitRecordMembershipConverter {
	
	public static StandardUnitRecordMembershipDTO toDTO(StandardUnitRecordMembershipPO src) {
		if (src == null)
		return null;	
		StandardUnitRecordMembershipDTO tar = new StandardUnitRecordMembershipDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static StandardUnitRecordMembershipPO toPO(StandardUnitRecordMembershipDTO src) {
		if (src == null)
		return null;	
		StandardUnitRecordMembershipPO tar = new StandardUnitRecordMembershipPO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<StandardUnitRecordMembershipDTO> toDTO(List<StandardUnitRecordMembershipPO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordMembershipDTO> list = new ArrayList<StandardUnitRecordMembershipDTO>();
		for (StandardUnitRecordMembershipPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitRecordMembershipPO> toPO(List<StandardUnitRecordMembershipDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordMembershipPO> list = new ArrayList<StandardUnitRecordMembershipPO>();
		for (StandardUnitRecordMembershipDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	public static StandardUnitRecordMembershipDTO toDTO(StandardUnitRecordMembershipVO src) {
		if (src == null)
		return null;	
		StandardUnitRecordMembershipDTO tar = new StandardUnitRecordMembershipDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());	
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static StandardUnitRecordMembershipVO toVO(StandardUnitRecordMembershipDTO src) {
		if (src == null)
		return null;	
		StandardUnitRecordMembershipVO tar = new StandardUnitRecordMembershipVO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());	
		tar.setStandardUnitRecordId(src.getStandardUnitRecordId());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<StandardUnitRecordMembershipDTO> toDTOs(List<StandardUnitRecordMembershipVO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordMembershipDTO> list = new ArrayList<StandardUnitRecordMembershipDTO>();
		for (StandardUnitRecordMembershipVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<StandardUnitRecordMembershipVO> toVO(List<StandardUnitRecordMembershipDTO> srcs) {
		if (srcs == null)
			return null;
		List<StandardUnitRecordMembershipVO> list = new ArrayList<StandardUnitRecordMembershipVO>();
		for (StandardUnitRecordMembershipDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
}
	