package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.CardStampsAdministrationDTO;
import com.egeo.components.product.po.CardStampsAdministrationPO;
import com.egeo.components.product.vo.CardStampsAdministrationVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-26 09:06:16
 */
public class CardStampsAdministrationConverter {
	
	
	public static CardStampsAdministrationDTO toDTO(CardStampsAdministrationVO src) {
		if (src == null)
		return null;	
		CardStampsAdministrationDTO tar = new CardStampsAdministrationDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setLeadingEndCategoryId(src.getLeadingEndCategoryId());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setSortValue(src.getSortValue());	
		tar.setIsShow(src.getIsShow());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateUserid(src.getUpdateUserid());	
		tar.setUpdateUsername(src.getUpdateUsername());	
		tar.setUpdateUserip(src.getUpdateUserip());	
		tar.setUpdateUsermac(src.getUpdateUsermac());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setClientVersionno(src.getClientVersionno());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static CardStampsAdministrationVO toVO(CardStampsAdministrationDTO src) {
		if (src == null)
		return null;	
		CardStampsAdministrationVO tar = new CardStampsAdministrationVO();
		tar.setId(src.getId());
		tar.setName(src.getName());	
		tar.setLeadingEndCategoryId(src.getLeadingEndCategoryId());	
		tar.setPictureUrl(src.getPictureUrl());	
		tar.setStandardUnitId(src.getStandardUnitId());	
		tar.setSortValue(src.getSortValue());	
		tar.setIsShow(src.getIsShow());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateUserid(src.getUpdateUserid());	
		tar.setUpdateUsername(src.getUpdateUsername());	
		tar.setUpdateUserip(src.getUpdateUserip());	
		tar.setUpdateUsermac(src.getUpdateUsermac());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setClientVersionno(src.getClientVersionno());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<CardStampsAdministrationDTO> toDTOs(List<CardStampsAdministrationVO> srcs) {
		if (srcs == null)
			return null;
		List<CardStampsAdministrationDTO> list = new ArrayList<CardStampsAdministrationDTO>();
		for (CardStampsAdministrationVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CardStampsAdministrationVO> toVO(List<CardStampsAdministrationDTO> srcs) {
		if (srcs == null)
			return null;
		List<CardStampsAdministrationVO> list = new ArrayList<CardStampsAdministrationVO>();
		for (CardStampsAdministrationDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CardStampsAdministrationDTO toDTO(CardStampsAdministrationPO src) {
		if (src == null)
		return null;	
		CardStampsAdministrationDTO tar = new CardStampsAdministrationDTO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setLeadingEndCategoryId(src.getLeadingEndCategoryId());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setSortValue(src.getSortValue());
		tar.setIsShow(src.getIsShow());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setClientVersionno(src.getClientVersionno());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CardStampsAdministrationPO toPO(CardStampsAdministrationDTO src) {
		if (src == null)
		return null;	
		CardStampsAdministrationPO tar = new CardStampsAdministrationPO();
		tar.setId(src.getId());
		tar.setName(src.getName());
		tar.setLeadingEndCategoryId(src.getLeadingEndCategoryId());
		tar.setPictureUrl(src.getPictureUrl());
		tar.setStandardUnitId(src.getStandardUnitId());
		tar.setSortValue(src.getSortValue());
		tar.setIsShow(src.getIsShow());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setClientVersionno(src.getClientVersionno());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CardStampsAdministrationDTO> toDTO(List<CardStampsAdministrationPO> srcs) {
		if (srcs == null)
			return null;
		List<CardStampsAdministrationDTO> list = new ArrayList<CardStampsAdministrationDTO>();
		for (CardStampsAdministrationPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CardStampsAdministrationPO> toPO(List<CardStampsAdministrationDTO> srcs) {
		if (srcs == null)
			return null;
		List<CardStampsAdministrationPO> list = new ArrayList<CardStampsAdministrationPO>();
		for (CardStampsAdministrationDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	