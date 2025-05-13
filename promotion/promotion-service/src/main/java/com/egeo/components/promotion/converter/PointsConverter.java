package com.egeo.components.promotion.converter;

import java.util.ArrayList;
import java.util.List;

import com.egeo.components.promotion.dto.PointsDTO;
import com.egeo.components.promotion.po.PointsPO;
import com.egeo.components.promotion.vo.PointsVO;

/**
 * DTO和PO相互转换工具类
 * 
 * @author min
 * @date 2017-11-09 15:50:11
 */
public class PointsConverter {

	public static PointsDTO toDTO(PointsVO src) {
		PointsDTO tar = new PointsDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setPoints(src.getPoints());	
		tar.setDesc(src.getDesc());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static PointsVO toVO(PointsDTO src) {
		PointsVO tar = new PointsVO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());	
		tar.setPoints(src.getPoints());	
		tar.setDesc(src.getDesc());	
		tar.setCreateTime(src.getCreateTime());	
		tar.setUpdateTime(src.getUpdateTime());	
		tar.setPlatformId(src.getPlatformId());	
		return tar;
	}

	public static List<PointsDTO> toDTOs(List<PointsVO> srcs) {
		if (srcs == null)
			return null;
		List<PointsDTO> list = new ArrayList<PointsDTO>();
		for (PointsVO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PointsVO> toVO(List<PointsDTO> srcs) {
		if (srcs == null)
			return null;
		List<PointsVO> list = new ArrayList<PointsVO>();
		for (PointsDTO src : srcs) {
			list.add(toVO(src));
		}
		return list;
	}
	public static PointsDTO toDTO(PointsPO src) {
		PointsDTO tar = new PointsDTO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setPoints(src.getPoints());
		tar.setDesc(src.getDesc());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static PointsPO toPO(PointsDTO src) {
		PointsPO tar = new PointsPO();
		tar.setId(src.getId());
		tar.setUserId(src.getUserId());
		tar.setPoints(src.getPoints());
		tar.setDesc(src.getDesc());
		tar.setCreateTime(src.getCreateTime());
		tar.setUpdateTime(src.getUpdateTime());
		tar.setPlatformId(src.getPlatformId());
		return tar;
	}

	public static List<PointsDTO> toDTO(List<PointsPO> srcs) {
		if (srcs == null)
			return null;
		List<PointsDTO> list = new ArrayList<PointsDTO>();
		for (PointsPO src : srcs) {
			list.add(toDTO(src));
		}
		return list;
	}

	public static List<PointsPO> toPO(List<PointsDTO> srcs) {
		if (srcs == null)
			return null;
		List<PointsPO> list = new ArrayList<PointsPO>();
		for (PointsDTO src : srcs) {
			list.add(toPO(src));
		}
		return list;
	}
}
	