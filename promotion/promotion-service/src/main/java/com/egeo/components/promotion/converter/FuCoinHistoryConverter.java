package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.FuCoinHistoryDTO;
import com.egeo.components.promotion.po.FuCoinHistoryPO;
import com.egeo.components.promotion.vo.FuCoinHistoryVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-11-10 15:43:54
 */
public class FuCoinHistoryConverter {

	
	public static FuCoinHistoryDTO toDTO(FuCoinHistoryVO src) {
		FuCoinHistoryDTO tar = new FuCoinHistoryDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());	
		tar.setUserId(src.getUserId());	
		tar.setCoin(src.getCoin());	
		tar.setOperationType(src.getOperationType());	
		tar.setOperationPhase(src.getOperationPhase());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static FuCoinHistoryVO toVO(FuCoinHistoryDTO src) {
		FuCoinHistoryVO tar = new FuCoinHistoryVO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());	
		tar.setUserId(src.getUserId());	
		tar.setCoin(src.getCoin());	
		tar.setOperationType(src.getOperationType());	
		tar.setOperationPhase(src.getOperationPhase());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<FuCoinHistoryDTO> toDTOs(List<FuCoinHistoryVO> srcs) {
		if (srcs == null)
			return null;
		List<FuCoinHistoryDTO> list = new ArrayList<FuCoinHistoryDTO>();
		for (FuCoinHistoryVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FuCoinHistoryVO> toVO(List<FuCoinHistoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<FuCoinHistoryVO> list = new ArrayList<FuCoinHistoryVO>();
		for (FuCoinHistoryDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static FuCoinHistoryDTO toDTO(FuCoinHistoryPO src) {
		FuCoinHistoryDTO tar = new FuCoinHistoryDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setCoin(src.getCoin());
		tar.setOperationType(src.getOperationType());
		tar.setOperationPhase(src.getOperationPhase());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static FuCoinHistoryPO toPO(FuCoinHistoryDTO src) {
		FuCoinHistoryPO tar = new FuCoinHistoryPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setCoin(src.getCoin());
		tar.setOperationType(src.getOperationType());
		tar.setOperationPhase(src.getOperationPhase());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<FuCoinHistoryDTO> toDTO(List<FuCoinHistoryPO> srcs) {
		if (srcs == null)
			return null;
		List<FuCoinHistoryDTO> list = new ArrayList<FuCoinHistoryDTO>();
		for (FuCoinHistoryPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<FuCoinHistoryPO> toPO(List<FuCoinHistoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<FuCoinHistoryPO> list = new ArrayList<FuCoinHistoryPO>();
		for (FuCoinHistoryDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	