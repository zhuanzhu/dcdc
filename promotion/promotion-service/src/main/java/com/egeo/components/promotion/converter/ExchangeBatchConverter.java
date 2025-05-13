package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.ExchangeBatchDTO;
import com.egeo.components.promotion.po.ExchangeBatchPO;
import com.egeo.components.promotion.vo.ExchangeBatchVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author feng
 * @date 2018-12-14 10:57:17
 */
public class ExchangeBatchConverter {

	
	public static ExchangeBatchDTO toDTO(ExchangeBatchVO src) {
		if (src == null)
		return null;	
		ExchangeBatchDTO tar = new ExchangeBatchDTO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());	
		tar.setBatchId(src.getBatchId());	
		tar.setType(src.getType());	
		tar.setAddPrice(src.getAddPrice());	
		tar.setSort(src.getSort());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ExchangeBatchVO toVO(ExchangeBatchDTO src) {
		if (src == null)
		return null;	
		ExchangeBatchVO tar = new ExchangeBatchVO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());	
		tar.setBatchId(src.getBatchId());	
		tar.setType(src.getType());	
		tar.setAddPrice(src.getAddPrice());	
		tar.setSort(src.getSort());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<ExchangeBatchDTO> toDTOs(List<ExchangeBatchVO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeBatchDTO> list = new ArrayList<ExchangeBatchDTO>();
		for (ExchangeBatchVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExchangeBatchVO> toVO(List<ExchangeBatchDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeBatchVO> list = new ArrayList<ExchangeBatchVO>();
		for (ExchangeBatchDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ExchangeBatchDTO toDTO(ExchangeBatchPO src) {
		if (src == null)
		return null;	
		ExchangeBatchDTO tar = new ExchangeBatchDTO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());
		tar.setBatchId(src.getBatchId());
		tar.setType(src.getType());
		tar.setAddPrice(src.getAddPrice());
		tar.setSort(src.getSort());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ExchangeBatchPO toPO(ExchangeBatchDTO src) {
		if (src == null)
		return null;	
		ExchangeBatchPO tar = new ExchangeBatchPO();
		tar.setId(src.getId());
		tar.setExchangeId(src.getExchangeId());
		tar.setBatchId(src.getBatchId());
		tar.setType(src.getType());
		tar.setAddPrice(src.getAddPrice());
		tar.setSort(src.getSort());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<ExchangeBatchDTO> toDTO(List<ExchangeBatchPO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeBatchDTO> list = new ArrayList<ExchangeBatchDTO>();
		for (ExchangeBatchPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExchangeBatchPO> toPO(List<ExchangeBatchDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeBatchPO> list = new ArrayList<ExchangeBatchPO>();
		for (ExchangeBatchDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	