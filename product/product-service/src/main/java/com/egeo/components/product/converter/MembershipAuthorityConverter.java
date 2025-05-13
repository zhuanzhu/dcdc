package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.condition.MembershipAuthorityCondition;
import com.egeo.components.product.dto.MembershipAuthorityDTO;
import com.egeo.components.product.po.MembershipAuthorityPO;
import com.egeo.components.product.vo.MembershipAuthorityVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author whf
 * @date 2018-09-06 16:12:28
 */
public class MembershipAuthorityConverter {

	public static MembershipAuthorityDTO toDTO(MembershipAuthorityVO src) {
		if (src == null)
		return null;	
		MembershipAuthorityDTO tar = new MembershipAuthorityDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());	
		tar.setAuthorityId(src.getAuthorityId());	
		tar.setIsStop(src.getIsStop());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MembershipAuthorityVO toVO(MembershipAuthorityDTO src) {
		if (src == null)
		return null;	
		MembershipAuthorityVO tar = new MembershipAuthorityVO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());	
		tar.setAuthorityId(src.getAuthorityId());	
		tar.setIsStop(src.getIsStop());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static List<MembershipAuthorityDTO> toDTOs(List<MembershipAuthorityVO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipAuthorityDTO> list = new ArrayList<MembershipAuthorityDTO>();
		for (MembershipAuthorityVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MembershipAuthorityVO> toVO(List<MembershipAuthorityDTO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipAuthorityVO> list = new ArrayList<MembershipAuthorityVO>();
		for (MembershipAuthorityDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MembershipAuthorityDTO toDTO(MembershipAuthorityPO src) {
		if (src == null)
		return null;	
		MembershipAuthorityDTO tar = new MembershipAuthorityDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setAuthorityId(src.getAuthorityId());
		tar.setIsStop(src.getIsStop());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}
	
	public static MembershipAuthorityDTO conditionToDTO(MembershipAuthorityCondition src) {
		if (src == null)
			return null;	
		MembershipAuthorityDTO tar = new MembershipAuthorityDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setAuthorityId(src.getAuthorityId());
		tar.setIsStop(src.getIsStop());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setAuthorityName(src.getAuthorityName());
		tar.setMembershipName(src.getMembershipName());
		return tar;
	}

	public static MembershipAuthorityPO toPO(MembershipAuthorityDTO src) {
		if (src == null)
		return null;	
		MembershipAuthorityPO tar = new MembershipAuthorityPO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setAuthorityId(src.getAuthorityId());
		tar.setIsStop(src.getIsStop());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MembershipAuthorityDTO> toDTO(List<MembershipAuthorityPO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipAuthorityDTO> list = new ArrayList<MembershipAuthorityDTO>();
		for (MembershipAuthorityPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}
	
	public static List<MembershipAuthorityDTO> conditionToDTO(List<MembershipAuthorityCondition> srcs) {
		if (srcs == null)
			return null;
		List<MembershipAuthorityDTO> list = new ArrayList<MembershipAuthorityDTO>();
		for (MembershipAuthorityCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}

	public static List<MembershipAuthorityPO> toPO(List<MembershipAuthorityDTO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipAuthorityPO> list = new ArrayList<MembershipAuthorityPO>();
		for (MembershipAuthorityDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	