package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.condition.ExchangeActivityCondition;
import com.egeo.components.promotion.dto.ExchangeActivityDTO;
import com.egeo.components.promotion.po.ExchangeActivityPO;
import com.egeo.components.promotion.vo.ExchangeActivityVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author feng
 * @date 2018-12-14 10:57:17
 */
public class ExchangeActivityConverter {

	public static ExchangeActivityDTO toDTO(ExchangeActivityVO src) {
		if (src == null)
		return null;	
		ExchangeActivityDTO tar = new ExchangeActivityDTO();
		tar.setId(src.getId());
		tar.setSort(src.getSort());	
		tar.setExchangeName(src.getExchangeName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setEndTime(src.getEndTime());	
		tar.setStatus(src.getStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTimeStr(src.getCreateTimeStr());
		tar.setUpdateTimeStr(src.getUpdateTimeStr());
		tar.setEndTimeStr(src.getEndTimeStr());
		tar.setUnitStatus(src.getUnitStatus());
		tar.setNum(src.getNum());

		return tar;
	}

	public static ExchangeActivityVO toVO(ExchangeActivityDTO src) {
		if (src == null)
		return null;	
		ExchangeActivityVO tar = new ExchangeActivityVO();
		tar.setId(src.getId());
		tar.setSort(src.getSort());	
		tar.setExchangeName(src.getExchangeName());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setEndTime(src.getEndTime());	
		tar.setStatus(src.getStatus());
		tar.setPlatformId(src.getPlatformId());
		tar.setCreateTimeStr(src.getCreateTimeStr());
		tar.setUpdateTimeStr(src.getUpdateTimeStr());
		tar.setEndTimeStr(src.getEndTimeStr());
		tar.setUnitStatus(src.getUnitStatus());
		tar.setNum(src.getNum());
		return tar;
	}

	public static List<ExchangeActivityDTO> toDTOs(List<ExchangeActivityVO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeActivityDTO> list = new ArrayList<ExchangeActivityDTO>();
		for (ExchangeActivityVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExchangeActivityVO> toVO(List<ExchangeActivityDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeActivityVO> list = new ArrayList<ExchangeActivityVO>();
		for (ExchangeActivityDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static ExchangeActivityDTO toDTO(ExchangeActivityPO src) {
		if (src == null)
		return null;	
		ExchangeActivityDTO tar = new ExchangeActivityDTO();
		tar.setId(src.getId());
		tar.setSort(src.getSort());
		tar.setExchangeName(src.getExchangeName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setEndTime(src.getEndTime());
		tar.setStatus(src.getStatus());
		tar.setIdIcon(src.getIdIcon());
		tar.setSortIcon(src.getSortIcon());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static ExchangeActivityPO toPO(ExchangeActivityDTO src) {
		if (src == null)
		return null;	
		ExchangeActivityPO tar = new ExchangeActivityPO();
		tar.setId(src.getId());
		tar.setSort(src.getSort());
		tar.setExchangeName(src.getExchangeName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setEndTime(src.getEndTime());
		tar.setStatus(src.getStatus());
		tar.setIdIcon(src.getIdIcon());
		tar.setSortIcon(src.getSortIcon());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<ExchangeActivityDTO> toDTO(List<ExchangeActivityPO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeActivityDTO> list = new ArrayList<ExchangeActivityDTO>();
		for (ExchangeActivityPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<ExchangeActivityPO> toPO(List<ExchangeActivityDTO> srcs) {
		if (srcs == null)
			return null;
		List<ExchangeActivityPO> list = new ArrayList<ExchangeActivityPO>();
		for (ExchangeActivityDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}

	public static ExchangeActivityCondition toCondition(ExchangeActivityDTO src) {
		if (src == null)
			return null;
		ExchangeActivityCondition tar = new ExchangeActivityCondition();
		tar.setId(src.getId());
		tar.setSort(src.getSort());
		tar.setExchangeName(src.getExchangeName());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setEndTime(src.getEndTime());
		tar.setStatus(src.getStatus());
		tar.setIdIcon(src.getIdIcon());
		tar.setSortIcon(src.getSortIcon());
		tar.setPlatformId(src.getPlatformId());
		tar.setNewBatchList(ExchangeBatchConverter.toPO(src.getNewBatchList()));
		tar.setOldBatchIdList(src.getOldBatchIdList());
		tar.setUnitStatus(src.getUnitStatus());
		return tar;
	}

}
	