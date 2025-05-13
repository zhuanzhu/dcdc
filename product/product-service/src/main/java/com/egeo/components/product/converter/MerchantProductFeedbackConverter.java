package com.egeo.components.product.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.product.dto.MerchantProductFeedbackDTO;
import com.egeo.components.product.po.MerchantProductFeedbackPO;
import com.egeo.components.product.vo.MerchantProductFeedbackVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author paul
 * @date 2018-09-13 15:05:18
 */
public class MerchantProductFeedbackConverter {
	
	
	public static MerchantProductFeedbackDTO toDTO(MerchantProductFeedbackVO src) {
		if (src == null)
		return null;	
		MerchantProductFeedbackDTO tar = new MerchantProductFeedbackDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());	
		tar.setMerchantProductName(src.getMerchantProductName());	
		tar.setAttributeName(src.getAttributeName());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setSellPlatformId(src.getSellPlatformId());	
		tar.setUserName(src.getUserName());	
		tar.setAccount(src.getAccount());	
		tar.setPhone(src.getPhone());	
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setFeedbackStatus(src.getFeedbackStatus());	
		tar.setReceiverId(src.getReceiverId());	
		tar.setAcceptResult(src.getAcceptResult());	
		tar.setCertificateUrl(src.getCertificateUrl());	
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MerchantProductFeedbackVO toVO(MerchantProductFeedbackDTO src) {
		if (src == null)
		return null;	
		MerchantProductFeedbackVO tar = new MerchantProductFeedbackVO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());	
		tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());	
		tar.setMerchantProductName(src.getMerchantProductName());	
		tar.setAttributeName(src.getAttributeName());	
		tar.setSalePrice(src.getSalePrice());	
		tar.setSellPlatformId(src.getSellPlatformId());	
		tar.setUserName(src.getUserName());	
		tar.setAccount(src.getAccount());	
		tar.setPhone(src.getPhone());	
		tar.setContent(src.getContent());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setFeedbackStatus(src.getFeedbackStatus());	
		tar.setReceiverId(src.getReceiverId());	
		tar.setAcceptResult(src.getAcceptResult());	
		tar.setCertificateUrl(src.getCertificateUrl());	
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantProductFeedbackDTO> toDTOs(List<MerchantProductFeedbackVO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductFeedbackDTO> list = new ArrayList<MerchantProductFeedbackDTO>();
		for (MerchantProductFeedbackVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductFeedbackVO> toVO(List<MerchantProductFeedbackDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductFeedbackVO> list = new ArrayList<MerchantProductFeedbackVO>();
		for (MerchantProductFeedbackDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static MerchantProductFeedbackDTO toDTO(MerchantProductFeedbackPO src) {
		if (src == null)
		return null;	
		MerchantProductFeedbackDTO tar = new MerchantProductFeedbackDTO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());
		tar.setMerchantProductName(src.getMerchantProductName());
		tar.setAttributeName(src.getAttributeName());
		tar.setSalePrice(src.getSalePrice());
		tar.setSellPlatformId(src.getSellPlatformId());
		tar.setUserName(src.getUserName());
		tar.setAccount(src.getAccount());
		tar.setPhone(src.getPhone());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setFeedbackStatus(src.getFeedbackStatus());
		tar.setReceiverId(src.getReceiverId());
		tar.setAcceptResult(src.getAcceptResult());
		tar.setCertificateUrl(src.getCertificateUrl());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static MerchantProductFeedbackPO toPO(MerchantProductFeedbackDTO src) {
		if (src == null)
		return null;	
		MerchantProductFeedbackPO tar = new MerchantProductFeedbackPO();
		tar.setId(src.getId());
		tar.setMerchantProductId(src.getMerchantProductId());
		tar.setMerchantProductSerialNumber(src.getMerchantProductSerialNumber());
		tar.setMerchantProductName(src.getMerchantProductName());
		tar.setAttributeName(src.getAttributeName());
		tar.setSalePrice(src.getSalePrice());
		tar.setSellPlatformId(src.getSellPlatformId());
		tar.setUserName(src.getUserName());
		tar.setAccount(src.getAccount());
		tar.setPhone(src.getPhone());
		tar.setContent(src.getContent());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setFeedbackStatus(src.getFeedbackStatus());
		tar.setReceiverId(src.getReceiverId());
		tar.setAcceptResult(src.getAcceptResult());
		tar.setCertificateUrl(src.getCertificateUrl());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<MerchantProductFeedbackDTO> toDTO(List<MerchantProductFeedbackPO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductFeedbackDTO> list = new ArrayList<MerchantProductFeedbackDTO>();
		for (MerchantProductFeedbackPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<MerchantProductFeedbackPO> toPO(List<MerchantProductFeedbackDTO> srcs) {
		if (srcs == null)
			return null;
		List<MerchantProductFeedbackPO> list = new ArrayList<MerchantProductFeedbackPO>();
		for (MerchantProductFeedbackDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	