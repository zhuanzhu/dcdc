package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MembershipUserDTO;
import com.egeo.components.product.po.MembershipUserPO;
import com.egeo.components.product.vo.MembershipUserVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author whf
 * @date 2018-09-06 16:12:29
 */
public class MembershipUserConverter {

	
	public static MembershipUserDTO toDTO(MembershipUserVO src) {
		if (src == null)
		return null;	
		MembershipUserDTO tar = new MembershipUserDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());	
		tar.setUserId(src.getUserId());	
		tar.setStatusCode(src.getStatusCode());	
		tar.setStartTime(src.getStartTime());	
		tar.setEndTime(src.getEndTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MembershipUserVO toVO(MembershipUserDTO src) {
		if (src == null)
		return null;	
		MembershipUserVO tar = new MembershipUserVO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());	
		tar.setUserId(src.getUserId());	
		tar.setStatusCode(src.getStatusCode());	
		tar.setStartTime(src.getStartTime());	
		tar.setEndTime(src.getEndTime());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static List<MembershipUserDTO> toDTOs(List<MembershipUserVO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipUserDTO> list = new ArrayList<MembershipUserDTO>();
		for (MembershipUserVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MembershipUserVO> toVO(List<MembershipUserDTO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipUserVO> list = new ArrayList<MembershipUserVO>();
		for (MembershipUserDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MembershipUserDTO toDTO(MembershipUserPO src) {
		if (src == null)
		return null;	
		MembershipUserDTO tar = new MembershipUserDTO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setUserId(src.getUserId());
		tar.setStatusCode(src.getStatusCode());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MembershipUserPO toPO(MembershipUserDTO src) {
		if (src == null)
		return null;	
		MembershipUserPO tar = new MembershipUserPO();
		tar.setId(src.getId());
		tar.setMembershipId(src.getMembershipId());
		tar.setUserId(src.getUserId());
		tar.setStatusCode(src.getStatusCode());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MembershipUserDTO> toDTO(List<MembershipUserPO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipUserDTO> list = new ArrayList<MembershipUserDTO>();
		for (MembershipUserPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MembershipUserPO> toPO(List<MembershipUserDTO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipUserPO> list = new ArrayList<MembershipUserPO>();
		for (MembershipUserDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	