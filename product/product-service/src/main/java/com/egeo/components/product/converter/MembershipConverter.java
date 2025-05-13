package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.condition.MembershipCondition;
import com.egeo.components.product.dto.MembershipDTO;
import com.egeo.components.product.po.MembershipPO;
import com.egeo.components.product.vo.MembershipVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author whf
 * @date 2018-09-06 16:12:28
 */
public class MembershipConverter {

	public static MembershipDTO toDTO(MembershipVO src) {
		if (src == null)
		return null;	
		MembershipDTO tar = new MembershipDTO();
		tar.setId(src.getId());
		tar.setMembershipCode(src.getMembershipCode());	
		tar.setMembershipName(src.getMembershipName());	
		tar.setMembershipLogImgUrl(src.getMembershipLogImgUrl());	
		tar.setLinkedSkuId(src.getLinkedSkuId());	
		tar.setCategoryId(src.getCategoryId());	
		tar.setCategoryName(src.getCategoryName());	
		tar.setValidPeriodVal(src.getValidPeriodVal());	
		tar.setValidPeriodUnit(src.getValidPeriodUnit());	
		tar.setRemarks(src.getRemarks());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static MembershipVO toVO(MembershipDTO src) {
		if (src == null)
		return null;	
		MembershipVO tar = new MembershipVO();
		tar.setId(src.getId());
		tar.setMembershipCode(src.getMembershipCode());	
		tar.setMembershipName(src.getMembershipName());	
		tar.setMembershipLogImgUrl(src.getMembershipLogImgUrl());	
		tar.setLinkedSkuId(src.getLinkedSkuId());	
		tar.setCategoryId(src.getCategoryId());	
		tar.setCategoryName(src.getCategoryName());	
		tar.setValidPeriodVal(src.getValidPeriodVal());	
		tar.setValidPeriodUnit(src.getValidPeriodUnit());	
		tar.setRemarks(src.getRemarks());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		return tar;
	}

	public static List<MembershipDTO> toDTOs(List<MembershipVO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipDTO> list = new ArrayList<MembershipDTO>();
		for (MembershipVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MembershipVO> toVO(List<MembershipDTO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipVO> list = new ArrayList<MembershipVO>();
		for (MembershipDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MembershipDTO toDTO(MembershipPO src) {
		if (src == null)
		return null;	
		MembershipDTO tar = new MembershipDTO();
		tar.setId(src.getId());
		tar.setMembershipCode(src.getMembershipCode());
		tar.setMembershipName(src.getMembershipName());
		tar.setMembershipLogImgUrl(src.getMembershipLogImgUrl());
		tar.setLinkedSkuId(src.getLinkedSkuId());
		tar.setCategoryId(src.getCategoryId());
		tar.setCategoryName(src.getCategoryName());
		tar.setValidPeriodVal(src.getValidPeriodVal());
		tar.setValidPeriodUnit(src.getValidPeriodUnit());
		tar.setRemarks(src.getRemarks());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static MembershipPO toPO(MembershipDTO src) {
		if (src == null)
		return null;	
		MembershipPO tar = new MembershipPO();
		tar.setId(src.getId());
		tar.setMembershipCode(src.getMembershipCode());
		tar.setMembershipName(src.getMembershipName());
		tar.setMembershipLogImgUrl(src.getMembershipLogImgUrl());
		tar.setLinkedSkuId(src.getLinkedSkuId());
		tar.setCategoryId(src.getCategoryId());
		tar.setCategoryName(src.getCategoryName());
		tar.setValidPeriodVal(src.getValidPeriodVal());
		tar.setValidPeriodUnit(src.getValidPeriodUnit());
		tar.setRemarks(src.getRemarks());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		return tar;
	}

	public static List<MembershipDTO> toDTO(List<MembershipPO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipDTO> list = new ArrayList<MembershipDTO>();
		for (MembershipPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MembershipPO> toPO(List<MembershipDTO> srcs) {
		if (srcs == null)
			return null;
		List<MembershipPO> list = new ArrayList<MembershipPO>();
		for (MembershipDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
	
	public static MembershipDTO conditionToDTO(MembershipCondition src) {
		if (src == null)
		return null;	
		MembershipDTO tar = new MembershipDTO();
		tar.setId(src.getId());
		tar.setMembershipCode(src.getMembershipCode());
		tar.setMembershipName(src.getMembershipName());
		tar.setMembershipLogImgUrl(src.getMembershipLogImgUrl());
		tar.setLinkedSkuId(src.getLinkedSkuId());
		tar.setCategoryId(src.getCategoryId());
		tar.setCategoryName(src.getCategoryName());
		tar.setValidPeriodVal(src.getValidPeriodVal());
		tar.setValidPeriodUnit(src.getValidPeriodUnit());
		tar.setRemarks(src.getRemarks());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setUserId(src.getUserId());
		return tar;
	}
	
	public static List<MembershipDTO> conditionToDTO(List<MembershipCondition> srcs) {
		if (srcs == null)
			return null;
		List<MembershipDTO> list = new ArrayList<MembershipDTO>();
		for (MembershipCondition src : srcs) {
			list.add(conditionToDTO(src));
		}
		return list;
	}
	
}
	