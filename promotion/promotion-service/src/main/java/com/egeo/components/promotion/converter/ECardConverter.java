package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.ECardDTO;
import com.egeo.components.promotion.po.ECardPO;
import com.egeo.components.promotion.vo.ECardVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-01-10 05:14:25
 */
public class ECardConverter {

	
	public static ECardDTO toDTO(ECardVO src) {
		if (src == null)
		return null;	
		ECardDTO tar = new ECardDTO();
		tar.setId(src.getId());
		tar.setBatch(src.getBatch());	
		tar.setSkuId(src.getSkuId());	
		tar.setSkuName(src.getSkuName());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());	
		tar.setType(src.getType());	
		tar.setCardNumber(src.getCardNumber());	
		tar.setCardThick(src.getCardThick());	
		tar.setUuid(src.getUuid());	
		tar.setStartTime(src.getStartTime());	
		tar.setEndTime(src.getEndTime());	
		tar.setSource(src.getSource());	
		tar.setFaceValue(src.getFaceValue());	
		tar.setRemark(src.getRemark());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateUserid(src.getUpdateUserid());	
		tar.setUpdateUsername(src.getUpdateUsername());	
		tar.setUpdateUserip(src.getUpdateUserip());	
		tar.setUpdateUsermac(src.getUpdateUsermac());	
		tar.setClientVersionno(src.getClientVersionno());	
		tar.setIsValid(src.getIsValid());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setUserId(src.getUserId());
		tar.setUserLoginName(src.getUserLoginName());	
		tar.setIsAllocation(src.getIsAllocation());	
		tar.setAllocationTime(src.getAllocationTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setUserId(src.getUserId());
		return tar;
	}

	public static ECardVO toVO(ECardDTO src) {
		if (src == null)
		return null;	
		ECardVO tar = new ECardVO();
		tar.setId(src.getId());
		tar.setBatch(src.getBatch());	
		tar.setSkuId(src.getSkuId());	
		tar.setSkuName(src.getSkuName());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());	
		tar.setType(src.getType());	
		tar.setCardNumber(src.getCardNumber());	
		tar.setCardThick(src.getCardThick());	
		tar.setUuid(src.getUuid());	
		tar.setStartTime(src.getStartTime());	
		tar.setEndTime(src.getEndTime());	
		tar.setSource(src.getSource());	
		tar.setFaceValue(src.getFaceValue());	
		tar.setRemark(src.getRemark());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateUserid(src.getUpdateUserid());	
		tar.setUpdateUsername(src.getUpdateUsername());	
		tar.setUpdateUserip(src.getUpdateUserip());	
		tar.setUpdateUsermac(src.getUpdateUsermac());	
		tar.setClientVersionno(src.getClientVersionno());	
		tar.setIsValid(src.getIsValid());	
		tar.setOrderCode(src.getOrderCode());	
		tar.setUserId(src.getUserId());
		tar.setUserLoginName(src.getUserLoginName());	
		tar.setIsAllocation(src.getIsAllocation());	
		tar.setAllocationTime(src.getAllocationTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		tar.setUserId(src.getUserId());
		return tar;
	}

	public static List<ECardDTO> toDTOs(List<ECardVO> srcs) {
		if (srcs == null)
			return null;
		List<ECardDTO> list = new ArrayList<ECardDTO>();
		for (ECardVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ECardVO> toVO(List<ECardDTO> srcs) {
		if (srcs == null)
			return null;
		List<ECardVO> list = new ArrayList<ECardVO>();
		for (ECardDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ECardDTO toDTO(ECardPO src) {
		if (src == null)
		return null;	
		ECardDTO tar = new ECardDTO();
		tar.setId(src.getId());
		tar.setBatch(src.getBatch());
		tar.setSkuId(src.getSkuId());
		tar.setSkuName(src.getSkuName());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());
		tar.setType(src.getType());
		tar.setCardNumber(src.getCardNumber());
		tar.setCardThick(src.getCardThick());
		tar.setUuid(src.getUuid());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setSource(src.getSource());
		tar.setFaceValue(src.getFaceValue());
		tar.setRemark(src.getRemark());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setClientVersionno(src.getClientVersionno());
		tar.setIsValid(src.getIsValid());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setUserLoginName(src.getUserLoginName());
		tar.setIsAllocation(src.getIsAllocation());
		tar.setAllocationTime(src.getAllocationTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setUserId(src.getUserId());
		tar.setStartTimeBegin(src.getStartTimeBegin());
		tar.setStartTimeFinish(src.getStartTimeFinish());
		tar.setEndTimeBegin(src.getEndTimeBegin());
		tar.setEndTimeFinish(src.getEndTimeFinish());
		tar.setCreateTimeBegin(src.getCreateTimeBegin());
		tar.setCreateTimeFinish(src.getCreateTimeFinish());
		tar.setAllocationTimeBegin(src.getAllocationTimeBegin());
		tar.setAllocationTimeFinish(src.getAllocationTimeFinish());
		tar.setShortUrl(src.getShortUrl());
		return tar;
	}

	public static ECardPO toPO(ECardDTO src) {
		if (src == null)
		return null;	
		ECardPO tar = new ECardPO();
		tar.setId(src.getId());
		tar.setBatch(src.getBatch());
		tar.setSkuId(src.getSkuId());
		tar.setSkuName(src.getSkuName());
		tar.setSkuSerialNumber(src.getSkuSerialNumber());
		tar.setType(src.getType());
		tar.setCardNumber(src.getCardNumber());
		tar.setCardThick(src.getCardThick());
		tar.setUuid(src.getUuid());
		tar.setStartTime(src.getStartTime());
		tar.setEndTime(src.getEndTime());
		tar.setSource(src.getSource());
		tar.setFaceValue(src.getFaceValue());
		tar.setRemark(src.getRemark());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateUserid(src.getUpdateUserid());
		tar.setUpdateUsername(src.getUpdateUsername());
		tar.setUpdateUserip(src.getUpdateUserip());
		tar.setUpdateUsermac(src.getUpdateUsermac());
		tar.setClientVersionno(src.getClientVersionno());
		tar.setIsValid(src.getIsValid());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setUserLoginName(src.getUserLoginName());
		tar.setIsAllocation(src.getIsAllocation());
		tar.setAllocationTime(src.getAllocationTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		tar.setUserId(src.getUserId());
		tar.setStartTimeBegin(src.getStartTimeBegin());
		tar.setStartTimeFinish(src.getStartTimeFinish());
		tar.setEndTimeBegin(src.getEndTimeBegin());
		tar.setEndTimeFinish(src.getEndTimeFinish());
		tar.setCreateTimeBegin(src.getCreateTimeBegin());
		tar.setCreateTimeFinish(src.getCreateTimeFinish());
		tar.setAllocationTimeBegin(src.getAllocationTimeBegin());
		tar.setAllocationTimeFinish(src.getAllocationTimeFinish());
		tar.setShortUrl(src.getShortUrl());
		return tar;
	}

	public static List<ECardDTO> toDTO(List<ECardPO> srcs) {
		if (srcs == null)
			return null;
		List<ECardDTO> list = new ArrayList<ECardDTO>();
		for (ECardPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ECardPO> toPO(List<ECardDTO> srcs) {
		if (srcs == null)
			return null;
		List<ECardPO> list = new ArrayList<ECardPO>();
		for (ECardDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	