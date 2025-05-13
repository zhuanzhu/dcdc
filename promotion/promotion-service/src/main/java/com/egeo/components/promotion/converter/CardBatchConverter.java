package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.CardBatchDTO;
import com.egeo.components.promotion.po.CardBatchPO;
import com.egeo.components.promotion.vo.CardBatchVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2018-07-16 09:23:47
 */
public class CardBatchConverter {

	
	public static CardBatchDTO toDTO(CardBatchVO src) {
		if (src == null)
		return null;	
		CardBatchDTO tar = new CardBatchDTO();
		tar.setId(src.getId());
		tar.setBatchCode(src.getBatchCode());	
		tar.setImportSum(src.getImportSum());	
		tar.setSource(src.getSource());	
		tar.setRemark(src.getRemark());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static CardBatchVO toVO(CardBatchDTO src) {
		if (src == null)
		return null;	
		CardBatchVO tar = new CardBatchVO();
		tar.setId(src.getId());
		tar.setBatchCode(src.getBatchCode());	
		tar.setImportSum(src.getImportSum());	
		tar.setSource(src.getSource());	
		tar.setRemark(src.getRemark());	
		tar.setCreateUserid(src.getCreateUserid());	
		tar.setCreateUsername(src.getCreateUsername());	
		tar.setCreateUserip(src.getCreateUserip());	
		tar.setCreateUsermac(src.getCreateUsermac());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<CardBatchDTO> toDTOs(List<CardBatchVO> srcs) {
		if (srcs == null)
			return null;
		List<CardBatchDTO> list = new ArrayList<CardBatchDTO>();
		for (CardBatchVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CardBatchVO> toVO(List<CardBatchDTO> srcs) {
		if (srcs == null)
			return null;
		List<CardBatchVO> list = new ArrayList<CardBatchVO>();
		for (CardBatchDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static CardBatchDTO toDTO(CardBatchPO src) {
		if (src == null)
		return null;	
		CardBatchDTO tar = new CardBatchDTO();
		tar.setId(src.getId());
		tar.setBatchCode(src.getBatchCode());
		tar.setImportSum(src.getImportSum());
		tar.setSource(src.getSource());
		tar.setRemark(src.getRemark());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static CardBatchPO toPO(CardBatchDTO src) {
		if (src == null)
		return null;	
		CardBatchPO tar = new CardBatchPO();
		tar.setId(src.getId());
		tar.setBatchCode(src.getBatchCode());
		tar.setImportSum(src.getImportSum());
		tar.setSource(src.getSource());
		tar.setRemark(src.getRemark());
		tar.setCreateUserid(src.getCreateUserid());
		tar.setCreateUsername(src.getCreateUsername());
		tar.setCreateUserip(src.getCreateUserip());
		tar.setCreateUsermac(src.getCreateUsermac());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<CardBatchDTO> toDTO(List<CardBatchPO> srcs) {
		if (srcs == null)
			return null;
		List<CardBatchDTO> list = new ArrayList<CardBatchDTO>();
		for (CardBatchPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<CardBatchPO> toPO(List<CardBatchDTO> srcs) {
		if (srcs == null)
			return null;
		List<CardBatchPO> list = new ArrayList<CardBatchPO>();
		for (CardBatchDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	