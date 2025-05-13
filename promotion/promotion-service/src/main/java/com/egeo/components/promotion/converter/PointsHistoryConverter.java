package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PointsHistoryDTO;
import com.egeo.components.promotion.po.PointsHistoryPO;
import com.egeo.components.promotion.vo.PointsHistoryVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-11-09 15:50:11
 */
public class PointsHistoryConverter {

	
	public static PointsHistoryDTO toDTO(PointsHistoryVO src) {
		PointsHistoryDTO tar = new PointsHistoryDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());	
		tar.setUserId(src.getUserId());	
		tar.setPoints(src.getPoints());	
		tar.setOperationType(src.getOperationType());	
		tar.setOperationPhase(src.getOperationPhase());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static PointsHistoryVO toVO(PointsHistoryDTO src) {
		PointsHistoryVO tar = new PointsHistoryVO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());	
		tar.setUserId(src.getUserId());	
		tar.setPoints(src.getPoints());	
		tar.setOperationType(src.getOperationType());	
		tar.setOperationPhase(src.getOperationPhase());	
		tar.setType(src.getType());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<PointsHistoryDTO> toDTOs(List<PointsHistoryVO> srcs) {
		if (srcs == null)
			return null;
		List<PointsHistoryDTO> list = new ArrayList<PointsHistoryDTO>();
		for (PointsHistoryVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PointsHistoryVO> toVO(List<PointsHistoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<PointsHistoryVO> list = new ArrayList<PointsHistoryVO>();
		for (PointsHistoryDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static PointsHistoryDTO toDTO(PointsHistoryPO src) {
		PointsHistoryDTO tar = new PointsHistoryDTO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setPoints(src.getPoints());
		tar.setOperationType(src.getOperationType());
		tar.setOperationPhase(src.getOperationPhase());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static PointsHistoryPO toPO(PointsHistoryDTO src) {
		PointsHistoryPO tar = new PointsHistoryPO();
		tar.setId(src.getId());
		tar.setOrderCode(src.getOrderCode());
		tar.setUserId(src.getUserId());
		tar.setPoints(src.getPoints());
		tar.setOperationType(src.getOperationType());
		tar.setOperationPhase(src.getOperationPhase());
		tar.setType(src.getType());
		tar.setCreateTime(src.getCreateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<PointsHistoryDTO> toDTO(List<PointsHistoryPO> srcs) {
		if (srcs == null)
			return null;
		List<PointsHistoryDTO> list = new ArrayList<PointsHistoryDTO>();
		for (PointsHistoryPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PointsHistoryPO> toPO(List<PointsHistoryDTO> srcs) {
		if (srcs == null)
			return null;
		List<PointsHistoryPO> list = new ArrayList<PointsHistoryPO>();
		for (PointsHistoryDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	